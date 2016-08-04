package com.cwjcsu.projecteuler.p1_50;

import java.util.ArrayList;

public class Problem23 {
	public static boolean isAbundant(int n) {
		int sum = 0;

		for (int i = 1; i < n; i++) {
			if (n % i == 0) {
				sum += i;
			}
		}

		if (sum > n) {
			return true;
		}

		return false;
	}

	public static void main(String[] args) {
		int result = 0;
		int[] numbers = new int[28123];
		ArrayList abundants = new ArrayList();

		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = i + 1;

			result += numbers[i];

			if (isAbundant(numbers[i])) {
				abundants.add(numbers[i]);
			}
		}

		for (int i = 0; i < abundants.size(); i++) {
			for (int j = i; j < abundants.size(); j++) {
				int index = (Integer) abundants.get(i)
						+ (Integer) abundants.get(j) - 1;

				if (index < numbers.length) {
					result -= numbers[index];
					numbers[index] = 0;
				}
			}
		}

		System.out.println(result);
	}
}
