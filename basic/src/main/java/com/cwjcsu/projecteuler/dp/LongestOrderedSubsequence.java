package com.cwjcsu.projecteuler.dp;

import java.util.Arrays;

public class LongestOrderedSubsequence {
	public static int los(int[] s) {
		int n = s.length;
		// m[i] means sub sequence s[0..i] los count
		int[] m = new int[n];
		m[0] = 1;
		Arrays.fill(m, 1);
		for (int i = 1; i < n; i++) {
			int t = 0;
			for (int j = 0; j < i; j++) {
				if (s[i] > s[j]) {
					if (m[j] > t) {
						t = m[j];
					}
				}
			}
			m[i] = t + 1;
		}
		return m[n - 1];
	}

//	public static int[] los1(int[] s) {
//		int n = s.length;
//		// m[i] means sub sequence s[0..i] los count
//		int[] m = new int[n];
//		Arrays.fill(m, 1);
//		int[] r = new int[n];
//		int k = 0;
//		int J = 0;
//		for (int i = 1; i < n; i++) {
//			int t = 0;
//			for (int j = 0; j < i; j++) {
//				if (s[i] > s[j]) {
//					if (m[j] > t) {
//						t = m[j];
//					}
//				}
//			}
//			m[i] = t + 1;
//		}
//		return m[n - 1];
//	}
}
