package com.cwjcsu.learning.trial;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author atlas
 * @date 2013-5-14
 */
public class WatchDogTest {

	private static ScheduledThreadPoolExecutor scheduled = new ScheduledThreadPoolExecutor(
			1);

	static volatile boolean running = true;

	public static void main(String[] args) throws FileNotFoundException {
		if (args.length != 1) {
			System.err.println("Must input timeout");
		}
		int timeout = Integer.parseInt(args[0]);
		final FeedDog fd = new FeedDog();
		final ScheduledFuture<?> future = scheduled.scheduleAtFixedRate(fd, 0,
				timeout * 1000 / 2, TimeUnit.MILLISECONDS);

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				running = false;
				future.cancel(true);
				fd.close();
				wakeup();
			}
		});

		await();
	}

	static void await() {
		synchronized (WatchDogTest.class) {
			try {
				WatchDogTest.class.wait();
			} catch (InterruptedException e) {
			}
		}
	}

	static void wakeup() {
		synchronized (WatchDogTest.class) {
			WatchDogTest.class.notifyAll();
		}
	}

	static class FeedDog implements Runnable {
		File f = new File("/dev/watchdog");
		private FileOutputStream raf;

		public FeedDog() throws FileNotFoundException {
			raf = new FileOutputStream(f);
		}

		@Override
		public void run() {
			if (running) {
				try {
					System.out.println("Feed dog...");
					raf.write('a');
					// raf.getFD().sync();
				} catch (IOException e) {
					e.printStackTrace();
					scheduled.shutdownNow();
					running = false;
					wakeup();
				}
			}
		}

		public void close() {
			try {
				System.out.println("Kill Dog");
				raf.write('V');
				raf.flush();
				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
