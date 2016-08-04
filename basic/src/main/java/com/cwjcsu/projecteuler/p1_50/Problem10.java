package com.cwjcsu.projecteuler.p1_50;

import java.math.BigInteger;

import com.cwjcsu.projecteuler.util.Util;

public class Problem10 {
	public static void main(String[] args) {
//		System.out.println(sumOfPrimesBlow(2000000));
		int[] primes= Util.getPrimesBlow(2000000);
		System.out.println(Util.sum(primes));
	}

	// ������2000003
	public static long sumOfPrimesBlow(long e) {
		if (e < 2) {
			return 0;
		}
		BigInteger n = BigInteger.valueOf(2);
		long s = n.longValue();
		long v = 0L;
		while ((v = (n = n.nextProbablePrime()).longValue()) < e) {
			s += v;
		}
		return v;
	}
}
