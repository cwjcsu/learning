package com.cwjcsu.projecteuler.p51_100;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import com.cwjcsu.projecteuler.util.Util;

/**
 * 55374, ok
 * 
 * @author sunny
 * 
 */
public class P78 {

	static Map<Integer, BigInteger> dict = new HashMap<Integer, BigInteger>();

	public static void main(String[] args) {
		int n = 2;
		// System.out.println(p(55374));
		BigInteger L = BigInteger.valueOf(1000000);
		while (true) {
			if (n % 10000 == 0) {
				System.out.println(n + "," + p(n));
			}
			if (p(n).mod(L).equals(BigInteger.ZERO)) {
				System.out.println("got" + n);
				break;
			}
			n++;
		}
	}

	public static BigInteger p(int n) {
		if (n == 0) {
			return BigInteger.ONE;
		} else if (n < 0) {
			return BigInteger.ZERO;
		}
		BigInteger got = dict.get(n);
		if (got != null) {
			return got;
		}
		int k = 1;
		BigInteger count = BigInteger.ZERO;
		while (true) {
			int n1 = n - (3 * k * k - k) / 2;
			int n2 = n - (3 * k * k + k) / 2;
			BigInteger r = Util.isEven(k - 1) ? BigInteger.ONE : BigInteger
					.valueOf(-1);
			count = count.add((p(n1).add(p(n2)).multiply(r)));
			if (n2 <= 0) {
				dict.put(n, count);
				return count;
			}
			k++;
		}
	}
}
