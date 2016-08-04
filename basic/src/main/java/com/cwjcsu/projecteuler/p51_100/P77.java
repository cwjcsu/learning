package com.cwjcsu.projecteuler.p51_100;

public class P77 {

	public static void main(String[] args) {
		int[] primes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43,
				47, 53, 59, 61, 67, 71, 73, 79 };
		int t = 11;
		boolean got = false;
		while (true) {
			int[] ways = new int[t + 1];
			ways[1] = 1;
			for (int i : primes) {
				for (int j = i + 1; j < t + 1; j++) {
					ways[j] += ways[j - i];
					if (ways[t] > 5000) {
						System.out.println(t);
						got = true;
						break;
					}
				}
				if (got)
					break;
			}

			t = t + 1;
		}

	}
}
