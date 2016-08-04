package com.cwjcsu.projecteuler.p101_150;

import java.util.BitSet;

import com.cwjcsu.projecteuler.util.Util;

/**
 * ������
 * @author atlas
 * @date 2013-4-26
 */
public class PE145 {
	/**
	 * 608720
	 * @param args
	 */
	public static void main(String[] args) {
		 int N = 1000000000;
//		int N = 1000;
		int c = 0;
		BitSet bs = new BitSet(N);
		for (int i = 10; i <= N; i++) {
			if (i % 10 == 0) {
				continue;
			}
			if (bs.get(i)) {
				c++;
				continue;
			}
			int r = Util.reverse(i);
			if (Util.isDigitsOdd(i + r)) {
				bs.set(r);
				c++;
			}
		}
		System.out.println(c);
	}

	public static void testMethods() {
		int N = 1000;
		int c = 0;
		for (int i = 10; i <= N; i++) {
			if (i % 10 == 0) {
				continue;
			}
			if (Util.isDigitsOdd(i + Util.reverse(i)))
				c++;
		}
		System.out.println(c);
	}

}
