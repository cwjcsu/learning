package com.cwjcsu.projecteuler.p1_50;

import java.math.BigInteger;
/**
 * 137846528820
 * @author Sunny
 *
 */
public class Problem15 {
	static int steps = 0;

	public static void main(String[] args) {
		do2();
	}

	static void do2() {
		System.out.println(A(40, 40)
				.divide(jiecheng(20).multiply(jiecheng(20))));
	}

	static void do1() {
		next(0, 0);
		System.err.println(steps);
	}

	/*
	 * ------>x | | | y
	 */
	static void next(int x, int y) {
		if (x == 20 && y == 20) {
			steps++;
			return;
		}
		if (x < 20)
			next(x + 1, y);
		if (y < 20)
			next(x, y + 1);

	}

	/**
	 * Ӧ���� A<sup><sub>m+n</sub>m+n</sup>/(m!*n!)
	 * 
	 * @param e
	 * @return
	 */
	static int getRouter(int m, int n) {
		return 0;
	}

	/*
	 * m>=n
	 */
	static BigInteger A(long m, long n) {
		BigInteger s = BigInteger.ONE;
		n = m - n + 1;
		while (m >= n) {
			s = s.multiply(BigInteger.valueOf(m--));
		}
		return s;
	}

	static BigInteger jiecheng(long e) {
		BigInteger s = BigInteger.ONE;
		while (e > 1) {
			s = s.multiply(BigInteger.valueOf(e--));
		}
		return s;
	}
}
