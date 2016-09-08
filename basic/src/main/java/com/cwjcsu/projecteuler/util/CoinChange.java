package com.cwjcsu.projecteuler.util;

/**
 * 数组s是钱币的面值(s1<s2<s3<...)。 假设每种钱币都是足够多的，那么有多少中方式把它们组合成n
 *
 * @param n
 * @param m
 * @return
 */
public class CoinChange {
	/**
	 *
	 * @param n
	 *            需要组成的钱币总数
	 * @param m
	 *            s中可以使用的面值的索引上限，也就是从0-m所表示的面值都可以使用。
	 *            <P>
	 *            算法的递归逻辑是这样的：所有可能的组合数C(n,m)可以分成两类：（1）组合没有包含面值s[m]的钱币；（2）
	 *            组合至少包含一个面值为s[m]的钱币，于是C(n,m)=C(n,m-1)+C(n-s[m],m)
	 *            </p>
	 * @param s
	 * @return
	 */
	static int count(int n, int m, int[] s) {
		if (n == 0)
			return 1;
		if (n < 0)
			return 0;
		if (m < 0 && n >= 1)
			return 0;
		return count(n, m - 1, s) + count(n - s[m], m, s);
	}

	public static void main(String[] args) {
		p77();
	}

	// 73682
	public static void p36() {
		int[] s = { 1, 2, 5, 10, 20, 50, 100, 200 };
		System.out.println(count(200, s.length - 1, s));
	}

	public static void p77() {
		int[] s = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53,
				59, 61, 67, 71, 73, 79 };
		for (int t = 11;; t++) {
			int r = count(t, s.length - 1, s);
			if (r > 5000) {
				System.out.println(t);
				break;
			}
		}
	}
}
