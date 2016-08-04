package com.cwjcsu.projecteuler.p1_50;
import com.cwjcsu.projecteuler.util.Util;

public class Problem27 {
	public static void main(String[] args) {
		int A = 0, B = 0, N = 0;
		for (int a = -1000; a <= 1000; a++) {
			for (int b = -1000; b <= 1000; b++) {
				int tmp = getConsecutivePrimeCount(a, b);
				if (tmp > N) {
					N = tmp;
					A = a;
					B = b;
				}
			}
		}
		//A:-61,B:971,N:71
		System.err.println("A:" + A + ",B:" + B + ",N:" + N);
	}

	static int getConsecutivePrimeCount(int a, int b) {
		for (int n = 0;; n++) {
			int s = n * n + a * n + b;
			if (s <= 0 || !Util.isProbablePrime(s, 100)) {
				return n;
			}
		}
	}
}
