package com.cwjcsu.projecteuler.dp;

import java.util.Arrays;

public class Knapsack {
	/**
	 * 
	 * @param W
	 *            载重
	 * @param w
	 *            每种物品的重量
	 * @param v
	 *            每种物品的价值
	 * @return
	 */
	public static int knapsack01(int W, int[] v, int[] w) {
		int N = w.length;
		int[] f = new int[W + 1];
		for (int i = 0; i < N; i++) {
			for (int j = W; j >= w[i]; j--) {
				int t = f[j - w[i]] + v[i];
				if (t > f[j]) {
					f[j] = t;
				}
			}
		}
		// System.out.println(f[W]);
		return f[W];
	}

	/**
	 * 
	 * @param W
	 *            载重
	 * @param w
	 *            每种物品的重量
	 * @param v
	 *            每种物品的价值
	 * @return
	 */
	public static int knapsack0n(int W, int[] v, int[] w) {
		int N = w.length;
		int[] f = new int[W + 1];
		Arrays.fill(f, 0);
		for (int i = 0; i < N; i++) {
			for (int j = w[i]; j <= W; j++) {
				int t = f[j - w[i]] + v[i];
				if (t > f[j]) {
					f[j] = t;
				}
			}
		}
		return f[W];
	}

	/**
	 * OK
	 * 
	 * @param C
	 *            载重
	 * @param v
	 *            价值
	 * @param w
	 *            重量
	 * @return
	 */
	public static int knapsack01_1(int C, int[] v, int[] w) {
		int[] f = new int[C + 1];
		for (int i = 0; i < v.length; i++) {
			for (int j = C; j >= w[i]; j--) {
				int t = f[j - w[i]] + v[i];
				if (t > f[j]) {
					f[j] = t;
				}
			}
		}
		return f[C];
	}

	public static int knapsackComplete(int C, int[] v, int[] w) {
		int[] f = new int[C + 1];
		Arrays.fill(f, 0);
		for (int i = 0; i < v.length; i++) {
			for (int j = w[i]; j <= C; j++) {
				int t = f[j - w[i]] + v[i];
				if (t > f[j]) {
					f[j] = t;
				}
			}
		}
		return f[C];
	}
}
