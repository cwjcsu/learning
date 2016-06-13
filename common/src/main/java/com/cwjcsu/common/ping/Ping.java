package com.cwjcsu.common.ping;

import com.cwjcsu.common.DateUtil;
import com.cwjcsu.common.OsUtil;
import com.cwjcsu.common.exec.LocalCommandService;
import com.cwjcsu.common.pojo.OsInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;


public class Ping {

	private Ping() {

	}

	private static Executor executor = LocalCommandService.getInstance().getExecutorService();

	private static PingResolver resolver;

	public static synchronized PingResolver getResolver() {
		if (resolver == null) {
			OsInfo osInfo = OsUtil.getOsInfo();
			String os = osInfo.getName();
			if (os.toLowerCase().contains("windows")) {// Windows 2003
				resolver = new WindowsResolver();
			} else if ("linux".equalsIgnoreCase(os)) {
				resolver = new LinuxResolver();
			} else if ("aix".equalsIgnoreCase(os)) {
				resolver = new AixResolver();
			} else if ("Mac OS X".equalsIgnoreCase(os)) {
				resolver = new MacResolver();
			} else {
				throw new RuntimeException("Unsupported operation system '"
						+ os + "' for resolving ping command.");
			}
		}
		return resolver;
	}

	/**
	 * ping hosts,获取它们的ping相应时间(毫秒)
	 * 
	 * @param hosts
	 * @param timeout
	 * @return
	 */
	public static Map<String, Integer> ping(List<String> hosts, long timeout) {
		return ping(hosts, timeout, true);
	}

	/**
	 * ping hosts,获取它们的ping相应时间(毫秒)
	 * 
	 * @param hosts
	 * @param timeout
	 * @return
	 */
	public static Map<String, Integer> ping(List<String> hosts, long timeout,
			boolean debug) {
		Map<String, Integer> pings = new HashMap<String, Integer>();
		CountDownLatch cdl = new CountDownLatch(hosts.size());
		List<Worker> workers = new ArrayList<Worker>();
		PingResolver resolver = getResolver();
		for (String host : hosts) {
			Worker worker = new Worker(host, cdl, resolver, debug);
			workers.add(worker);
			executor.execute(worker);
		}
		try {
			cdl.await(timeout, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
		}
		// time out or all worker is done.
		for (Worker worker : workers) {
			worker.destroy();
		}
		try {
			cdl.await();
		} catch (InterruptedException e) {
			//
		}
		for (Worker worker : workers) {
			pings.put(worker.getHost(), worker.getTime());
		}
		return pings;
	}

	public static int pingQuietly(String host, long timeout) {
		return ping(host, timeout, false);
	}

	public static int ping(String host, long timeout, boolean debug) {
		CountDownLatch cdl = new CountDownLatch(1);
		PingResolver resolver = getResolver();
		Worker worker = new Worker(host, cdl, resolver, debug);
		executor.execute(worker);
		try {
			cdl.await(timeout, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
		}
		worker.destroy();
		try {
			cdl.await();
		} catch (InterruptedException e) {
			//
		}
		return worker.getTime();
	}

	public static int ping(String host, long timeout) {
		return ping(host, timeout, true);
	}

	public static List<PingResult> pingAndSort(List<String> hosts, long timeout) {
		Map<String, Integer> pings = ping(hosts, timeout);
		List<PingResult> sorted = new ArrayList<PingResult>();
		for (Map.Entry<String, Integer> ping : pings.entrySet()) {
			sorted.add(new PingResult(ping.getKey(), ping.getValue()));
		}
		Collections.sort(sorted);
		return sorted;
	}

	public static String pingFastest(List<String> hosts, long timeout) {
		if (hosts.size() > 0) {
			List<PingResult> sorted = pingAndSort(hosts, timeout);
			return sorted.get(0).getHost();
		}
		return null;
	}

	private static volatile boolean running = false;

	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("Please input IP Addresses");
			System.exit(1);
			return;
		}
		final Thread t = Thread.currentThread();
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				running = false;
				t.interrupt();
			}
		});
		PingResolver.LOG.setLevel(Level.SEVERE);
		List<String> hosts = new ArrayList<String>();
		for (String arg : args) {
			hosts.add(arg);
		}
		running = true;
		long start = 0;
		while (running) {
			start = System.currentTimeMillis();
			Map<String, Integer> results = ping(hosts, 3000);
			for (String host : hosts) {
				int time = results.get(host);
				if (time < 5000) {
					System.out.println(DateUtil.formatDate(new Date(),
							"yyyy-MM-dd HH:mm:ss.SSS")
							+ " : ping "
							+ host
							+ " :" + results.get(host) + " ms");
				} else {
					System.out.println(DateUtil.formatDate(new Date(),
							"yyyy-MM-dd HH:mm:ss.SSS")
							+ " : ping "
							+ host
							+ " Unreachable");
				}
			}
			long elapsed = System.currentTimeMillis() - start;
			if (elapsed > 0 && elapsed < 1000) {
				try {
					Thread.sleep(1000 - elapsed);
				} catch (InterruptedException e) {
				}
			}
		}
	}
}
