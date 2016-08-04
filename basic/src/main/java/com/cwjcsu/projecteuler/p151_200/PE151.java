package com.cwjcsu.projecteuler.p151_200;

public class PE151 {
	static int count = 0;
	static int sum = 0;

	static double c = 0;

	public static void main(String[] args) {
		// next(1, 1, 1, 1, 1);
		// System.out.println(sum + "," + count);
		next(1, 1, 1, 1, 0, 1d);
		System.out.println(c);
	}

	static void next(int A2, int A3, int A4, int A5, int step) {
		sum++;
		if (A2 + A3 + A4 + A5 == 1) {
			count++;
		}
		if (A2 >= 1) {
			next(A2 - 1, A3 + 1, A4 + 1, A5 + 1, step + 1);
		}
		if (A3 >= 1) {
			next(A2, A3 - 1, A4 + 1, A5 + 1, step + 1);
		}
		if (A4 >= 1) {
			next(A2, A3, A4 - 1, A5 + 1, step + 1);
		}
		if (A5 >= 1) {
			next(A2, A3, A4, A5 - 1, step + 1);
		}
		if (A2 + A3 + A4 + A5 == 0) {
			return;
		}
		if (step == 16) {
			return;
		}
	}

	// 0.464399
	static void next(int A2, int A3, int A4, int A5, int single, double p) {
		int isSingle = 0;
		int sum = A2 + A3 + A4 + A5;

		if (sum == 1 && A5 == 0) {
			isSingle = 1;
		} else if (sum == 0) {
			c += single * p;
			return;
		}
		if (A2 >= 1) {
			next(A2 - 1, A3 + 1, A4 + 1, A5 + 1, single + isSingle, p * A2
					/ sum);
		}
		if (A3 >= 1) {
			next(A2, A3 - 1, A4 + 1, A5 + 1, single + isSingle, p * A3 / sum);
		}
		if (A4 >= 1) {
			next(A2, A3, A4 - 1, A5 + 1, single + isSingle, p * A4 / sum);
		}
		if (A5 >= 1) {
			next(A2, A3, A4, A5 - 1, single + isSingle, p * A5 / sum);
		}
	}
}
