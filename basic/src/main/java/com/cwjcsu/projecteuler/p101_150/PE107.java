package com.cwjcsu.projecteuler.p101_150;

import com.cwjcsu.projecteuler.util.Util;

/**
 * 
 * @author atlas
 * @date 2013-4-28
 */
public class PE107 {

	static int N = 1000000;

	// last 2 primes around N is 999983,1000003
	static int[] primes = Util.getPrimesBlow(N + 3);

	public static void main(String[] args) {
		long s = 0;
		for (int i = 2; i < primes.length - 1; i++) {
			int p1 = primes[i];
			int p2 = primes[i + 1];
			int m = Util.POWER_OF_TEN[Util.getSize(p1)];
			for (int x = 2;; x++) {
				long n = x * p2;
				if (n % m == p1) {
					s += n;
					break;
				}
			}
		}
		System.out.println(s);
	}
}
