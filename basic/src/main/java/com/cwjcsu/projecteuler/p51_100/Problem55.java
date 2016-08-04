package com.cwjcsu.projecteuler.p51_100;

import java.math.BigInteger;

import com.cwjcsu.projecteuler.util.Util;

public class Problem55 {
	public static void main(String[] args) {
		int s = 0;
		for (int i = 0; i < 10000; i++) {
			if (checkLychrel(BigInteger.valueOf(i))) {
				s++;
			}
		}
		System.out.println(s);
	}

	static boolean checkLychrel(BigInteger I) {
		int c = 0;
		while (c < 50) {
			c++;
			I = reverseAndAdd(I);
			if (Util.isPalindrome(I.toString(10))) {
				break;
			}
		}
		return c >= 50;
	}

	static BigInteger reverseAndAdd(BigInteger I) {
		String s = new StringBuffer(I.toString(10)).reverse().toString();
		return I.add(new BigInteger(s, 10));
	}

}
