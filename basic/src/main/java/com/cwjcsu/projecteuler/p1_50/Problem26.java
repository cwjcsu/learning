package com.cwjcsu.projecteuler.p1_50;
/**
 * 983
 * @author Sunny
 *
 */
public class Problem26 {
	public static void main(String[] args) {
		int d = -1, max = 0;
		for (int i = 2; i < 1000; i++) {
			int tmp = dowork(1, i);
			if (tmp > max) {
				max = tmp;
				d = i;
			}
		}
		System.out.println("D:"+d+",max:"+max+",");
	}

	static int dowork(int n, int d) {
//		System.out.print(n / d + ".");
		n = n % d;
		int m = numBeforeRepeat(n, d);
		for (int i = 0; i < m; i++) {
			n *= 10;
//			System.out.print(n / d);
			n %= d;
		}
		int r = n;
		int count = 0;
		if (r != 0) {
//			System.out.print('(');
			do {
				n *= 10;
//				System.out.print(n / d);
				n %= d;
				count++;
			} while (n != r);
//			System.out.println(')');
		} else {
//			System.out.println();
		}
		return count;
	}

	private static int numBeforeRepeat(int n, int d) {
		int c2 = 0, c5 = 0;
		if (n == 0)
			return 1;
		while (d % 2 == 0) {
			d /= 2;
			c2++;
		}// dominator ��2�ĸ���
		while (d % 5 == 0) {
			d /= 5;
			c5++;
		}// dominator ��5�ĸ���
		while (n % 2 == 0) {
			n /= 2;
			c2--;
		}// can go to negative
		while (n % 5 == 0) {
			n /= 5;
			c5--;
		}// can go to negative
		if (c2 > c5) {
			if (c2 > 0)
				return c2;
			else
				return 0;
		} else {
			if (c5 > 0)
				return c5;
			else
				return 0;
		}
	}
}
