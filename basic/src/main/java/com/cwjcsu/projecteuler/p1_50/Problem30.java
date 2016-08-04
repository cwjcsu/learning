package com.cwjcsu.projecteuler.p1_50;

import java.math.BigInteger;

import com.cwjcsu.projecteuler.util.Util;

/*

 We��re going to loop through numbers testing them out. We know we can skip 1. But how far do we need to loop up to? Let��s look at 4 digit numbers first; the lowest 4-digit number is 1000.  1^5 + 0^5 + 0^5 + 0^5 =1.  The highest 4-digit number is 9999.  9^5 + 9^5 + 9^5 + 9^5 = 236196.  Somewhere in between there is scope for the 5th powers of a 4 digit number to add up to a 4 digit number.

 What about 5 digit numbers?  The highest sum of the 5th powers is 295,245. So all 5 digit numbers fall within that range.

 And with 6 digit numbers?  The highest sum of the 5th powers is 354294.  So we can cover 6 digit numbers up to that far and no further.

 With 7 digit numbers, the highest F is 413343, i.e. the highest sum of the 5th powers of a 7 digit number only adds up to a 6 digit number.  So we only need to loop from 2 to 354294.

 */
public class Problem30 {
	public static void main(String[] args) {
		int s = 0;
		BigInteger h = BigInteger.valueOf(413343);
		for (BigInteger i = BigInteger.valueOf(2); i.compareTo(h) <= 0; i = i
				.add(BigInteger.ONE)) {
			int d = i.compareTo(sumOfDigitPower5(i));
			if (d == 0) {
				s++;
			}
		}
		System.out.println(s);
	}

	static BigInteger sumOfDigitPower5(BigInteger d) {
		int[] ds = Util.toDigit(d);
		BigInteger s = BigInteger.ZERO;
		for (int i = 0; i < ds.length; i++) {
			s = s.add(BigInteger.valueOf(Util.digitPower5(ds[i])));
		}
		return s;
	}

	public static boolean isOK(int number) {
		int n = number;
		int r = 0;
		int tmp;

		while (n > 0) {
			tmp = n % 10;
			int v = 1;

			for (int i = 0; i < 5; i++) {
				v *= tmp;
			}

			r += v;
			n /= 10;
		}

		if (number == r) {
			return true;
		}

		return false;
	}

	static void do1() {
		int result = 0;

		for (int i = 2; i < 354294; i++) {
			if (isOK(i)) {
				result += i;
			}
		}

		System.out.println(result);
	}
}
