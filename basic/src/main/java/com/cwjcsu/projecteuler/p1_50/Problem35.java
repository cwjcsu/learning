package com.cwjcsu.projecteuler.p1_50;

import com.cwjcsu.projecteuler.util.Util;
// 55
public class Problem35 {
	static int N = 1000000;
	static boolean[] isPrime;
	static int[] prim;

	public static void main(String[] args) {
		// System.out.println(next(1234));
		do1();
	}

	static void do1() {
		getPrimeDecisionBlow(N);
		int s = 0;
		for (int i = 0; i < prim.length; i++) {
			int p = prim[i];
			if (!isPrime[p]) {
				continue;
			}
			p = next(p);
			int meet = 1;
			while (prim[i] != p && p != -1) {
				meet++;
				if (!isPrime[p]) {
					break;
				}
				//calculated��not do again
				isPrime[p] = false;
				p = next(p);
			}
			s += p == prim[i] ? meet : 0;
		}
		System.out.println(s);
	}

	public static void getPrimeDecisionBlow(int ex) {
		isPrime = new boolean[ex + 1];
		int[] prime = new int[ex + 1];
		int total = 0;
		for (int i = 0; i < isPrime.length; i++) {
			isPrime[i] = true;
		}
		for (int i = 2; i <= ex; i++) {
			if (isPrime[i]) {
				prime[total++] = i;
			}
			for (int j = 0; j < total && i * prime[j] <= ex; j++) {
				isPrime[i * prime[j]] = false;
				if (i % prime[j] == 0)
					break;
			}
		}
		prim = new int[total];
		System.arraycopy(prime, 0, prim, 0, total);
	}

	static int next(int d) {
		int size = Util.getSize(d);
		int mod = (int) Math.pow(10, size - 1);
		int d1 = d / mod;
		if (Util.isEven(d1) && size > 1) {
			return -1;
		}
		int d2 = d % mod;
		return d2 * 10 + d1;
	}
}
