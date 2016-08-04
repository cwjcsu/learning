package com.cwjcsu.projecteuler.p1_50;

import com.cwjcsu.projecteuler.util.Util;

public class Problem41 {
	/*
	 * Nine digit pandigital numbers 1+2+3+4+5+6+7+8+9=45 which is divisible by
	 * 3 and Eight digit pandigital numbers 1+2+3+4+5+6+7+8=36 which is
	 * divisible by 3
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] primes = Util.getPrimesBlow(7654321);
		int P = 0;
		for (int i = 0; i < primes.length; i++) {
			if (Util.isPandigital1_n(primes[i], Util.getSize(primes[i]))) {
				P = primes[i];
			}
		}
		System.out.println(P);
	}
}
