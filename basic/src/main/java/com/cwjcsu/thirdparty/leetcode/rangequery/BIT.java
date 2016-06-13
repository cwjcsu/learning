/*$Id: $
 * author                     date    comment
 * cwjcsu@gmail.com  2015年11月23日  Created
 */
package com.cwjcsu.thirdparty.leetcode.rangequery;

/**
 * BinaryIndexedTree
 * 
 * Fenwick tree
 * 
 * https://leetcode.com/problems/range-sum-query-mutable/
 * 
 * 
 * @author atlas
 *
 */
public class BIT {

	private int[] sums;
	private int[] nums;

	public BIT(int[] nums) {
		this.nums = nums;
		this.sums = new int[nums.length + 1];
		for (int i = 0; i < nums.length; i++) {
			add(i + 1, nums[i]);
		}
	}

	/**
	 * 第x个元素增加v（x从1开始）
	 * 
	 * @param x
	 * @param v
	 */
	public void add(int x, int v) {
		while (x <= nums.length) {
			sums[x] += v;
			x += lowbit(x);
		}
	}

	/**
	 * 求下标为0~x的和
	 * 
	 * @param x
	 * @return
	 */
	public int sum(int x) {
		int sum = 0;
		while (x > 0) {
			sum += this.sums[x];
			x -= lowbit(x);
		}
		return sum;
	}

	public int sum(int i, int j) {
		return sum(j + 1) - sum(i);
	}

	public void update(int i, int v) {
		add(i + 1, v - this.nums[i]);
		this.nums[i] = v;
	}

	/**
	 * 求2^k，k是i的二进制末尾0的个数。也就是在BIT数组中，下标为i的元素管辖的区间的元素个数。
	 * 
	 * sum[i]就是包含nums[i]的往前lowbit(i)个元素的和。
	 * 
	 * 例如lowbit(16)=16,lowbit(15)=1,lowbit(14)=2,...
	 * 
	 * @param i
	 * @return
	 */
	public static int lowbit(int i) {
		return i & -i;
	}

	public static void main(String[] args) {
		for (int i = 1; i <= 16; i++) {
			System.out.println(i + ":" + lowbit(i));
		}
	}
}
