package com.cwjcsu.projecteuler.p1_50;

public class Problem24 {
	public static String[] permutations(String digits) {
		String[] result;
		String[] resultSub;
		String digitsSub;
		int n = 1;

		for (int i = 1; i <= digits.length(); i++) {
			n *= i;
		}

		result = new String[n];

		if (n == 1) {
			result[0] = digits;
			return result;
		}

		for (int i = 0; i < digits.length(); i++) {
			digitsSub = digits.substring(0, i) + digits.substring(i + 1);
			resultSub = permutations(digitsSub);

			for (int j = 0; j < resultSub.length; j++) {
				result[i * resultSub.length + j] = digits.charAt(i)
						+ resultSub[j];
			}
		}

		return result;
	}

	public static void main(String[] args) {
		String[] result = permutations("0123456789");

		System.out.println(result[999999]);
	}
}
