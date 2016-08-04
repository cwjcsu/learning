package com.cwjcsu.projecteuler.p1_50;

import java.math.BigInteger;

import com.cwjcsu.projecteuler.util.Util;

public class Problem36 {
	public static void main(String[] args) {
		BigInteger m = BigInteger.valueOf(1000000);
		BigInteger s = BigInteger.ZERO;
		for (BigInteger i = BigInteger.ONE; i.compareTo(m) <= 0; i = i
				.add(BigInteger.ONE)) {
			if (Util.isPalindrome(i.toString(10))
					&& Util.isPalindrome(i.toString(2))) {
				s = s.add(i);
			}
		}
		System.out.println(s);
	}
}
