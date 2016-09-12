/*$Id: $
 * author                     date    comment
 * cwjcsu@gmail.com  2015年10月26日  Created
 */
package com.cwjcsu.thirdparty.leetcode;

/**
 * @author atlas
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


    public static TreeNode _(int x) {
        return new TreeNode(x);
    }

    public static TreeNode _(int val, TreeNode left, TreeNode right) {
        return new TreeNode(val, left, right);
    }

    @Override
    public String toString() {
        if (left == null && right == null) {
            return String.valueOf(val);
        }
        return "(" + val + "," + (left != null ? left : "") + "," + (right != null ? right : "") + ")";
    }
}
