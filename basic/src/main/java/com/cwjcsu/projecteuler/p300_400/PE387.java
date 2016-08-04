package com.cwjcsu.projecteuler.p300_400;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

import com.cwjcsu.projecteuler.util.Util;

/**
 * Created by Atlas on 2015/10/11.
 */
public class PE387 {
	static long N = (long) Math.pow(10, 14);

	public static void main(String[] args) {// 696067597313468
		// System.out.println(sumHarshadNumberBelow(N));
		long s = System.nanoTime();
		System.out.println(sumHarshadNumberBelow());
		System.out.println("Time consumed : " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - s) + " ms");
		// System.out.println(sumHarshadNumberBelow2(10000));
	}

	/**
	 * 按照素数遍历，fail：
	 * 
	 * @param N
	 * @param h
	 * @return
	 */
	public static long sumHarshadNumberBelow(long N) {
		long sum = 0;
		for (long i = 11; i < N; i = BigInteger.valueOf(i).nextProbablePrime().longValue()) {
			if (Util.isStrongRightTruncatableHarshadNumber(i / 10)) {
				sum += i;
			}
		}
		return sum;
	}

	public static long sumHarshadNumberBelow() {
		long sum = 0;
		for (int i = 1; i < 9; i++) {
			sum += sumHarshadNumber(i, i, false);
		}
		return sum;
	}

	/**
	 * 按照 Truncatable HarshadNumber遍历：
	 * 
	 * @param n
	 *            a Right Truncatable HarshadNumber
	 * @param ds
	 *            sum of digits of n
	 * @param strong
	 *            n is strong HarshadNumber
	 * @return 以n为前缀的所有StrongRightTruncatableHarshad素数之和
	 */
	public static long sumHarshadNumber(long n, int ds, boolean strong) {
		n = n * 10;
		if (n >= N) {
			return 0;
		}
		long sum = 0;
		for (int i = 0; i <= 9; i++) {
			n += i; // new n
			ds += i;// new ds
			if (strong && Util.isProbablePrime(n, 100)) {// new n is prime
				sum += n;
			}
			if (n % ds == 0) { // new n is a Right Truncatable
								// HarshadNumber,recursive
				sum += sumHarshadNumber(n, ds, Util.isProbablePrime(n / ds, 100));
			}
			n -= i;// restore n
			ds -= i;// restore ds
		}
		return sum;
	}

}
