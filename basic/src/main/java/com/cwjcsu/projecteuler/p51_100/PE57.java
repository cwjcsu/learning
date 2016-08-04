package com.cwjcsu.projecteuler.p51_100;

import java.math.BigInteger;

import com.cwjcsu.projecteuler.util.Util;

/**
 * <p>
 * N 3 7 17 41 99 239 577 1393
 * <p>
 * - - - - - - - - - - - - -
 * <p>
 * D 2 5 12 29 70 169 408 985
 * 
 * N(i)=2*N(i-1)+N(i-2) D(i)=2*D(i-1)+D(i-2) for i>=3;
 * 
 * @author Sunny
 * 
 */
public class PE57 {
	public static void main(String[] args) {
		BigInteger n1 = BigInteger.valueOf(3), n2 = BigInteger.valueOf(7);
		BigInteger d1 = BigInteger.valueOf(2), d2 = BigInteger.valueOf(5);
		int s = 0;
		for (int i = 2; i <= 1000; i++) {
//			System.out.println(n1 + "," + d1);
			// System.out.println(n2+","+d2);
			BigInteger t1 = next(n1, n2);
			BigInteger t2 = next(d1, d2);
			if (Util.size(t1) > Util.size(t2)) {
				s++;
			}
			n1 = n2;
			n2 = t1;
			d1 = d2;
			d2 = t2;
		}
		System.out.println(s);
	}

	static long next(long n1, long n2) {
		return (n2 << 1) + n1;
	}

	static BigInteger next(BigInteger n1, BigInteger n2) {
		return n2.shiftLeft(1).add(n1);
	}
}
