package com.cwjcsu.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;

/**
 * TODO shutdown all Executors after Spring shutting down.
 * 
 * @author atlas
 * @date 2012-11-20
 */
public class ExecutorUtil {

	private static ConcurrentMap<String, ExecutorService> executors = new ConcurrentHashMap<String, ExecutorService>();
	private static ScheduledExecutorService scheduledExecutorService;

	public static synchronized ScheduledExecutorService getScheduler() {
		if (scheduledExecutorService == null) {
			scheduledExecutorService = Executors.newScheduledThreadPool(3,
					new NamedThreadFactory("HA-SCHDL"));
		}
		return scheduledExecutorService;
	}

	/**
	 * 
	 * @param command
	 * @param delay
	 *            milliseconds
	 * @return
	 */
	public static ScheduledFuture<?> schedule(Runnable command, long delay) {
		return getScheduler().schedule(command, delay, TimeUnit.MILLISECONDS);
	}

	/**
	 * schedule a one-shot job
	 * 
	 * @param name
	 * @param command
	 * @param delay
	 *            milliseconds
	 * @return
	 */
	public static ScheduledFuture<?> schedule(String name, Runnable command,
			long delay) {
		return getScheduler().schedule(wrap(name, command), delay,
				TimeUnit.MILLISECONDS);
	}

	/**
	 * 
	 * @param callable
	 * @param delay
	 *            milliseconds
	 * @return
	 */
	public static <V> ScheduledFuture<V> schedule(Callable<V> callable,
			long delay) {
		return getScheduler().schedule(callable, delay, TimeUnit.MILLISECONDS);
	}

	public static <V> ScheduledFuture<V> schedule(String name,
			Callable<V> callable, long delay) {
		return getScheduler().schedule(wrap(name, callable), delay,
				TimeUnit.MILLISECONDS);
	}

	/**
	 * 
	 * @param command
	 * @param initialDelay
	 *            milliseconds
	 * @param period
	 *            milliseconds
	 * @return
	 */
	public static ScheduledFuture<?> scheduleAtFixedRate(Runnable command,
			long initialDelay, long period) {
		return getScheduler().scheduleAtFixedRate(command, initialDelay,
				period, TimeUnit.MILLISECONDS);
	}

	/**
	 * 
	 * @param command
	 * @param initialDelay
	 *            milliseconds
	 * @param delay
	 *            milliseconds
	 * @return
	 */
	public static ScheduledFuture<?> scheduleWithFixedDelay(Runnable command,
			long initialDelay, long delay) {
		return getScheduler().scheduleWithFixedDelay(command, initialDelay,
				delay, TimeUnit.MILLISECONDS);
	}

	public static ScheduledFuture<?> scheduleWithFixedDelay(String info,
			Runnable command, long initialDelay, long delay) {
		if (info != null) {
			command = wrap(info, command);
		}
		return getScheduler().scheduleWithFixedDelay(command, initialDelay,
				delay, TimeUnit.MILLISECONDS);
	}

	public static ExecutorService getExecutor(String name) {
		ExecutorService executor = executors.get(name);
		if (executor != null) {
			return executor;
		}
		// TODO add priority.
		ThreadPoolExecutor executor0 = new ThreadPoolExecutor(0, 20, 60L,
				TimeUnit.SECONDS, new SynchronousQueue<Runnable>(),
				new NamedThreadFactory(name));
		executor0.setRejectedExecutionHandler(new CallerRunsPolicy());
		ExecutorService old = executors.putIfAbsent(name, executor0);
		if (old != null)
			return old;
		return executor0;
	}

	public static ExecutorService getDefaultExecutor() {
		return getExecutor("AJ");
	}

	public static Runnable wrap(final String name, final Runnable r) {
		return new Runnable() {
			public void run() {
				String oldName = Thread.currentThread().getName();
				try {
					Thread.currentThread().setName(oldName + "=>" + name);
					r.run();
				} catch (Throwable e) {
					_.COMMON.error("error executing " + name, e);
				} finally {
					Thread.currentThread().setName(oldName);
				}
			}
		};
	}

	public static <R> Callable<R> wrap(final String name, final Callable<R> r) {
		return new Callable<R>() {
			public R call() throws Exception {
				String oldName = Thread.currentThread().getName();
				try {
					Thread.currentThread().setName(oldName + "=>" + name);
					return r.call();
				} catch (Throwable e) {
					_.COMMON.error("error calling " + name, e);
					if (e instanceof Exception) {
						throw (Exception) e;
					} else {
						throw new Exception("error calling " + name, e);
					}
				} finally {
					Thread.currentThread().setName(oldName);
				}
			}
		};
	}

	public static void execute(final String threadName, final Runnable r) {
		execute(wrap(threadName, r));
	}

	public static <V> Future<V> execute(final String threadName,
			final Callable<V> r) {
		return getDefaultExecutor().submit(wrap(threadName, r));
	}

	public static void execute(Runnable r) {
		getDefaultExecutor().execute(r);
	}

	public static void executeSimultaneously(final List<Runnable> jobs) {
		executeSimultaneously(null, jobs);
	}

	public static void executeSimultaneously(String name,
			final List<Runnable> jobs) {
		final CountDownLatch cdl = new CountDownLatch(jobs.size());
		for (int i = 0; i < jobs.size(); i++) {
			final int index = i;
			Runnable wrapper = new Runnable() {
				public void run() {
					try {
						jobs.get(index).run();
					} finally {
						cdl.countDown();
					}
				}
			};
			String newName = null;
			if (name != null) {
				newName = "AsyncCountDown[" + index + "]";
			} else {
				newName = "AsyncCountDown[" + index + "]=>" + name;
			}
			execute(newName, wrapper);
		}
		try {
			cdl.await();
		} catch (InterruptedException e) {
		}
	}

	public static <V> Map<String, V> executeSimultaneously(
			final Map<String, Callable<V>> jobs) {
		return executeSimultaneously(jobs, 0);
	}

	public static <V> Map<String, V> executeSimultaneously(
			final Map<String, Callable<V>> jobs, long timeout) {
		final CountDownLatch cdl = new CountDownLatch(jobs.size());
		final Map<String, V> results = new HashMap<String, V>(jobs.size());
		final Map<String, Future<V>> futures = new HashMap<String, Future<V>>(
				jobs.size());
		for (final Map.Entry<String, Callable<V>> entry : jobs.entrySet()) {
			Callable<V> wrapper = new Callable<V>() {
				public V call() throws Exception {
					try {
						V v = entry.getValue().call();
						return v;
					} finally {
						cdl.countDown();
					}
				}
			};
			String newName = "AsyncCountDown[" + entry.getKey() + "]";
			futures.put(entry.getKey(), execute(newName, wrapper));
		}
		try {
			if (timeout > 0) {
				cdl.await(timeout, TimeUnit.MILLISECONDS);
			} else {
				cdl.await();
			}
		} catch (InterruptedException e) {
		}
		for (Map.Entry<String, Future<V>> entry : futures.entrySet()) {
			Future<V> future = entry.getValue();
			try {
				V v = future.get(1, TimeUnit.MILLISECONDS);
				results.put(entry.getKey(), v);
			} catch (Exception e) {
				results.put(entry.getKey(), null);
			}
		}
		return results;
	}

	public static void shutdown(boolean now) {
		if (scheduledExecutorService != null) {
			if (now) {
				scheduledExecutorService.shutdownNow();
				getDefaultExecutor().shutdownNow();
			} else {
				scheduledExecutorService.shutdown();
				getDefaultExecutor().shutdownNow();
			}
		}
	}
}
