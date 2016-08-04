package com.cwjcsu.projecteuler.p1_50;

public class Problem39 {
	public static void main(String[] args) {
		int S = 0;
		int I = 0;
		for (int i = 3; i <= 1000; i++) {
			int s = 0;
			int maxa = i / 3;
			for (int a = 1; a <= maxa; a++) {
				int maxb = (i - a) / 2;
				int AA = a * a;
				for (int b = a; b <= maxb; b++) {
					int BB = b * b;
					for (int c = b; a + b + c <= i; c++) {
						if (isTriangle(a, b, c, i) && (AA + BB == c * c)) {
							s++;
						}
					}
				}
			}
			if (s > S) {
				S = s;
				I = i;
			}
		}
		System.err.println("S:" + S + ",I:" + I);
	}

	/**
	 * a<=b<=c
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	static boolean isTriangle(int a, int b, int c, int i) {
		return (a + b > c) && (a + b + c == i);
	}
}
