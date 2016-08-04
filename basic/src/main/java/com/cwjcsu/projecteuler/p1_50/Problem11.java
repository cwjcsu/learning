package com.cwjcsu.projecteuler.p1_50;

import com.cwjcsu.projecteuler.util.IOUtil;
//						 51267216
//wrong ,right answer is 70600674
public class Problem11 {
	public static void main(String[] args) {
		test1();
	}

	static int[][] X;
	static {
		try {
			X = IOUtil.readDataMatrix("G.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void test1() {
		int M = 0;
		int A1 = 0, A2 = 0, B1 = 0, B2 = 0;
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				if (i <= 16) {
					int mul = multiple(X[i][j], X[i + 1][j], X[i + 2][j],
							X[i + 3][j]);
					if (mul > M) {
						M = mul;
						A1 = i;
						A2 = j;
						B1 = i + 3;
						B2 = j;
					}
				}
				if (j <= 16) {
					int mul = multiple(X[i][j], X[i][j + 1], X[i][j + 2],
							X[i][j + 3]);
					if (mul > M) {
						M = mul;
						A1 = i;
						A2 = j;
						B1 = i;
						B2 = j + 3;
					}
				}
				if (i <= 16 && j <= 16) {
					int mul = multiple(X[i][j], X[i + 1][j + 1],
							X[i + 2][j + 2], X[i + 3][j + 3]);
					if (mul > M) {
						M = mul;
						A1 = i;
						A2 = j;
						B1 = i + 3;
						B2 = j + 3;
					}
				}

			}
		}
		System.out.println("Max product:" + M + ",(" + A1 + "," + A2 + ")-->("
				+ B1 + "," + B2 + ")");

	}

	static int multiple(int a1, int a2, int a3, int a4) {
		return a1 * a2 * a3 * a4;
	}
}
