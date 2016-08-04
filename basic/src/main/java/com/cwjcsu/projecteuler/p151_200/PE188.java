package com.cwjcsu.projecteuler.p151_200;

import java.util.concurrent.TimeUnit;

import com.cwjcsu.projecteuler.util.Util;

/**
 * FROM:http://blog.dreamshire.com/2009/04/26/project-euler-problem-188-
 * solution/
 * 
 * 
 * (a*a*a) % n = ( (a%n) * (a%n) * (a%n) ) % n
 * 
 * @author atlas
 * @date 2013-4-28
 */
public class PE188 {
	public static void main(String[] args) {
		long s = System.nanoTime();
		System.out.println(hyper_exp(1777, 1855, 100000000));
		System.out.println("time consumed:" + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - s) + " ms");
	}

	/**OK
	 * 
	 * 求 a↑↑k % m
	 * @param a
	 * @param k
	 * @param m
	 * @return
	 */
	static long hyper_exp(long a, long k, long m) {
		long temp = 1;
		while (k > 0) {
			temp = Util.powerMod(a, temp, m);
			k--;
		}
		return temp;
	}

	/**OK
	 * 
	 * 求 a↑↑k % m = a<sup>a↑↑(k-1)</sup> % m
	 */
	static long hyper_exp2(long a, long k, long m) {
		if (k == 1) {
			return a;
		}
		return Util.powerMod(a, hyper_exp2(a, k - 1, m), m);
	}
}
