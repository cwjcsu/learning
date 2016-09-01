/*
 * author               date               comment
 * chenweijun@skybility.com   2016/9/1 15:15       Created
 * All rights reserved.
 */

package com.cwjcsu.thirdparty.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 295. Find Median from Data Stream
 *
 * 1，使用Collections.sort(list):Time Limit Exceeded
 * 2,使用插入排序的变种：二分查找排序,成功
 *
 * @author Atlas
 */
public class MedianFinder {
    public List<Integer> list = new ArrayList<Integer>();

    public MedianFinder() {
    }

    // Adds a number into the data structure.
    public void addNum(int num) {//插入排序O(log2(n))
        int l = 0;
        int r = list.size();
        int m = (l + r) / 2;
        while (l < r) {
            m = (l + r) / 2;
            if (list.get(m) < num) {
                l = m;
            } else if (list.get(m) > num) {
                r = m;
            } else {
                l = r = m;
            }
            if (l == r - 1) {
                if (list.get(l) >= num) {
                    m = l;
                } else {
                    m = r;
                }
                break;
            }
        }
        list.add(m, num);//O(n)
    }

    // Returns the median of current data stream
    public double findMedian() {
        if (list.size() % 2 == 0) {
            return (list.get(list.size() / 2 - 1) + list.get(list.size() / 2)) / 2.0;
        } else {
            return list.get(list.size() / 2);
        }
    }
}
