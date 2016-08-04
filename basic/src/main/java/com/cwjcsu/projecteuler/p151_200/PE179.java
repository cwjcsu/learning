package com.cwjcsu.projecteuler.p151_200;

import java.util.Arrays;

/**
 * 使用下面的方法计算出i的所有因子个数，保存在sieve[i]中
 * @author atlas
 * @date 2013-4-26
 */
public class PE179 {

	public static void main(String[] args) {
		final int LIMIT = 10000000;
		int[] sieve = new int[LIMIT + 1];
		Arrays.fill(sieve, 2); // don't worry about elements 0 & 1

		for (int i = 2; i <= (int) Math.sqrt(LIMIT); i++) {
			int j = i * i;
			sieve[j]--;
			while (j <= LIMIT) {
				sieve[j] += 2;
				j += i;
			}
		}

		int answer = 0;

		for (int i = 2; i < LIMIT; i++) {
			if (sieve[i] == sieve[i + 1])
				answer++;
		}
		System.out.println(answer);
	}
}
