package com.cwjcsu.projecteuler.p51_100;

import java.math.BigInteger;

import com.cwjcsu.projecteuler.util.Util;

public class Problem66 {
	public static void main(String[] args) {
		// System.out.println(pell(61));
		// System.out.println(pell1(61));
		// solve();
		// for (int i = 10; i < 100; i++) {
		// System.out.println("i,pell(i)" + i + "," + pell(i));
		// }

		// ok
		 solve1();

//		pell1(661);

	}

	/**
	 * ok
	 */
	static void solve1() {
		int N = 1000;
		int[] primes = Util.getPrimesBlow(N);
		BigInteger t = null;
		BigInteger Max = BigInteger.ZERO;
		long Di = 0;
		for (int i = 0; i < primes.length; i++) {
			t = Util.pell(primes[i]);
			if (t.compareTo(Max) > 0) {
				Max = t;
				Di = primes[i];
			}
			System.out.println("i,pell(i)" + primes[i] + "," + t);
		}
		System.out.println("X,Di=" + Max + "," + Di);
	}

	static void solve() {
		int N = 1000;
		int[] primes = Util.getPrimesBlow(N);
		long Max = 0;
		long Di = 0;
		for (int i = 0; i < primes.length; i++) {
			long t = pell(primes[i]);
			if (t > Max) {
				Max = t;
				Di = primes[i];
			}
			System.out.println("i,pell(i)" + primes[i] + "," + t);
		}
		System.out.println("X,Di=" + Max + "," + Di);
	}

	public static BigInteger pell1(int d) {
		BigInteger p = BigInteger.ONE, k = BigInteger.ONE, x1 = BigInteger.ONE, y = BigInteger.ZERO;
		double sd = Math.sqrt(d);
		BigInteger x = null;
		int P = 0, K = 0, X1 = 0, Y = 0;
		while (k.compareTo(BigInteger.ONE) != 0
				|| y.compareTo(BigInteger.ZERO) == 0) {
			p = k.multiply((p.divide(k).add(BigInteger.ONE))).add(p.negate());
			BigInteger t = p.add(BigInteger.valueOf(-(long) sd)).divide(k)
					.multiply(k);
			p = p.add(t.negate());
			x = (p.multiply(x1).add(y.multiply(BigInteger.valueOf(d))))
					.divide(k.abs());
			y = (p.multiply(y).add(x1)).divide(k.abs());
			k = (p.multiply(p).add(BigInteger.valueOf(-d))).divide(k);
			x1 = x;
			if (p.add(BigInteger.valueOf(Long.MAX_VALUE / 2).negate())
					.compareTo(BigInteger.ZERO) > 0) {
				P++;
			}
			if (k.add(BigInteger.valueOf(Long.MAX_VALUE / 2).negate())
					.compareTo(BigInteger.ZERO) > 0) {
				K++;
			}
			if (x1.add(BigInteger.valueOf(Long.MAX_VALUE / 2).negate())
					.compareTo(BigInteger.ZERO) > 0) {
				X1++;
			}
			if (y.add(BigInteger.valueOf(Long.MAX_VALUE / 2).negate())
					.compareTo(BigInteger.ZERO) > 0) {
				Y++;
			}
		}
		System.out.println("p,k,x1,y:" + P + "," + K + "," + X1 + "," + Y);
		return x;
	}

 

	/**
	 * if d = 181,overflow,
	 * 
	 *
	 * @param d
	 * @return
	 */
	public static long pell(int d) {
		long p = 1, k = 1, x1 = 1, y = 0;
		double sd = Math.sqrt(d);
		long x = 0;
		while (k != 1 || y == 0) {
			p = k * (p / k + 1) - p;
			p = p - (int) ((p - sd) / k) * k;

			x = (p * x1 + d * y) / Math.abs(k);
			y = (p * y + x1) / Math.abs(k);
			k = (p * p - d) / k;
			x1 = x;
		}
		return x;
	}
}
// class Triple {
// double
// }