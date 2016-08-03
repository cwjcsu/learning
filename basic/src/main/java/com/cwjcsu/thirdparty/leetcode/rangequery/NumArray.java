/*$Id: $
 * author                     date    comment
 * cwjcsu@gmail.com  2015年11月23日  Created
 */
package com.cwjcsu.thirdparty.leetcode.rangequery;

/**
 *  303
 * @author atlas
 *
 */
public class NumArray {
	int[] nums;
	int[] sums;

	public NumArray(int[] nums) {
		this.nums = nums;
		this.sums = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			int prev = i > 0 ? sums[i - 1] : 0;
			sums[i] = prev + nums[i];
		}
	}

	void update(int i, int val) {
		int prev = nums[i];
		int d = val - prev;
		for (int k = i; k < sums.length; k++) {
			sums[k] = sums[k] + d;
		}
	}

	public int sumRange(int i, int j) {
		if (i == 0) {
			return sums[j];
		}
		return sums[j] - sums[i - 1];
	}

}
