package com.cwjcsu.projecteuler.p101_150;

import com.cwjcsu.projecteuler.util.Util;

/**
 * see http://www.mathblog.dk/project-euler-120-maximum-remainder/
 * http://www.mathblog.dk/project-euler-123-remainder/ <br/>
 * we got
 * 
 * r=2p<sub>n</sub>n
 * 
 * @author atlas
 * @date 2013-4-27
 */
public class PE123 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long limit = 10000000000L;
		long r = 0;
		int[] primes = Util.getPrimesBlow(500000);
		int n = 7037;

		while (r < limit) {
			n += 2;
			long p = primes[n - 1];
			r = 2 * p * n;
		}
		//21035
		System.out.println(n);
	}

}
