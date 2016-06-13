package com.cwjcsu.common.ping;

import com.cwjcsu.common.IOUtils;
import com.cwjcsu.common.Resources;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * 
 * @author atlas
 * 
 * @date 2012-8-24
 */
class Worker implements Runnable {
	private static Logger LOG = PingResolver.LOG;
	private final String host;
	private final CountDownLatch cdl;
	private int time = Integer.MAX_VALUE;

	private Process process = null;
	private PingResolver resolver;

	private boolean debug = true;

	Worker(String commandLine, CountDownLatch cdl, PingResolver resolver) {
		this(commandLine, cdl, resolver, true);
	}

	Worker(String commandLine, CountDownLatch cdl, PingResolver resolver,
			boolean debug) {
		this.cdl = cdl;
		this.host = commandLine;
		this.resolver = resolver;
		this.debug = debug;
	}

	public void run() {
		int exit = -1;
		String output = null;
		String pingcmd = null;
		long start = System.nanoTime();
		try {
			pingcmd = resolver.getPingCommand(1, host);
			if (debug) {
				LOG.log(Level.INFO, "executing ping command " + pingcmd);
			}
			ProcessBuilder pb = new ProcessBuilder(pingcmd.split("\\s+"));
			pb.redirectErrorStream(true);
			start = System.nanoTime();
			process = pb.start();
			process.getOutputStream().close();
			output = IOUtils.getStreamContent(process.getInputStream(), 512);
			exit = process.waitFor();
		} catch (InterruptedException ignore) {
		} catch (Exception e) {
			if (debug) {
				LOG.log(Level.WARNING, "error when execute '" + pingcmd + "'",
						e);
			}
		} finally {
			try {
				if (exit == 0 && output != null) {
					time = resolver.getPingTime(output);
				}
			} catch (Exception e) {// make sure not throw runtime exception
				if (debug) {
					LOG.log(Level.WARNING, "error when resolving " + output, e);
				}
			} finally {
				if (time == Integer.MAX_VALUE && exit == 0) {
					// means ping success but resolve error,use process time as
					// ping time
					time = (int) TimeUnit.NANOSECONDS.toMillis(System
							.nanoTime() - start);
				}
				if (cdl != null)
					cdl.countDown();
			}
			if (exit == 0) {
				if (debug) {
					LOG.log(Level.INFO, pingcmd + " ok :" + time);
				}
			} else {
				if (debug) {
					LOG.warning(pingcmd + " fail,exit:" + exit + ",errorput:"
							+ output);
				}
			}
		}

	}

	public void destroy() {
		Resources.close(process);
	}

	public String getHost() {
		return host;
	}

	public int getTime() {
		return time;
	}
}