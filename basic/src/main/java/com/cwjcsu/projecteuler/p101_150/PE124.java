package com.cwjcsu.projecteuler.p101_150;

import java.util.Arrays;

import com.cwjcsu.projecteuler.util.Tick;
import com.cwjcsu.projecteuler.util.Util;
/**
 * Brute force
 * @author atlas
 *
 */
public class PE124 {
	static int N = 100000;
	static Tick t  = new Tick();
	static int[] primes = Util.getPrimesBlow(N + 1);

	public static void main(String[] args) {
		Rad[] rads = new Rad[N + 1];
		rads[0] = new Rad(0, 0);
		for (int i = 1; i <= N; i++) {
			int[] factors = Util.getPrimeDivisors(i, primes);
			rads[i] = new Rad(i, multipleFacors(factors));
		}
		Arrays.sort(rads);
		System.out.println(rads[10000].n);
		System.out.println(t.elapsedTime());
	}

	static int multipleFacors(int[] factors) {
		int m = 1;
		int f = 0;
		for (int i = 0; i < factors.length; i++) {
			if (factors[i] > f) {
				f = factors[i];
				m *= f;
			}
		}
		return m;
	}

	static class Rad implements Comparable<Rad> {
		int n;
		int rad;

		private Rad(int n, int rad) {
			super();
			this.n = n;
			this.rad = rad;
		}

		@Override
		public int compareTo(Rad o) {
			int ret = rad - o.rad;
			if (ret == 0) {
				ret = n - o.n;
			}
			if (ret < 0) {
				return -1;
			} else if (ret > 0) {
				return 1;
			}
			return 0;
		}

		@Override
		public String toString() {
			return n + "," + rad;
		}

	}
}
