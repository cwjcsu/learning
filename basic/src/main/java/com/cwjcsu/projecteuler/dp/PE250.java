package com.cwjcsu.projecteuler.dp;

import java.math.BigInteger;
import java.util.Arrays;

import com.cwjcsu.projecteuler.util.Util;

//51452813835211
public class PE250 {
	public static void main(String[] args) {
		do1();
	}

	public static void do2() {
		int N = 250250;
		int n = 250;
		BigInteger sum = BigInteger.ZERO;
		// H[i]��ʾ��������A���Ӽ�����A:Ԫ��֮��%250=i;
		long[] F = new long[n];
		for (int i = 1; i <= N; i++) {
			int m = Util.powerMod(i, i, n);
			if (m == 0) {
				sum = sum.add(BigInteger.ONE);
			}
			for (int j = 0; j < n; j++) {
				int s = (j + m) % n;
				if (s == 0) {
					sum = sum.add(BigInteger.valueOf(F[m]));
				}
				F[s] = F[s] + F[m];
			}
			F[m] = F[m] + 1;
		}
		System.out.println(sum);
	}

	public static void do1() {
		int N = 250250;
		int n = 250;
		BigInteger sum = BigInteger.ZERO;
		// H[i]��ʾ��������A���Ӽ�����A:Ԫ��֮��%250=i;
		BigInteger[] F = new BigInteger[n];
		Arrays.fill(F, BigInteger.ZERO);
		for (int i = 1; i <= N; i++) {
			if (i % 5000 == 0) {
				System.out.println(i);
			}
			int m = Util.powerMod(i, i, n);
			if (m == 0) {
				sum = sum.add(BigInteger.ONE);
			}
			for (int j = 0; j < n; j++) {
				int s = (j + m) % n;
				if (s == 0) {
					sum = sum.add(F[m]);
				}
				F[s] = F[s].add(F[m]);
			}
			F[m] = F[m].add(BigInteger.ONE);
		}
		System.out.println(sum);
	}

}
