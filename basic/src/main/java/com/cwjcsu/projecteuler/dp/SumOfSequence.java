package com.cwjcsu.projecteuler.dp;

/**
 * 求一个数列中的连续若干个数和的最大值 s[n]: 假设s[i]-s[j]为所求，则<br>
 * （1），s[i]>0,s[i-1]<0,s[j]>0,s[j+1]<0,如果没越界的话。 <br>
 * （2），S(0,i-1)<0（表示从0到i-1求和） ,S(j+1,n)<0.<br>
 * 所以，如果动态过程可以这样描述：从s[n]加入一个元素s[n+1]时，如果S(j+1,n)+s[n+1]>0，则s[j+1,n+1]都应该算入最优序列，
 * 否则不算。
 * 
 * @author Sunny
 * 
 */
public class SumOfSequence {
	public static void main(String[] args) {
		int[] s = new int[] { 3, -4, 5, 1, 32, -23, 43, -2, 32, -100 };
		// 88
		System.out.println(maxSumOfSubSequence(s));
	}

	public static int maxSumOfSubSequence(int[] s) {
		int n = s.length;
		int[] S = new int[n];
		S[0] = s[0];
		int k = 0;
		for (int i = 1; i < n; i++) {
			if (s[i] > 0) {
				if (S[k] > 0) {
					S[k] = S[k] + s[i];
				} else {
					S[++k] = s[i];
				}
			} else {
				if (S[k] < 0) {
					S[k] = S[k] + s[i];
				} else {
					S[++k] = s[i];
				}
			}
		}
		int[] a = new int[++k];
		System.arraycopy(S, 0, a, 0, k);
		int A = -Integer.MAX_VALUE;
		int B = 0;
		for (int i = 0; i < k; i++) {
			int X = a[i];
			if (A < X) {
				if (A + B > 0) {
					A = A + B + X;
					B = 0;
				} else {
					A = X;
					B = 0;
				}
			} else {
				if (X + B > 0) {
					A = A + B + X;
					B = 0;
				} else {
					B = X + B;
				}
			}
		}
		return A;
	}
	// public static int maxSumOfSubSequence(int[] s) {
	// int n = s.length;
	// int[] S = new int[n];
	// int k = 0;
	// S[0] = s[0];
	// int max = 0;
	// for (int i = 1; i < n; i++) {
	// if (s[i] > 0) {
	// if (S[k] > 0) {
	// S[k] = S[k] + s[i];
	// if (S[k] > max) {
	// max = S[k];
	// }
	// } else {
	// S[k++] = s[i];
	// if (S[k] > max) {
	// max = S[k];
	// }
	// }
	//
	// } else {
	// if (S[k] > 0) {
	// S[k] = S[k] + s[i];
	// if (S[k] > max) {
	// max = S[k];
	// }
	// } else {
	// S[k++] = s[i];
	// if (S[k] > max) {
	// max = S[k];
	// }
	// }
	// }
	// }
	// return 0;
	// }
}
