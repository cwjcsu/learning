/*$Id: $
 --------------------------------------
  Skybility
 ---------------------------------------
  Copyright By Skybility ,All right Reserved
 * author                     date    comment
 * chenweijun@skybility.com  2015年10月19日  Created
 */
package com.cwjcsu.projecteuler.p101_150;

import java.math.BigInteger;

import com.cwjcsu.projecteuler.util.Util;

/**
 * 
 * @author atlas
 *
 */
public class PE134 {

	public static void main0(String[] args) {// brost: OK:18613426663617118
		int L = BigInteger.valueOf(1000000).nextProbablePrime().intValue();// 1000003
		int[] primes = Util.getPrimesBlow(L);// last 2 is 999983,1000003
		long sum = 0;
		for (int i = 2; i <= primes.length - 2; i++) {
			int p1 = primes[i];
			int p2 = primes[i + 1];
			sum += getProductEndWith(p1, p2);
			if (sum < 0) {
				System.err.println("Overflow");
			}
		}
		System.out.println(sum);
	}

	public static void main(String[] args) {
		long s = System.currentTimeMillis();
		int L = BigInteger.valueOf(1000000).nextProbablePrime().intValue();// 1000003
		int[] primes = Util.getPrimesBlow(L);// last 2 is 999983,1000003
		long sum = 0;
		for (int i = 2; i <= primes.length - 2; i++) {
			int p1 = primes[i];
			int p2 = primes[i + 1];
			sum += Util.getProductEndWith(p1, p2);
		}
		System.out.println(sum);
		System.out.println("Time consumed:" + (System.currentTimeMillis() - s) + " ms");
	}
	// findN(999983,1000003) = 666662999983

	/**
	 * get the min integer n which endWiths p1 and can be divided by p2
	 */
	public static long getProductEndWith(int p1, int p2) {
		int size = Util.getSize(p1);
		long n0 = (long) Math.pow(10, size);
		long n = p1 + n0;
		while (true) {
			if (n % p2 == 0) {
				return n;
			}
			n += n0;
		}
	}
}
