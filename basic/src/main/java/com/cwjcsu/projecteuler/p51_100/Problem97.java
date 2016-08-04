package com.cwjcsu.projecteuler.p51_100;

import java.math.BigInteger;

import com.cwjcsu.projecteuler.util.Util;

public class Problem97 {
	/**
	 * 28433* 2^7830457 +1
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		long a = 28433;
		long b = 7830457;
		long m = (long) Math.pow(10, 10);
		long r = Util.powerMod(BigInteger.valueOf(2), BigInteger.valueOf(b), BigInteger.valueOf(m))
				.mod(BigInteger.valueOf(m)).multiply(BigInteger.valueOf(a % m)).add(BigInteger.ONE)
				.mod(BigInteger.valueOf(m)).longValue();
		System.out.println(r);
	}
}
