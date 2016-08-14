package com.cwjcsu.thirdparty.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Atlas on 2016/8/14.
 */
public class _382_Solution {
    private List<Integer> list;
    private Random R = new Random();
    /**
     * @param head The linked list's head.
     *             Note that the head is guaranteed to be not null, so it contains at least one node.
     */
    public _382_Solution(ListNode head) {
        ListNode next = head;
        list = new ArrayList<Integer>();
        while (next != null) {
            list.add(next.val);
            next = next.next;
        }
    }

    /**
     * Returns a random node's value.
     */
    public int getRandom() {
        return list.get(R.nextInt(list.size()));
    }
}
