package com.cwjcsu.projecteuler.p1_50;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import com.cwjcsu.projecteuler.util.Util;

public class Problem14 {
	static int[] store = new int[10000];

	public static void main(String[] args) {
		// getStartingNumberWhenChainLogest(1000000);
		System.out.println(getLen(393215));
		long Max = 1, N = 13;
		for (int i = 1, n = 393215; n < 1000000; n = 2 * i + 1) {

			int m = getLen(n);

			if (m > Max) {
				Max = m;
				N = n;
			}
			i++;
		}
		System.out.println(N + "," + Max);
	}

	static int L = 1024 * 1024 * 16;
	static Map<Integer, Integer> map = new HashMap<Integer, Integer>(L * 2);

	// 393215
	static int getLen(long n) {
		BigInteger N = BigInteger.valueOf(n);
		int len = 1;
		long hold = n;
		while (N.compareTo(BigInteger.ONE) != 0) {
			N = next(N);
			len++;
		}
		return len;
	}

	// static void getStartingNumberWhenChainLogest(int e) {
	//
	// int Length = 0;
	// int E = 0;
	// for (int s = 11; s <= e; s = 2 * s + 1) {
	// int tmp = getChianLength(s);
	// if (s == 393215) {
	// int a = 0;
	// }
	// System.out.println(s + "," + tmp);
	// if (tmp > Length) {
	// Length = tmp;
	// E = s;
	// }
	// }
	// System.out.println("Starting Number:" + E + ",Chain length:" + Length);
	// }
	static BigInteger TWO = BigInteger.valueOf(2);

	static BigInteger next(BigInteger i) {
		if (Util.isEven(i.intValue())) {
			return i.shiftRight(1);
		} else {
			return i.add(i.shiftLeft(1)).add(BigInteger.ONE);
		}
	}

	static long next(long c) {
		if (Util.isEven(c)) {
			return c >> 1;
		} else {
			return (c << 2) + c + 1;
		}
	}
}
