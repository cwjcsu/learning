package com.cwjcsu.projecteuler.p101_150;

import java.util.List;

import com.cwjcsu.projecteuler.util.Util;
import com.cwjcsu.projecteuler.util.Util.PrimeDivisor;

/**
 * 1/x + 1/y = 1/n, x<=y, 求整数解个数 ans = [phi(n^2) + 1] / 2 ,phi(n): n的约数的个数,
 *
 * see PE80.txt
 *
 * @author Sunny
 *
 */
public class Problem108 {
	// 180180
	public static void main(String[] args) {
		int n = 90000000;
		int[] primes = Util.getPrimesBlow(n);
		System.out.println("begin");
		for (int i = 10;; i++) {
			if (ansCount(i, primes) > 1000000) {
				System.out.println(i);
				break;
			}
		}
	}

	public static int ansCount(int n, int[] primes) {
		List<PrimeDivisor> divisor = Util.getPrimeDivisors2(n, primes);
		int multi = 1;
		for (int i = 0; i < divisor.size(); i++) {
			multi *= (2 * divisor.get(i).power + 1);
		}
		return (multi + 1) / 2;
	}
}
