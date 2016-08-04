package com.cwjcsu.projecteuler.p51_100;

public class Problem94 {
	/**
	 * m^2=3n^2+-1
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int N = 1000000000;
		int Len = (N + 1) / 3;
		long sum = 0;
		for (int i = 1;; i++) {
			int t = 3 * i * i;
			double x1 = Math.sqrt(t + 1);
			double x2 = Math.sqrt(t - 1);
			if (isSquare(x1)) {
				int C = 4 * (t + 1);
				if (C > N) {
					break;
				}
				sum += C;
			}
			if (isSquare(x2)) {
				int C = 4 * (t + 1);
				if (C > N) {
					break;
				}
				sum += C;
			}
		}
		System.out.println(sum);
	}

	static boolean isSquare(double x) {
		return x == (int) x;
	}
}
