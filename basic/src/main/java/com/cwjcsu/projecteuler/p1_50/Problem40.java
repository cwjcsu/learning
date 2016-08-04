package com.cwjcsu.projecteuler.p1_50;

public class Problem40 {
	public static void main(String[] args) {
		int result = 1;
		char[] number = new char[1000000];
		String s;
		int n = 1;
		int index = 0;

		while (index < number.length) {
			s = String.valueOf(n);

			for (int i = 0; i < s.length(); i++) {
				if (index + i >= number.length) {
					break;
				}

				number[index + i] = s.charAt(i);
			}

			index += s.length();
			n++;
		}

		for (n = 1; n <= 1000000; n *= 10) {
			result *= (number[n - 1] - '0');
		}

		System.out.println(result);
	}
}
