package com.cwjcsu.projecteuler.p1_50;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

import com.cwjcsu.projecteuler.util.Util;

public class Problem48 {
	public static void main(String[] args) {// 9110846700
		long s = System.nanoTime();
		 System.out.println(usePowerMod(1000));
//		System.err.println(bruteForce(1000));
		System.out.println("time consumed : " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - s) + " ms");
	}

	static String bruteForce(int n) {
		BigInteger S = BigInteger.ONE;
		BigInteger Max = BigInteger.valueOf(n);
		for (BigInteger i = BigInteger.valueOf(2); i.compareTo(Max) < 0; i = i.add(BigInteger.ONE)) {
			S = S.add(i.pow(i.intValue()));
		}
		String s = S.toString(10);
		return s.substring(s.length() - 10, s.length());
	}

	static String usePowerMod(int n) {
		BigInteger N = BigInteger.valueOf(n);
		BigInteger S = BigInteger.ZERO;
		BigInteger m = BigInteger.valueOf((long) Math.pow(10, 10));
		for (BigInteger i = BigInteger.ONE; i.compareTo(N) <= 0; i = i.add(BigInteger.ONE)) {
			S = S.add(Util.powerMod(i, i, m));
		}
		S = S.mod(m);
		String s = S.toString(10);
		return s;
	}
}
