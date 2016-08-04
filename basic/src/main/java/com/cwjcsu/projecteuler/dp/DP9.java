package com.cwjcsu.projecteuler.dp;

public class DP9 {
	public static void main(String[] args) {
		int[] v = new int[] { 800, 400, 300, 400, 200 };
		int[] w = new int[] { 2, 5, 5, 3, 2 };
		int N = 1000;
		System.out.println(dp(v, w, N));
	}

	public static boolean[] knapsack01(int C, int[] v1, int[] w1) {
		int N = w1.length;
		int[] v = new int[v1.length + 1];
		int[] w = new int[w1.length + 1];
		v[0] = w[0] = 0;
		System.arraycopy(v1, 0, v, 1, v1.length);
		System.arraycopy(w1, 0, w, 1, w1.length);
		// V[i][j] ��ʾ��ǰi����Ʒװ������Ϊj�ı����л�õ�����ֵ
		int[][] V = new int[N + 1][C + 1];
		boolean[] x = new boolean[N];
		int i = 0, j = 0;
		for (i = 0; i <= N; i++) {
			for (j = 0; j <= C; j++) {
				if (i == 0 || j == 0) {
					V[i][j] = 0;
				} else if (j < w[i]) {
					V[i][j] = V[i - 1][j];
				} else {
					int t1 = v[i] + V[i - 1][j - w[i]];
					int t2 = V[i - 1][j];
					V[i][j] = t1 > t2 ? t1 : t2;
				}
			}
		}
		i--;
		j--;
		for (; j >= 0 && i >= 1;) {
			x[i - 1] = V[i][j] > V[i - 1][j];
			if (x[i - 1]) {
				j = j - w[i];
			}
			i--;
		}
		System.out.println(V[N][C]);
		return x;
	}

	/**
	 * 
	 * @param v
	 *            �۸�
	 * @param w
	 *            ��Ҫ��
	 * @param N
	 *            ��Ǯ��
	 * @return
	 */
	public static int dp(int[] v, int[] w, int N) {
		int[] value = new int[v.length];
		for (int i = 0; i < w.length; i++) {
			value[i] = w[i] * v[i];
		}
		return Knapsack.knapsack01_1(N, value, v);
	}
}
