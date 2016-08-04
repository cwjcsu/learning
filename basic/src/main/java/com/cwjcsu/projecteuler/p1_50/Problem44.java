package com.cwjcsu.projecteuler.p1_50;

public class Problem44 {

	public static boolean isPentagonal(long number) {
		number = 24 * number + 1;
		double s = Math.sqrt(number);
		number = (long) s;

		if (s - number > 0.0000001 || s - number < -0.0000001) {
			return false;
		}

		number++;

		if (number % 6 != 0) {
			return false;
		}

		return true;
	}

	static void do1() {
		long result = Long.MAX_VALUE;
		long[] p = new long[20000];

		for (int i = 0; i < p.length; i++) {
			if (3 * (i + 1) - 2 > result) {
				break;
			}

			p[i] = (3 * (i + 1) - 1) * (i + 1) / 2;

			for (int j = 0; j < i; j++) {
				long sum = p[i] + p[j];

				if (!isPentagonal(sum)) {
					continue;
				}

				long dif = p[i] - p[j];

				if (isPentagonal(dif) && dif < result) {
					result = dif;
				}
			}
		}

		System.out.println(result);
	}

	public static void main(String[] args) {
		System.out.println(isPentagonal(5));
	}
}
