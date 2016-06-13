/*$Id: $
 * author             date   comment
 * cwjcsu@gmail.com  2013-3-14  Created
 */
package com.cwjcsu.common.exec;

import com.cwjcsu.common.IOUtils;
import com.cwjcsu.common.NamedThreadFactory;
import com.cwjcsu.common.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.*;

public class LocalCommandService {
    static final Logger logger = LoggerFactory
            .getLogger(LocalCommandService.class);

    private ExecutorService localCommandPool = null;

    private static class LazyHolder {
        private static final LocalCommandService INSTANCE = new LocalCommandService();
    }

    private LocalCommandService() {
        String threadCountStr = System.getProperty("localcommand.service.thread.count");
        String aliveSecondsStr = System.getProperty("localcommand.service.thread.aliveSeconds");
        int threadCount = 20;
        int aliveSeconds = 60;
        try {
            if (threadCountStr != null) {
                threadCount = Integer.parseInt(threadCountStr);
            }
            if (aliveSecondsStr != null) {
                aliveSeconds = Integer.parseInt(aliveSecondsStr);
            }
        } catch (NumberFormatException e) {
        }
        localCommandPool = new ThreadPoolExecutor(threadCount, Integer.MAX_VALUE,
                aliveSeconds, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(), new NamedThreadFactory("localcommand-service-thread"));
    }

    public static LocalCommandService getInstance() {
        return LazyHolder.INSTANCE;
    }

    public ExecutorService getExecutorService() {
        return localCommandPool;
    }

    public ExecuteResult executeCommand(final Map<String, String> environment, final String[] command, final CommandOutputStreamHandler outputStreamHandler, final long timeout, final boolean redirect) {
        Process process = null;
        InputStream pIn = null;
        InputStream pErr = null;
        Future<Integer> executeFuture = null;
        Future<String> outputGobblerFuture = null;
        Future<String> errorGobblerFuture = null;
        try {
            ProcessBuilder pb = new ProcessBuilder(command);
            if (environment != null) {
                pb.environment().putAll(environment);
            }
            pb.redirectErrorStream(redirect);
            process = pb.start();

            final Process p = process;

            pIn = process.getInputStream();
            StreamGobbler outputGobbler = new StreamGobbler(
                    pIn, "OUTPUT");
            outputGobblerFuture = localCommandPool.submit(outputGobbler);
            if (!redirect) {
                pErr = process.getErrorStream();
                StreamGobbler errorGobbler = new StreamGobbler(pErr, "ERROR");
                errorGobblerFuture = localCommandPool.submit(errorGobbler);
            }

            if (outputStreamHandler != null) {
                outputStreamHandler.handle(p.getOutputStream());
            }
            try {
                //close process's output stream.
                p.getOutputStream().close();
            } catch (Throwable e) {
            }

            // create a Callable for the command's Process which can be called
            // by an Executor
            Callable<Integer> call = new Callable<Integer>() {
                public Integer call() throws Exception {
                    p.waitFor();
                    return p.exitValue();
                }
            };

            // submit the command's call and get the result from a
            executeFuture = localCommandPool.submit(call);
            int exitCode = executeFuture.get(timeout,
                    TimeUnit.MILLISECONDS);
            return new ExecuteResult(exitCode, outputGobblerFuture.get(timeout, TimeUnit.MILLISECONDS), errorGobblerFuture != null ? errorGobblerFuture.get(timeout, TimeUnit.MILLISECONDS) : null);
        } catch (IOException ex) {
            String errorMessage = "The command [" + StringUtils.join(command, " ")
                    + "] execute failed.";
            logger.debug(errorMessage, ex);
            return new ExecuteResult(-1, ExecuteResult.FailType.IOError, null, ex.toString());
        } catch (TimeoutException ex) {
            String errorMessage = "The command [" + StringUtils.join(command, " ") + "] timed out.";
            logger.debug(errorMessage, ex);
            return new ExecuteResult(-1, ExecuteResult.FailType.TimedOut, null, "Timed out.");
        } catch (ExecutionException ex) {
            String errorMessage = "The command [" + StringUtils.join(command, " ")
                    + "] did not complete due to an execution error.";
            String error = ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage();
            logger.debug(errorMessage, ex);
            ExecuteResult.FailType failType = ExecuteResult.FailType.Error;
            if (ex.getCause() instanceof InterruptedException) {
                failType = ExecuteResult.FailType.Interrupted;
            }
            return new ExecuteResult(-1, failType, null, error);
        } catch (InterruptedException ex) {
            String errorMessage = "The command [" + StringUtils.join(command, " ")
                    + "] did not complete due to an interrupted error.";
            logger.debug(errorMessage, ex);
            return new ExecuteResult(-1, ExecuteResult.FailType.Interrupted, null, "Interrupted");
        } finally {
            if (executeFuture != null) {
                try {
                    executeFuture.cancel(true);
                } catch (Exception ignore) {
                }
            }
            if (pIn != null) {
                IOUtils.closeQuietly(pIn);
                if (outputGobblerFuture != null && !outputGobblerFuture.isCancelled()) {
                    outputGobblerFuture.cancel(true);
                }
            }
            if (pErr != null) {
                IOUtils.closeQuietly(pErr);
                if (errorGobblerFuture != null && !errorGobblerFuture.isCancelled()) {
                    errorGobblerFuture.cancel(true);
                }
            }
            if (process != null) {
                process.destroy();
            }
        }
    }

    public ExecuteResult executeCommand(String[] command, long timeout) {
        return this.executeCommand(command, timeout, false);
    }

    public ExecuteResult executeCommand(String[] command, CommandOutputStreamHandler outputStreamHandler, long timeout, boolean redirect) {
        return this.executeCommand(null, command, outputStreamHandler, timeout, redirect);
    }

    public ExecuteResult executeCommand(String[] command, long timeout, boolean redirect) {
        return this.executeCommand(command, null, timeout, redirect);
    }
}
