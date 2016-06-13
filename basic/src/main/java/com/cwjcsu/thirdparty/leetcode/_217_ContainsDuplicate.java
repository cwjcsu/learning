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
public class _217_ContainsDuplicate {

	public boolean containsDuplicate(int[] nums) {
		java.util.Set<Integer> set = new java.util.HashSet<Integer>();
		for (int i = 0; i < nums.length; i++) {
			if(set.contains(nums[i])){
				return true;
			}
			set.add(nums[i]);
		}
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
