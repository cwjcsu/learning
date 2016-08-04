package com.cwjcsu.projecteuler.p1_50;

import java.util.Set;
import java.util.TreeSet;

public class Problem50 {
	public static boolean isPrime(int number) {
		if (number < 2) {
			return false;
		} else if (number == 2) {
			return true;
		} else {
			for (long i = 2; i <= Math.sqrt(number); i++) {
				if (number % i == 0) {
					return false;
				}
			}
		}

		return true;
	}

	public static void main(String[] args) {
		int result = 0;
		int maxLength = 0;
		Set primers = new TreeSet();

		for (int i = 2; i < 1000000; i++) {
			if (isPrime(i)) {
				primers.add(i);
			}
		}

		Integer[] p = (Integer[]) primers.toArray(new Integer[primers.size()]);

		for (int i = 0; i < p.length; i++) {
			long tmp = 0;

			for (int k = i; k < p.length; k++) {
				tmp += p[k];
			}

			for (int j = p.length - 1; j > i; j--) {
				if (tmp < 1000000 && isPrime((int) tmp) && (j - i) >= maxLength) {
					maxLength = j - i;
					result = (int) tmp;
					break;
				}

				tmp -= p[j];
			}
		}

		System.out.println(result);
	}
}
