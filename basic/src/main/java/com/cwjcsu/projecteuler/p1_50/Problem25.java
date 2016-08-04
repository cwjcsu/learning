package com.cwjcsu.projecteuler.p1_50;

import java.math.BigInteger;

public class Problem25 {
	public static void main(String[] args) {
		BigInteger current = BigInteger.valueOf(1);
		BigInteger pre = BigInteger.valueOf(1);
		BigInteger tmp;
		String s;
		int result = 2;

		while (true) {
			tmp = current.add(pre);
			pre = current;
			current = tmp;
			s = current.toString();
			result++;

			if (s.length() >= 1000) {
				break;
			}
		}

		System.out.println(result);
	}
}
