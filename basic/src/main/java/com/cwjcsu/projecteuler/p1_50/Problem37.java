package com.cwjcsu.projecteuler.p1_50;

import java.math.BigInteger;

import com.cwjcsu.projecteuler.util.Util;

public class Problem37 {
	/**
	 * 23
37
53
73
313
317
373
797
3137
3797
739397
SUM:748317
	 * @param args
	 */
	public static void main(String[] args) {
		BigInteger d = BigInteger.valueOf(13);
		int count = 0;
		long s = 0;
		while (count <11) {
			if (Util.checkPrimeTruncatable(d.longValue())) {
				System.out.println(d);
				s+=d.longValue();
				count++;
			}
			d = d.nextProbablePrime();
		}
		System.out.println("SUM:"+s);
	}
	 
}
