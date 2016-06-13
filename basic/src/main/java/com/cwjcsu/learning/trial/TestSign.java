package com.cwjcsu.learning.trial;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 
 * @author atlas
 * @date 2013-9-6
 */
public class TestSign {

	static Object lock = new Object();

	public static void startStdRedirector() throws IOException {
		File f = new File("TestSign.log");
		if (!f.exists()) {
			f.createNewFile();
		}
		FileOutputStream out = new FileOutputStream(f);
		PrintStream ps = new PrintStream(new BufferedOutputStream(out), true);
		System.setOut(ps);
		System.setErr(ps);
	}

	public static void main(String[] args) {
		try {
			startStdRedirector();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("TestSign started...");
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				synchronized (lock) {
					System.out.println("On ShutdownHook...");
					synchronized (lock) {
						lock.notifyAll();
					}
				}
			}
		});
		synchronized (lock) {
			try {
				lock.wait();
			} catch (InterruptedException e) {
			}
			System.out.println("TestSign Stopped...");
		}
	}
}
