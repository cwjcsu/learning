package com.cwjcsu.projecteuler.p1_50;
import com.cwjcsu.projecteuler.util.Util;

public class Problem21 {
	static int[] primes = Util.getPrimesBlow(20000000);

	public static void main(String[] args) {
		do1();
		// System.out.println(getSumOfDiviors(220));
	}

	static void do1() {
		// int[] calced = new int[10000];
		boolean[] flags = new boolean[10001];
		int s = 0;
		for (int i = 0; i <= 10000; i++) {
			if (flags[i]) {
				continue;
			}
			int sum = getSumOfDiviors(i);

			int sum2 = getSumOfDiviors(sum);
			if (sum2 > 10000) {
				continue;
			}
			if (i == sum2 && i != sum) {
				System.out.println(i + "," + sum);
				s += sum + i;
				flags[i] = true;
				flags[sum] = true;
			}
		}
		System.out.println(s);
	}

	static int getSumOfDiviors(int e) {
		int s = 1;
		int sqrtE = (int) Math.sqrt(e) + 1;
		for (int i = 2; i < e; i++) {
			if (e % i == 0) {
				s += i;
			}
		}
		return s;
	}
}
