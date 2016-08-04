package com.cwjcsu.projecteuler.p101_150;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.cwjcsu.projecteuler.util.Tick;
import com.cwjcsu.projecteuler.util.Util;

/**
 *  248155780267521=63<sup>8</sup>
 * 
 * @author atlas
 * @date 2013-4-27
 */
public class PE119 {
	private static final int INDEX = 30; // 1-based

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		method2();
		// method3();
	}

	/**
	 * check n<sup>k</sup>
	 * ����P=n<sup>k</sup>=a<sub>1</sub>a<sub>2</sub>...a<sub>L
	 * </sub>����ôn=a1+a2+...+al less than 9L�� ��һ��k����n��2��ʼ��1���ԣ���n>9Lʱ��break����k����1
	 */
	public static void method2() {
		Tick t = new Tick();
		List<Long> candidates = new ArrayList<Long>();
		for (int k = 2;; k++) {
			for (int n = 2;; n++) {
				long x = Util.power(n, k);
				if (x>=10 && isDigitsPowerSum(x) && !candidates.contains(x)) {
					candidates.add(x);
					if (candidates.size() >= INDEX) {
						Collections.sort(candidates);
						System.out.println(candidates.get(INDEX - 1));
						System.out.println(t.elapsedTime());
						return;
					}
				}
				if (n > Util.size(x) * 9)
					break;
			}
		}
	}

	/**
	 * use long instead of BigInteger.
	 */
	public static void method3() {
		// OK 11ms
		Tick t = new Tick();
		for (int limit = 1;; limit <<= 8) {
			List<Long> candidates = new ArrayList<Long>();
			for (int k = 2; 1 << k < limit; k++) {
				int n = 2;
				while (true) {
					long pow = (long) Math.pow(n, k);
					if (pow >= limit && Util.size(pow) * 9 < n)
						break;
					if (pow >= 10 && !candidates.contains(pow)
							&& isDigitsPowerSum(pow))
						candidates.add(pow);
					n++;
				}
			}
			if (candidates.size() >= INDEX) {
				Collections.sort(candidates);
				System.out.println(candidates.get(INDEX - 1).toString());
				System.out.println(limit);
				break;
			}
		}
		System.out.println(t.elapsedTime());
	}

	public static void method1() {
		// OK
		// 191 ms
		// https://github.com/nayuki/Project-Euler-solutions/blob/master/p119.java
		Tick t = new Tick();
		for (BigInteger limit = BigInteger.ONE;; limit = limit.shiftLeft(8)) {
			List<BigInteger> candidates = new ArrayList<BigInteger>();
			for (int k = 2; BigInteger.valueOf(2).pow(k).compareTo(limit) < 0; k++) {
				BigInteger n = BigInteger.valueOf(2);
				while (true) {
					BigInteger pow = n.pow(k);
					if (pow.compareTo(limit) >= 0
							&& BigInteger.valueOf(pow.toString().length() * 9)
									.compareTo(n) < 0)
						break;

					if (pow.compareTo(BigInteger.TEN) >= 0
							&& !candidates.contains(pow)
							&& isDigitSumPower(pow))
						candidates.add(pow);
					n = n.add(BigInteger.ONE);
				}
			}
			if (candidates.size() >= INDEX) {
				Collections.sort(candidates);
				System.out.println(candidates.get(INDEX - 1).toString());
				System.out.println(limit);
				break;
			}
		}

		System.out.println(t.elapsedTime());
	}

	static boolean isDigitsPowerSum(long x) {
		int s = digitSum(x);
		if (s == 1)
			return false;
		long pow = s;
		//pow may overflow
		while (pow < x && pow > 0) {
			pow *= s;
		}
		return pow == x;
	}

	static int digitSum(long x) {
		int s = 0;
		while (x > 0) {
			s += x % 10;
			x /= 10;
		}
		return s;
	}

	private static boolean isDigitSumPower(BigInteger x) {
		int digitSum = digitSum(x);
		if (digitSum == 1) // Powers of 10 are never a power of 1
			return false;

		BigInteger base = BigInteger.valueOf(digitSum);
		BigInteger pow = base;
		while (pow.compareTo(x) < 0)
			pow = pow.multiply(base);
		return pow.compareTo(x) == 0;
	}

	private static int digitSum(BigInteger x) {
		if (x.signum() < 1)
			throw new IllegalArgumentException("Only for positive integers");
		int sum = 0;
		for (char c : x.toString().toCharArray())
			sum += c - '0';
		return sum;
	}

}
