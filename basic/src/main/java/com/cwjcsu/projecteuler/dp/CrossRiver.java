package com.cwjcsu.projecteuler.dp;

import java.util.Arrays;

public class CrossRiver {
	public static void main(String[] args) {
		int L = 10;
		int S = 2;
		int T = 3;
		int[] stone = new int[] { 2, 3, 5, 6, 7 };
		// 2
		System.out.println(crossRiver(L, S, T, stone));
	}

	/**
	 * OK
	 * 
	 * @param L
	 * @param S
	 * @param T
	 * @param stone
	 * @return
	 */
	static int crossRiver(int L, int S, int T, int[] stone) {
		int[] F = new int[L];
		F[0] = 0;
		boolean[] has = new boolean[L];
		int MAX_VALUE = Integer.MAX_VALUE - L;
		Arrays.fill(has, false);
		for (int i = 0; i < stone.length; i++) {
			has[stone[i]] = true;
		}
		for (int i = 1; i < S; i++) {
			F[i] = MAX_VALUE;
		}
		for (int i = S; i < L; i++) {
			int max = MAX_VALUE;
			for (int k = S; k <= T; k++) {
				if (i - k >= 0) {
					if (F[i - k] < max) {
						max = F[i - k];
					}
				}
			}
			if (has[i]) {
				F[i] = max + 1;
			} else {
				F[i] = max;
			}
		}
		return F[L - 1];
	}
}
