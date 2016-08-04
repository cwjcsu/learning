package com.cwjcsu.projecteuler.p101_150;

import java.math.BigInteger;

import com.cwjcsu.projecteuler.util.Util;

/**
 * 
 * see pe 108
 */
public class PE110 {
	static BigInteger sum = BigInteger.ONE;
	static int[] primes;

	// 9350130049860600
	public static void main(String[] args) {
		int N = 4000000;
		double x = 0;
		System.out.println(x = (Math.log10(N * 2) / Math.log10(3)));
		System.out.println(Math.pow(3, 15));
		primes = Util.getPrimesBlow(1000);
		sum = BigInteger.ONE;
		for (int i = 0; i < 15; i++) {
			sum = sum.multiply(BigInteger.valueOf(primes[i]));
		}
		System.out.println(sum);
		dfs(0, 20, 1, BigInteger.ONE);
		System.out.println(sum);

	}

	// why?
	static void dfs(int nowp, long max, long nsol, BigInteger val) {
		if (sum.compareTo(val) < 0)
			return;
		if (nsol >= 8000000) {
			sum = val;
			return;
		}
		long i, j;
		for (i = max; i > 0; i--) {
			BigInteger next = val;
			for (j = 0; j < i; j++)
				next = next.multiply(BigInteger.valueOf(primes[nowp]));
			dfs(nowp + 1, i, nsol * (i * 2 + 1), next);
		}
	}
}
