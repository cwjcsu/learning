package com.cwjcsu.projecteuler.p51_100;

import com.cwjcsu.projecteuler.util.Util;
/**
 * see p 28
 * @author Sunny
 *
 */
public class PE58 {
	static int[] primes = Util.getPrimesBlow(30000000);

	public static void main(String[] args) {
		int total = 1;
		int prime = 0;
		for (int i = 3;; i += 2) {
			int[] d = getCornerNumber(i);
			if (Util.isProbablePrime(d[0], 100)) {
				prime++;
			}
			if (Util.isProbablePrime(d[1], 100)) {
				prime++;
			}
			if (Util.isProbablePrime(d[2], 100)) {
				prime++;
			}
			total += 4;
			if (10 * prime <= total) {
				System.out.println("Len:" + i);
				break;
			}
		}
	}

	static int[] getCornerNumber(int len) {
		int[] d = new int[4];
		d[3] = len * len;
		d[2] = d[3] - len + 1;
		d[1] = d[2] - len + 1;
		d[0] = d[1] - len + 1;
		return d;
	}
}
