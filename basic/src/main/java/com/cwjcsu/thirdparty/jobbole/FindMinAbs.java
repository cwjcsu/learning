/*$Id: $
 * author                     date    comment
 * cwjcsu@gmail.com  2015年10月21日  Created
 */
package com.cwjcsu.thirdparty.jobbole;

/**
 * 
 * @author atlas
 *
 */
public class FindMinAbs {

	/**
	 * 用三分法求升序数组data中绝对值最小的元素。如果有两个绝对值相等，返回值小的那一个。
	 */
	public static int findMinAbs(int[] data) {
		int left = 0, right = data.length - 1;
		int m1 = left, m2 = right;
		while (left < right) {
			m1 = left + (right - left) / 3;
			m2 = right - (right - left) / 3;
			int f1 = Math.abs(data[m1]);
			int f2 = Math.abs(data[m2]);
			if (f1 > f2) {
				left = m1 + 1;
			} else if (f1 < f2) {
				right = m2 - 1;
			} else {
				left = m1;
				right = m2;
				if (left == right - 1) {
					break;//TODO这里是发现了两个最小值
				}
			}
		}
		return data[left];
	}

	public static void main(String[] args) {
		int[] data = new int[] { -123, -23, -1, 2, 25, 100 };
		System.out.println(findMinAbs(data));
		data = new int[] { -123, -23, -1, 1, 2, 25, 100 };
		System.out.println(findMinAbs(data));
		data = new int[] { 12, 12, 23, 34,112, 125, 1100 };
		System.out.println(findMinAbs(data));
	}

}
