package com.cwjcsu.projecteuler.p1_50;
import com.cwjcsu.projecteuler.util.Util;

public class Problem4 {
	public static void main(String[] args) {
		test1();
	}

	// ������ 580085,995*583
	public static void test1() {
		int I = 0, J = 0, S = 0;
		for (int i = 100; i < 999; i++) {
			for (int j = 100; j < 999; j++) {
				int s = i * j;
				if (Util.isPalindrome(s)) {
					if (s > S) {
						I = i;
						J = j;
						S = s;
					}
				}
			}
		}
		System.err.println("I:" + I + ",J:" + J + ",S:" + S);
	}
}
