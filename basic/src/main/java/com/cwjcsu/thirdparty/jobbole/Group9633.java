/*$Id: $
 * author   date   comment
 * cwjcsu@gmail.com  2015年10月25日  Created
*/
package com.cwjcsu.thirdparty.jobbole;

public class Group9633 {

	public static void main(String[] args) {
		int[] x = { 2, 4, 5, 6, 7, 0, 1, 2 };
		x = new int[] { 15, 16, 17, 18, 19, 20, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, };
		// System.out.println(search(x, 3));
		// System.out.println(search(new int[] { 1, 1 }, 0));
		// System.out.println(search(new int[] { 1 }, 1));
		// System.out.println(search(new int[] { 3, 1, 1 }, 3));
		System.out.println(search(new int[] { 1, 3, 1, 1, 1 }, 3));
	}

	public static boolean search(int[] nums, int target) {
		int x0 = nums[0];
		int xn = nums[nums.length - 1];
		if (target == x0 || target == xn) {
			return true;
		}
		if (target > xn && target < x0) {
			return false;
		}
		int l = 0, r = nums.length - 1;
		while (l <= r) {
			int m = (l + r) / 2;
			if (target > xn) {
				if (nums[m] < xn) {
					r = m - 1;
				} else if (nums[m] > xn) {
					if (target < nums[m]) {
						r = m - 1;
					} else if (target > nums[m]) {
						l = m + 1;
					} else {
						return true;
					}
				} else {
					r = m - 1;
				}
			} else if (target < xn) {
				if (nums[m] > xn) {
					l = m + 1;
				} else if (nums[m] < xn) {
					if (target < nums[m]) {
						r = m - 1;
					} else if (target > nums[m]) {
						l = m + 1;
					} else {
						return true;
					}
				} else {
					r = m - 1;
				}
			} else {
				return true;
			}
		}
		return false;
	}
}
