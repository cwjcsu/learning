package com.cwjcsu.projecteuler.p51_100;

import java.util.LinkedList;
import java.util.List;

/**
 * 一个数有多少个数字可以用公式：(int)log10(n)+1,n不可以超过10，因为10^n有n+1个数字 <br>
 * One way to know how many digits a number has is to use the formula: int(log10
 * n) +1. For this problem n cannot be greater than 10 since 10n is always n+1
 * digits long.
 *
 * So we need to create a function such that f(n) = upper limit of n. Using our
 * log function and using 9 as an example we define f(9) as int (1 / (1 – log10
 * 9)) or 21 such that 9n < 10n-1. Thus 9n is n digits long when n ≤ 21. We
 * repeat this same process for 1 through 8 and sum the results for an answer.
 *
 * @author Sunny
 *
 */
public class Problem63 {
	/**
	 * I:1,r:1,n:1 I:2,r:2,n:1 I:3,r:3,n:1 I:4,r:4,n:1 I:5,r:5,n:1 I:6,r:6,n:1
	 * I:7,r:7,n:1 I:8,r:8,n:1 I:9,r:9,n:1 I:16,r:4,n:2 I:25,r:5,n:2
	 * I:36,r:6,n:2 I:49,r:7,n:2 I:64,r:8,n:2 I:81,r:9,n:2 I:125,r:5,n:3
	 * I:216,r:6,n:3 I:1296,r:6,n:4 I:2401,r:7,n:4 I:4096,r:8,n:4 I:6561,r:9,n:4
	 * I:16777216,r:8,n:8 I:43046721,r:9,n:8
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(Math.log10(10));
		do5();
		// System.out.println(Math.pow(999d, 1d / 3));
	}

	static void do5() {
		long s = 0;
		for (int i = 1; i < 10; i++) {
			s += (int) (1 / (1 - Math.log10(i)));
		}
		System.out.println(s);
	}

	static void do4() {
		long count = 0;
		for (int n = 1;; n++) {
			double min = Math.pow(Math.pow(10, n - 1), 1d / n);
			double max = Math.pow(Math.pow(10, n) - 1, 1d / n);
			long maxI = (long) max;
			long minI = min == (long) min ? (long) min : ((long) min + 1);
			if (minI > maxI) {
				break;
			}
			count += maxI - minI + 1;
			for (long i = minI; i < maxI; i++) {
				System.out.println("a^n=i:" + i + "^" + n + "="
						+ (long) Math.pow(i, n));
			}
		}
		System.out.println("Count:" + count);
	}

	// [a,b,i]
	static long[] next(long a, long b, long i) {
		long[] ret = new long[3];
		double i1 = Math.pow(a + 1, b);
		double i2 = Math.pow(a, b + 1);
		double r3 = Math.pow(i, 1d / (b + 1));
		long a1 = (r3 == (long) r3) ? (long) r3 : (long) (r3 + 1);
		long i3 = (long) Math.pow(a1, b + 1);

		// chooseI1: ret[0] = a + 1;
		// ret[1] = b;
		// ret[2] = (long) i1;
		//
		// chooseI2: ret[0] = a;
		// ret[1] = b + 1;
		// ret[2] = (long) i2;
		//
		// chooseI3: ret[0] = a1;
		// ret[1] = b + 1;
		// ret[2] = i3;

		if (i3 <= i) {
			if (i1 < i2) {
				ret[0] = a + 1;
				ret[1] = b;
				ret[2] = (long) i1;
			} else {
				ret[0] = a;
				ret[1] = b + 1;
				ret[2] = (long) i2;
			}
		} else if (i3 > i && i3 < (long) i1) {
			ret[0] = a1;
			ret[1] = b + 1;
			ret[2] = i3;
		} else if (i3 > i1) {
			ret[0] = a + 1;
			ret[1] = b;
			ret[2] = (long) i1;
		}
		return ret;
	}

	static void do3() {
		// check a^b;
		long a = 4, b = 2, i = 16;
		long bit;
		for (; i < Long.MAX_VALUE;) {
			bit = getBit(i);
			if (bit == b) {
				System.out.println("I:" + i + ",a:" + a + ",b:" + b);
			}
			if (i == 25) {
				int cccc = 0;
			}
			long[] ret = next(a, b, i);
			a = ret[0];
			b = ret[1];
			i = ret[2];
		}
	}

	static void do2() {
		List<Long> rs = new LinkedList<Long>();
		long bit = 1;
		double r;

		for (long i = 1; i < Long.MAX_VALUE;) {
			bit = getBit(i);
			r = Math.pow(i, 1d / bit);
			if (r == (long) r) {
				rs.add(new Long((long) r));
				System.out.println("I:" + i + ",r:" + (long) r + ",n:" + bit);

			}
			{
				i++;
			}

		}
	}

	static int getBit(long i) {
		return String.valueOf(i).length();
	}

	static void do1() {
		List<Long> rs = new LinkedList<Long>();
		long up = 10;
		long bit = 1;
		double r;
		boolean got = false;
		long nextI = 0;
		for (long i = 10; i < Long.MAX_VALUE;) {
			bit = getBit(i);
			r = Math.pow(i, 1d / bit);
			if (r == (long) r) {
				rs.add(new Long((long) r));
				System.out.println("I:" + i + ",r:" + (long) r + ",n:" + bit);
				if (r == 9) {
					int a = 0;
				}
				got = true;
			}
			if (got) {
				long a1 = (long) Math.pow(r, bit + 1);
				long a2 = (long) Math.pow(r + 1, bit);
				i = a1 < a2 ? a1 : a2;
				nextI = a1 > a2 ? a1 : a2;
			} else {
				i++;
			}

		}
		System.out.println("COUNT:" + rs.size());

	}
}
