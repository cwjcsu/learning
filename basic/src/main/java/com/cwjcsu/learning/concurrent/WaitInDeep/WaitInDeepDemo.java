/*$Id: $
 * author                     date    comment
 * cwjcsu@gmail.com  2015年11月4日  Created
 */
package com.cwjcsu.learning.concurrent.WaitInDeep;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author atlas
 *
 */
public class WaitInDeepDemo {

	private boolean ready = false;

	private Object lock = new Object();

	class Job implements Runnable {
		String name = "";
		long waitTime = 2000;

		public Job(String name, long waitTime) {
			super();
			this.name = name;
			this.waitTime = waitTime;
		}

		@Override
		public void run() {
			println(name, "before lock");
			synchronized (lock) {
				println(name, "after lock");
				while (!ready) {
					try {
						long s = System.currentTimeMillis();
						println(name, "start wait " + waitTime + " ms");
						lock.wait(waitTime);
						println(name,
								"after wait "
										+ (System.currentTimeMillis() - s)
										+ " ms");
					} catch (InterruptedException e) {
						println(name, "interrupted");
					}
				}
			}
		}
	}

	private void println(String name, String msg) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		System.out.println(sdf.format(new Date()) + " - [" + name + "] " + msg);
	}

	class SleepJob implements Runnable {
		String name = "";
		long sleepTime = 5000;

		public SleepJob(String name, long sleepTime) {
			super();
			this.name = name;
			this.sleepTime = sleepTime;
		}

		@Override
		public void run() {
			println(name, "before lock");
			synchronized (lock) {
				println(name, "after lock");
				try {
					long s = System.currentTimeMillis();
					println(name, "start sleep " + sleepTime + " ms");
					Thread.sleep(sleepTime);
					println(name, "after sleep "
							+ (System.currentTimeMillis() - s) + " ms");
				} catch (InterruptedException e) {
					println(name, "interrupted");
				}
			}
		}
	}

	public void runDemo() {
		int jobCount = 2;
		List<Thread> jobs = new ArrayList<Thread>();
		for (int i = 0; i < jobCount; i++) {
			Thread t = new Thread(new Job("Job#" + i, 2000));
			jobs.add(t);
			t.start();
		}
		int sleeperCount = 1;
		for (int i = 0; i < sleeperCount; i++) {
			Thread t = new Thread(new SleepJob("Sleeper#" + i, 3000));
			t.start();
		}
		try {
			Thread.sleep(1500);
			//1.5 秒后
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//1.5秒后，打断线程
		for (Thread t : jobs) {
			t.interrupt();
		}
		//job线程会在sleep线程完成以后才会返回wait方法或者抛出InterruptedException异常。
	}

	public static void main(String[] args) {
		new WaitInDeepDemo().runDemo();
	}
}
