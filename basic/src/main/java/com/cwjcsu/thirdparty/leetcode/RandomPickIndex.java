/*
 * author               date               comment
 * chenweijun@skybility.com   2016/9/12 10:02       Created
 * All rights reserved.
 */

package com.cwjcsu.thirdparty.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Atlas
 */
public class RandomPickIndex {
    private Map<Integer, List<Integer>> data = new HashMap<Integer, List<Integer>>();

    /**
     * Memory Limit Exceeded
     *
     * @param nums
     */
    public RandomPickIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            List<Integer> list = data.get(nums[i]);
            if (list == null) {
                list = new ArrayList<Integer>(1);
                data.put(nums[i], list);
            }
            list.add(i);
        }
    }

    public int pick(int target) {
        List<Integer> list = data.get(target);
        if (list == null) {
            return -1;
        }
        return list.get((int) (Math.random() * list.size()));
    }
}
