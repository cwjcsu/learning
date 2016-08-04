/*$Id: $
 --------------------------------------
  Skybility
 ---------------------------------------
  Copyright By Skybility ,All right Reserved
 * author   date   comment
 * Atlas  2015年10月10日  Created
*/
package com.cwjcsu.projecteuler.p400_500;


import com.cwjcsu.projecteuler.util.Util;

import java.util.concurrent.TimeUnit;


public class PE455 {

	public static void main(String[] args) {
		long s = System.nanoTime();
		long sum = 0;
		for (int i = 2; i <= 1000000; i++) {
			sum += f(i);
		}
		System.out.println(sum);
		System.out.println("Time consumed : " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - s) + " ms");
	}

	/**
	 * 求f(n) = x，满足：
	 * （1）， n^x % 10^9 = x； or
	 * （2），x=0 如果不存在满足（1）的x 
	 */
	public static long f(int n) {
		long m = (long) Math.pow(10, 9);
		long k = 0;
		while (true) {
			long x = Util.powerMod(n, k, m);
			if (k == x || x == 0) {
				return x;
			}
			k = x;
		}
	}

}
