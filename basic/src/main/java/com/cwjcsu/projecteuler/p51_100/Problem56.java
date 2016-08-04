package com.cwjcsu.projecteuler.p51_100;
import java.math.BigInteger;

import com.cwjcsu.projecteuler.util.Util;

public class Problem56 {
	public static void main(String[] args) {
		int S = 0;
		int tA = 0, tB = 0;
		String str = "";
		for (int a = 1; a < 100; a++) {
			for (int b = 1; b < 100; b++) {
				BigInteger A = BigInteger.valueOf(a);
				BigInteger c = A.pow(b);
				String tmp = c.toString(10);
				int s = Util.sumOfDigits(tmp);
				if (s > S) {
					S = s;
					tA = a;
					tB = b;
					str = tmp;
				}
			}
		}
		System.out.println("A:" + tA + ",B:" + tB + ",S:" + S + ",str:" + str);
	}
}
