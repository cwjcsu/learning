/*
 * author               date               comment
 * chenweijun@skybility.com   2016/9/2 16:22       Created
 * All rights reserved.
 */

package com.cwjcsu.thirdparty.leetcode;

import java.util.Iterator;
import java.util.List;

/**
 * 173. Binary Search Tree Iterator
 * @author Atlas
 */
public class BSTIterator {
    Iterator<Integer> iter;
    public BSTIterator(TreeNode root) {
        List<Integer> data = new Batch().inorderTraversal(root);
        iter = data.iterator();
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return iter.hasNext();
    }

    /** @return the next smallest number */
    public int next() {
        return iter.next();
    }
}
