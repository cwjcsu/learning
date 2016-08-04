/*$Id: $
 --------------------------------------
  Skybility
 ---------------------------------------
  Copyright By Skybility ,All right Reserved
 * author                     date    comment
 * chenweijun@skybility.com  2015年10月10日  Created
 */
package com.cwjcsu.projecteuler.p300_400;

import java.util.concurrent.TimeUnit;

/**
 * @see http://blog.dreamshire.com/project-euler-318-solution/
 * 
 *      Number (sqrt(p)+sqrt(q))^(2n)+(sqrt(q)-sqrt(p))^(2n) is integer. So
 *      {(sqrt(p)+sqrt(q))^(2n)} = 1
 * 
 * 
 *      Decimal tends to 1 if q^.5-p^.5<1
 * 
 *      There will be 2011 or more 9's if (q^5-p^.5)^2n <= 10^(-2011)
 * 
 *      Or, for any p,q pair then n = ceiling(-2011/(2*log(q^.5-p^.5)) whre log
 *      is base 10
 * 
 * 
 * @author atlas
 *
 */
public class PE318 {

	public static void main(String[] args) {// 709313889
		long t = System.nanoTime();
		long s = 0;
		int L = 2011;
		for (int p = 1; p < L; p++) {
			for (int q = p + 1; q < L - p + 1; q++) {
				double c = p + q - 2 * Math.sqrt(p * q);
				if (c < 1) {// 等价于 (√q-√p) < 1
					s += Math.ceil(-L / Math.log10(c));
				}
			}
		}
		System.out.println(s);
		System.out.println("Time consumed "
				+ TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - t) + " ms");
	}
}
