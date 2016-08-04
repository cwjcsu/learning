package com.cwjcsu.projecteuler.p1_50;
public class Problem9 {
	// A:200,B:375,C:425ABC:31875000
	public static void main(String[] args) {
		test1();
	}

	// ������
	static void test1() {
		int A = 0, B = 0, C = 0;
		L: for (int a = 1; a < 333; a++) {
			for (int b = a + 1; b < 500; b++) {
				for (int c = b + 1; c <= 1000; c++) {
					if ((a * a + b * b == c * c) && (a + b + c) == 1000) {
						A = a;
						B = b;
						C = c;
						break L;
					}

				}
			}
		}
		System.out.println("A:" + A + ",B:" + B + ",C:" + C + "ABC:" + A * B
				* C);
	}
}
