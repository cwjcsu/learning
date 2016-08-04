package com.cwjcsu.projecteuler.p51_100;

import java.math.BigInteger;

import com.cwjcsu.projecteuler.util.Util;

public class Problem65 {
	public static void main(String[] args) {
		System.out.println(doSum(100));
	}

	public static int doSum(int N) {
		BigInteger n = BigInteger.ZERO;
		BigInteger d = BigInteger.ONE;
		BigInteger t = null;
		long i = N;
		while (i > 0) {// 这里恰好计算到2+1/...中1/... +1，所以计算完后还要+1，
			int c = 1;
			if (i % 3 == 0) {
				c = (int) (2 * i / 3);
			}
			t = d;
			d = d.multiply(BigInteger.valueOf(c)).add(n);
			n = t;
			i--;
		}
		d = d.add(n);// 最后+1
		System.out.println("d/n:" + d + "/" + n);
		return Util.sumOfDigits(d.toString());
	}

}
