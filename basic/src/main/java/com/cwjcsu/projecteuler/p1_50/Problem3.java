package com.cwjcsu.projecteuler.p1_50;

import java.math.BigInteger;

import com.cwjcsu.projecteuler.util.Util;

public class Problem3 {
	//1471
	public static void main(String[] args) {
		long i=600851475143L;
		System.out.println(maxFactor(600851475143L ));
//		System.out.println(i%1471);
	}

	public static long maxFactor(long i) {
		BigInteger curr = BigInteger.valueOf(2);
		long v;
		long prev = 1;
		while ((v = curr.longValue()) < i) {
			if (Util.isFactorOf(v, i)) {
				i /= v;
				prev = v;
			}
			curr = curr.nextProbablePrime();
		}
		return v;
	}
}
