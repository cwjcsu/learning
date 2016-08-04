package com.cwjcsu.projecteuler.p151_200;

import java.util.BitSet;

import com.cwjcsu.projecteuler.util.Util;

/**
 * TODO,这个算法好像没有问题，为什么算出来的多一点。
 * @author atlas
 * @date 2013-4-26
 */
public class PE187 {

	/**
	 * why 17532915 wrong?
	 * 
	 * 17427258
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int N = 100000000;
		// int N = 30;
		int[] primes = Util.getPrimesBlow(N / 2);
		int c = 0;
		int c1 = 0;
		BitSet bs = new BitSet(N);// 17503089,17532915
		for (int i = 0; i < primes.length; i++) {
			for (int j = i; j < primes.length; j++) {
				int x = primes[i] * primes[j];
				if (x < 0 || x >= N) {
					break;
				}
				if (x < N) {
					bs.set(x);
					c1++;
				}
			}
		}
		for (int i = 0; i < bs.size(); i++) {
			if (bs.get(i))
				c++;
		}
		System.out.println(c + "," + c1);
	}
}
