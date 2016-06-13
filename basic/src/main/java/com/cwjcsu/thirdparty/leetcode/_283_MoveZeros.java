/*$Id: $
 * author                     date    comment
 * cwjcsu@gmail.com  2015年10月26日  Created
 */
package com.cwjcsu.thirdparty.leetcode;

/**
 * 
 * @author atlas
 *
 */
public class _283_MoveZeros {

	public static void moveZeroes(int[] nums) {
		int c = 0;
		for (int i = 0; i < nums.length - c; i++) {
			if (nums[i] == 0) {
				c++;
				for (int j = i; j < nums.length - 1; j++) {
					nums[j] = nums[j + 1];
				}
				nums[nums.length - 1] = 0;
				if (nums[i] == 0) {
					i--;
				}
			}
		}
	}

	public static void main(String[] args) {
		moveZeroes(new int[] { 0, 0, 1 });
	}
}
