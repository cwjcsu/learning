package com.cwjcsu.projecteuler;

import java.util.concurrent.TimeUnit;

/**
 * 
 * @author atlas
 * @date 2013-4-25
 */
public class TestGetDigitsSumOfInteger {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int N = 10000000;
		long t1 = System.nanoTime();
		for (int i = 0; i < N; i++) {
			get1(123456789);
		}
		long t2 = System.nanoTime();
		for (int i = 0; i < N; i++) {
			get2(123456789);
		}
		long t3 = System.nanoTime();

		System.out.println("get1:" + (TimeUnit.NANOSECONDS.toMillis(t2 - t1)));
		System.out.println("get2:" + (TimeUnit.NANOSECONDS.toMillis(t3 - t2)));
	}

	// N times,cost 274 ms avg.
	public static int get1(int x) {
		int s = 0;
		while (x > 0) {
			s += x % 10;
			x /= 10;
		}
		return s;
	}

	// N times,cost 600ms avg.
	public static int get2(int x) {
		String digits = Integer.toString(x);
		int s = 0;
		for (int i = 0; i < digits.length(); i++) {
			s += digits.charAt(i) - '0';
		}
		return s;
	}

}
