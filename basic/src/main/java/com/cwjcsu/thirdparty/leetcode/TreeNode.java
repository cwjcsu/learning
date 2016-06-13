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
public class TreeNode {
	public int val;
	public TreeNode left;
	public TreeNode right;

	public TreeNode(int x) {
		val = x;
	}

	public TreeNode(int val, TreeNode left, TreeNode right) {
		super();
		this.val = val;
		this.left = left;
		this.right = right;
	}

	@Override
	public String toString() {
		return "#" + val;
	}
}
