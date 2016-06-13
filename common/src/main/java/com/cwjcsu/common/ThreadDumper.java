package com.cwjcsu.common;

import java.lang.management.ManagementFactory;
import java.lang.management.MonitorInfo;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 打印线程栈的工具类
 * @author atlas
 * @date 2013-10-7
 */
public class ThreadDumper {

	/**
	 * dump当前JVM的线程栈，是jstack和jconsole关于线程栈的功能综合体，即会打印死锁信息
	 * 
	 * @return
	 */
	public static String dump() {
		RuntimeMXBean rmb = ManagementFactory.getRuntimeMXBean();
		ThreadMXBean rtb = ManagementFactory.getThreadMXBean();
		boolean lockedMonitors = rtb.isObjectMonitorUsageSupported();
		boolean lockedSynchronizers = rtb.isSynchronizerUsageSupported();
		Date now = new Date();
		ThreadInfo[] tis = rtb.dumpAllThreads(lockedMonitors,
				lockedSynchronizers);
		HashMap<Long, String> nameCache = new HashMap<Long, String>();
		for (ThreadInfo ti : tis) {
			nameCache.put(ti.getLockOwnerId(), ti.getThreadName());
		}
		StringBuilder sb = new StringBuilder();
		sb.append(DateUtil.formatDate(now, "yyyy-MM-dd HH:mm:ss.SSS"));
		sb.append(cr);
		sb.append(_.$("Full thread dump {} {} {}", rmb.getVmName(),
				rmb.getVmVendor(), rmb.getVmVersion()));
		sb.append(cr);
		sb.append(cr);
		List<Long[]> ids = getDeadlockedThreadIds(rtb);
		if (ids != null && ids.size() > 0) {
			for (int i = 0; i < ids.size(); i++) {
				Long[] circle = ids.get(i);
				sb.append("deadlock #" + i + ":");
				for (int j = 0; j < circle.length; j++) {
					Long id = circle[j];
					sb.append(nameCache.get(id));
					if (j < circle.length - 1) {
						sb.append(" - ");
					}
				}
				sb.append(cr);
			}
			sb.append(cr);
		} else {
			sb.append("NO deadlock detected");
			sb.append(cr);
			sb.append(cr);
		}
		for (ThreadInfo ti : tis) {
			MonitorInfo[] monitors = null;
			if (ti != null) {
				monitors = ti.getLockedMonitors();
				if (ti.getLockName() == null) {
					sb.append(_.$("\"{}\" with id {},state: {}", ti
							.getThreadName(), ti.getThreadId(), ti
							.getThreadState().toString()));
				} else if (ti.getLockOwnerName() == null) {
					sb.append(_.$("\"{}\" with id {},state:{} on {} ", ti
							.getThreadName(), ti.getThreadId(), ti
							.getThreadState().toString(), ti.getLockName()));
				} else {
					sb.append(_.$(
							"\"{}\" with id {},state:{} on {} owned by {} ", ti
									.getThreadName(), ti.getThreadId(), ti
									.getThreadState().toString(), ti
									.getLockName(), ti.getLockOwnerName()));
				}
				sb.append(_.$(",blocked:{},waited:{}", ti.getBlockedCount(),
						ti.getWaitedCount()));
				sb.append(cr);
				int index = 0;
				for (StackTraceElement e : ti.getStackTrace()) {
					sb.append("\t");
					sb.append(e.toString());
					sb.append(cr);
					if (monitors != null) {
						for (MonitorInfo mi : monitors) {
							if (mi.getLockedStackDepth() == index) {
								sb.append("\t");
								sb.append(_.$("- locked {}", mi.toString()));
								sb.append(cr);
							}
						}
					}
					index++;
				}
			}
			sb.append(cr);
		}
		return sb.toString();
	}

	private static final String cr = System.getProperty("line.separator");

	// Return deadlocked threads or null
	private static List<Long[]> getDeadlockedThreadIds(ThreadMXBean threadMBean) {
		long[] ids = threadMBean.findDeadlockedThreads();
		if (ids == null) {
			return null;
		}
		ThreadInfo[] infos = threadMBean.getThreadInfo(ids, Integer.MAX_VALUE);

		List<Long[]> dcycles = new ArrayList<Long[]>();
		List<Long> cycle = new ArrayList<Long>();

		// keep track of which thread is visited
		// one thread can only be in one cycle
		boolean[] visited = new boolean[ids.length];

		int deadlockedThread = -1; // Index into arrays
		while (true) {
			if (deadlockedThread < 0) {
				if (cycle.size() > 0) {
					// a cycle found
					dcycles.add(cycle.toArray(new Long[0]));
					cycle = new ArrayList<Long>();
				}
				// start a new cycle from a non-visited thread
				for (int j = 0; j < ids.length; j++) {
					if (!visited[j]) {
						deadlockedThread = j;
						visited[j] = true;
						break;
					}
				}
				if (deadlockedThread < 0) {
					// done
					break;
				}
			}

			cycle.add(ids[deadlockedThread]);
			long nextThreadId = infos[deadlockedThread].getLockOwnerId();
			for (int j = 0; j < ids.length; j++) {
				ThreadInfo ti = infos[j];
				if (ti.getThreadId() == nextThreadId) {
					if (visited[j]) {
						deadlockedThread = -1;
					} else {
						deadlockedThread = j;
						visited[j] = true;
					}
					break;
				}
			}
		}
		return dcycles;
	}
}
