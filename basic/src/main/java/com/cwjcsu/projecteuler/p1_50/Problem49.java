package com.cwjcsu.projecteuler.p1_50;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Problem49 {
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

	public static boolean isPermutation(int n1, int n2) {
		boolean result = true;
		String s1 = String.valueOf(n1);
		String s2 = String.valueOf(n2);

		for (int i = 0; i < s1.length(); i++) {
			if (!s2.contains(String.valueOf(s1.charAt(i)))) {
				result = false;
			}
		}

		for (int i = 0; i < s2.length(); i++) {
			if (!s1.contains(String.valueOf(s2.charAt(i)))) {
				result = false;
			}
		}

		return result;
	}

	public static void main(String[] args) {
		String result = null;
		Set smallerPrimers = new TreeSet();
		boolean find = false;

		for (int i = 1000; i < 10000; i++) {
			if (isPrime(i)) {
				Iterator it = smallerPrimers.iterator();

				while (it.hasNext()) {
					int pre = (Integer) (it.next());

					if (isPermutation(pre, i)) {
						int next = i + (i - pre);

						if (isPrime(next) && isPermutation(i, next)
								&& next < 10000) {
							result = String.valueOf(pre) + String.valueOf(i)
									+ String.valueOf(next);
							find = true;
							break;
						}
					}
				}

				if (find && i != 4817) {
					break;
				} else {
					find = false;
				}

				smallerPrimers.add(i);
			}
		}

		System.out.println(result);
	}
}
