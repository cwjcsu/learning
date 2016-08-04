package com.cwjcsu.projecteuler.p1_50;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import com.cwjcsu.projecteuler.util.Util;

public class Problem12 {

	static int[] primes = Util.getPrimesBlow(10000000);

	// 2��499�η�
	public static void main(String[] args) {
		//
		// BigInteger i = BigInteger.valueOf(2);
		// System.out.println(i.pow(499).toString(10));
		// System.out.println(getDivisorCount(104748));
		// System.out.println();
		// Util.print(" ", Util.getPrimeDivisors(500, primes));
		// do2();
		// System.out.println(getDivisorCount(28));
		// for (int i = 0; i < 1000; i++) {
		// System.out.println(primes[i]);
		// }
		do1();
	}

	static boolean isTriangleNumber(BigInteger I) {
		double d = I.multiply(BigInteger.valueOf(2)).doubleValue();
		long dn = (long) Math.sqrt(d);
		BigInteger n = BigInteger.valueOf(dn);
		BigInteger n$1 = n.add(BigInteger.ONE);
		return n.multiply(n$1).equals(I);
	}

	static void do2() {
		int[][] d = { { 2, 2, 5, 5, 5 }, { 4, 5, 5, 5 }, { 2, 2, 25, 5 },
				{ 10, 2, 5, 5 }, { 20, 5, 5 }, { 50, 2, 5 }, { 125, 2, 2 },
				{ 125, 4 }, { 250, 2 }, { 50, 10 }, { 500 }

		};
		for (int i = 0; i < d.length; i++) {
			BigInteger I = BigInteger.ONE;
			for (int j = 0; j < d[i].length; j++) {
				int p = primes[j];
				I = I.multiply(BigInteger.valueOf(p).pow(d[i][j] - 1));
			}
			if (isTriangleNumber(I)) {
				System.out.println("OOOK:" + I.toString(10));
			}
			System.out.println(I.toString(10));
		}

	}

	// i=12375,76576500
	static void do1() {
		for (int i = 2;; i++) {
			int n= i*(i+1)/2;
			int c = getDivisorCount( n);
			System.out.println(i + ":" + c);
			if (c >= 500) {
				System.out.println("==" + n);
				break;
			}
		}
	}

	static int getDivisorCount(int n) {
		Map<Integer, Integer> pp = new HashMap<Integer, Integer>(2048);
		int p = 0;
		int s = 1;
		for (int i = 0; i < primes.length && (p = primes[i]) <= n;) {
			if (n % p == 0) {
				Integer c = pp.get(p);
				if (c == null) {
					pp.put(p, 1);
				} else {
					pp.put(p, c + 1);
				}
				n /= p;
			} else {
				i++;
			}
		}
		for (Integer prim : pp.keySet()) {
			s *= (pp.get(prim) + 1);
		}
		return s;
	}
}
