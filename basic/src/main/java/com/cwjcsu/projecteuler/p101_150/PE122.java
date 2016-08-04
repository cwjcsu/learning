/*$Id: $
 --------------------------------------
  Skybility
 ---------------------------------------
  Copyright By Skybility ,All right Reserved
 * author   date   comment
 * Atlas  2015年10月1日  Created
*/
package com.cwjcsu.projecteuler.p101_150;

public class PE122 {

	public static void main(String[] args) {
		System.out.println(m(15));
	}

	static int[] K = new int[200];

	static {
		K[0] = 0;
		K[1] = 0;
		K[2] = 1;
		K[3] = 2;
		K[4] = 2;
		K[5] = 3;
	}

	// public static int loopM(int k) {
	// int S = 0;
	// for (int i = 1; i <= k; i++) {
	// S += m(i);
	// }
	// System.out.println("sum m(1~" + k + ")=" + S);
	// return S;
	// }

	// public static int m(int i, int[] m) {
	// int c = 0;
	// if (i == 1) {
	// c = 0;
	// } else if (m[i] > 0) {
	// return m[i];
	// }
	// m[i] = c;
	// return c;
	// }

	public static int m(int k) {
		int[] m = new int[k + 1];
		return m(k, m);
	}

	public static int f(int k, int[] m) {
		if (k == 1) {
			return 0;
		}
		if (m[k] > 0) {
			return 0;
		} else {
			return m(k, m);
		}
	}

	public static int m(int k, int[] m) {
		int C = Integer.MAX_VALUE;
		for (int i = 1; i <= k / 2; i++) {
			int j = k - i;
			int c = f(i, m) + f(j, m) + 1;
			if (c < C) {
				C = c;
			}
		}
		m[k] = C;
		System.out.println("m(" + k + ")=" + C);
		return C;
	}

}

