package com.cwjcsu.learning.trial;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * use root to run this test
 * 
 * @author atlas
 * @date 2013-9-27
 */
public class ClockTest2 {

	private class SleepThread extends Thread {
		// seconds
		private int sleepTime = 5;

		public SleepThread(int sleepTime) {
			super();
			this.sleepTime = sleepTime;
		}

		public void run() {
			System.out.println("test sleep " + sleepTime
					+ " seconds,sleeping...");
			Process process = null;
			try {
				process = Runtime.getRuntime().exec("sleep " + sleepTime);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	private class ChangedTimeThread extends Thread {
		// in seconds
		private int before = 1;
		// in seconds
		private int deltaTime = -10;

		public ChangedTimeThread(int before, int deltaTime) {
			super();
			this.before = before;
			this.deltaTime = deltaTime;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(before);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			Calendar now = Calendar.getInstance();
			now.add(Calendar.SECOND, deltaTime);
			String to = sdf.format(now.getTime());
			System.out.println("now is " + sdf.format(new Date())
					+ ", changed " + deltaTime + " seconds to " + to);
			// use root to run this
			Process p = null;
			try {
				p = Runtime.getRuntime().exec(getCommand(to));
				if (p.waitFor() != 0) {
					System.err.println("Error execute date -s ...");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (p != null) {
					p.destroy();
				}
			}
		}
	}

	public String getCommand(String time) {
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().contains("windows")) {
			return "cmd /c time " + time;
		} else {
			return "date -s " + time;
		}
	}

	public void testChanged(int seconds) {
		if (seconds > 0) {
			System.err.println("test change time to future:" + (seconds)
					+ " seconds later.");
		} else {
			System.err.println("test change time to histroy:" + (-seconds)
					+ " seconds earlier.");
		}
		long start = System.nanoTime();
		Thread sleepThread = new SleepThread(5);
		Thread changeThread = new ChangedTimeThread(1, seconds);
		sleepThread.start();
		changeThread.start();
		try {
			sleepThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long end = System.nanoTime();
		System.out.println("sleeping thread consume:"
				+ TimeUnit.NANOSECONDS.toSeconds(end - start) + " seconds");
	}

	// use root to run this
	public static void main(String[] args) {
		ClockTest2 t = new ClockTest2();
		int time = -5;
		if (args.length > 0) {
			time = Integer.valueOf(args[0]);
		}
		t.testChanged(time);
	}
}
