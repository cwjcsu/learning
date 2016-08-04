package com.cwjcsu.projecteuler.p51_100;

import java.util.BitSet;

import com.cwjcsu.projecteuler.util.Util;

/**
 * p1<sup>2</sup>+p2<sup>3</sup>+p3<sup>4</sup>=5百万， 当p2=p3=2时，p1取到可以使用的最大素数。
 * 即p最大是5百万-24的平方根
 *
 * @author atlas
 * @date 2013-4-26
 */
public class PE87 {
	/**
	 * 1097343
	 * 14656 ? why wrong?
	 * @param args
	 */
	public static void main(String[] args) {
		int N = 50000000;
		int PMax = (int) Math.sqrt(N) + 1;
		int[] primes = Util.getPrimesBlow(PMax);
		System.out.println(primes.length);
		BitSet bs = new BitSet(N);
		for (int i = 0; i < primes.length; i++) {
			int p1 = primes[i];
			int n = p1 * p1;
			for (int j = 0; j < primes.length; j++) {
				int p2 = primes[j];
				n += p2 * p2 * p2;
				if (n < 0 || n > N) {
					break;
				}
				for (int k = 0; k < primes.length; k++) {
					int p3 = primes[k];
					n += p3 * p3 * p3 * p3;
					if (n < 0 || n > N)
						break;
					if (n > 0 && n <= N) {
						bs.set(n);
					}
				}
			}
		}
		int c = 0;
		for (int i = 0; i <= bs.size(); i++) {
			if (bs.get(i))
				c++;
		}
		System.out.println(c);
	}
}
