/*
 * author               date               comment
 * chenweijun@skybility.com   2016/9/12 10:16       Created
 * All rights reserved.
 */

package com.cwjcsu.thirdparty.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 398. Random Pick Index
 *
 * @author Atlas
 */
public class RandomPickIndex2 {


    private int[] nums;

    /**
     * @param nums
     */
    public RandomPickIndex2(int[] nums) {
        this.nums = nums;
    }

    /**
     * O(n) OK
     * <p>
     * 这是时间换空间的思路
     *
     * @param target
     * @return
     */
    public int pick(int target) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                list.add(i);
            }
        }
        return list.get((int) (Math.random() * list.size()));
    }
}
