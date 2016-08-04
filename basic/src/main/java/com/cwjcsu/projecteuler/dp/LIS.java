package com.cwjcsu.projecteuler.dp;

import java.util.Arrays;

/**
 * Longest Increasing Subsequence
 * 
 * @author Sunny
 *         问题描述:给出一个序列a1,a2,a3,a4,a5,a6,a7….an,求它的一个子序列（设为s1,s2,…sk），使得这个子序列满足这样的性质
 *         ，s1<s2<s3<…<sk并且这个子序列的长度最长
 */
public class LIS {
	public static void main(String[] args) {
		System.out.println(lis2(new int[] { 12, 2, 13, 3, 4, 3, 4, 6, 7, 8, 6,
				4, 10, 11 }));
	}

	/**
	 * 上升序列在a中是没有间隔的
	 * 
	 * @param a
	 * @return
	 */
	public static int lis(int[] a) {
		int Sstart = 0;
		int Send = 0;
		int S0 = 0;
		for (int i = 1; i < a.length; i++) {
			if (a[i] > a[i - 1]) {
				if ((i - S0) > (Send - Sstart)) {
					Sstart = S0;
					Send = i;
				}
			} else {
				S0 = i;
			}
		}
		return Send - Sstart + 1;
	}

	/**
	 * 上升序列在a中可以存在间隔，O(n*n)
	 * 
	 * @param a
	 * @return
	 */
	public static int lis1(int[] a) {
		int[] d = new int[a.length];
		d[0] = a[0];
		for (int i = 1; i < a.length; i++) {
			int max = -Integer.MAX_VALUE;
			for (int k = 0; k < i; k++) {
				if (a[k] < a[i]) {
					if (d[k] > max) {
						max = d[k];
					}
				}
			}
			d[i] = max + 1;
		}
		return d[a.length - 1];
	}

	/**
	 * O(n*log(n)) OK
	 * 
	 * 
	 * 
	 * @param a
	 * @return
	 */
	public static int lis2(int[] a) {
		/**
		 * f[i]是序列a[0] ~ a[i]的最长子序列末尾的元素,所以，如果
		 * a[i] < a[i+1] 则f[i+1] = a[i+1];
		 * a[i] > a[i+1] 则f[i+1] = f[i]
		 */
		int[] f = new int[a.length + 1];
		Arrays.fill(f, Integer.MAX_VALUE);
		f[0] = a[0];
		int m = 0;
		for (int i = 0; i < a.length; i++) {
			int p = find(f, a[i], 0, m);
			if (a[i] < f[p])
				f[p] = a[i];
			if (p > m)
				m = p;
		}
		return m + 1;
	}

	private static int find(int[] f, int num, int l, int r) {
		while (l <= r) {
			int mid = (l + r) / 2;
			if (num == f[mid]) {
				return mid;
			}
			if (f[mid] < num && f[mid + 1] >= num)
				return mid + 1;
			else if (f[mid] > num) {
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		// num 比f中谁都小
		return l;
	}

}
