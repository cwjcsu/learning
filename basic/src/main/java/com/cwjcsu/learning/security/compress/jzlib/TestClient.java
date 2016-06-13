package com.cwjcsu.learning.security.compress.jzlib;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.CountDownLatch;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

import com.cwjcsu.common.ChecksumUtil;
import com.cwjcsu.learning.security.compress.Test;

/**
 * 
 * @author atlas
 * @date 2012-9-7
 */
// 4.3G
public class TestClient extends Test {
	static String file = "/home/atlas/Downloads/QQ2012.exe";

	static String file2 = "/home/atlas/Downloads/QQ2012.exe_2";

	public static void main(String[] args) throws UnknownHostException,
			IOException {
		String md5 = "0cc54ab80b1bc4be02747a2719dfb05d";
		int port = Integer.parseInt("1111");
		// Wait for a connection.
		final Socket s = new Socket("localhost", port);
		long start = System.currentTimeMillis();
		// System.out.println("Listening on port " + port + "...");
		final OutputStream out = compressed ? new DeflaterOutputStream(
				s.getOutputStream()) : s.getOutputStream();
		final InputStream in = compressed ? new InflaterInputStream(
				s.getInputStream()) : s.getInputStream();

		final InputStream fin = new FileInputStream(file);
		final OutputStream fout = new FileOutputStream(file2);

		final CountDownLatch cdl = new CountDownLatch(2);
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					copy(fin, out);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					cdl.countDown();
					try {
						out.close();
						s.shutdownOutput();
					} catch (IOException e) {
					}
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					copy(in, fout);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					cdl.countDown();
					try {
						fout.flush();
						s.shutdownInput();
					} catch (IOException e) {
					}
				}
			}
		});
		t1.start();
		t2.start();
		try {
			cdl.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("Done " + (end - start));
		System.out.println(ChecksumUtil.getFileMD5Checksum(file).equals(
				ChecksumUtil.getFileMD5Checksum(file2)));
	}
}
