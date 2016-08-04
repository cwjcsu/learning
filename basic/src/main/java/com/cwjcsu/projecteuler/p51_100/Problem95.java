package com.cwjcsu.projecteuler.p51_100;

import java.util.HashMap;

import com.cwjcsu.projecteuler.util.Util;

public class Problem95 {
	public static void main(String[] args) {
		long t1 = System.currentTimeMillis();
		int N = 1000000;
		int[] primes = Util.getPrimesBlow((int) Math.sqrt(N) + 1);
		int[] divs = new int[N + 1];
		for (int i = 2; i <= N; i++) {
			divs[i] = Util.sumDivisor(i, primes);
		}
		int maxLen = 0;
		int maxN = -1;
		// 2763ms
		HashMap<Integer, Boolean> mem = new HashMap<Integer, Boolean>();
		for (int j = 2; j <= N; j++) {
			int r = 0;
			int v = j;
			for (; v > 1 && v <= N && !Boolean.TRUE.equals(mem.get(v)); mem
					.put(v, true), v = divs[v]) {
				r++;
			}
			if (v == j && maxLen < r) {
				maxLen = r;
				maxN = j;
			}
			mem.clear();
		}

		System.out.println("len,n=" + maxLen + "," + maxN);
		System.out.println("time(ms):" + (System.currentTimeMillis() - t1));
	}
}
