package com.cwjcsu.projecteuler.p101_150;

import java.util.BitSet;

import com.cwjcsu.projecteuler.util.Util;

/**
 * 根据http://www.mathblog.dk/project-euler-131-primes-perfect-cube/
 * 的推导，最后可以得出p=(i+1)<sup>3</sup>-i<sup>3</sup>, 关于i的递增的，
 * 当i=577时，可以得到p=1000519，所以需要检查i=1到577。
 *
 * @author atlas
 * @date 2013-4-28
 */
public class PE131 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int N = 1000000;
		BitSet primes = Util.getPrimeDecisionBlow2(N);
		int c = 0;
		for (int i = 1; i < 577; i++) {
			if (primes.get((int) (Util.power(i + 1, 3) - Util.power(i, 3))))
				c++;
		}
		System.out.println(c);
	}

}
