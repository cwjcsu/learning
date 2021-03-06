package com.cwjcsu.thirdparty.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 385
 * Created by Atlas on 2016/8/14.
 */
public class NestedInteger {
    // Constructor initializes an empty nested list.
    Integer v;
    List<NestedInteger> list = new ArrayList<NestedInteger>();

    public NestedInteger() {

    }

    // Constructor initializes a single integer.
    public NestedInteger(int value) {
        this.v = value;
    }

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger() {
        return v != null;
    }

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger() {
        return v;
    }

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value) {
        this.v = value;
    }

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni) {
        if (list == null) {
            list = new ArrayList<NestedInteger>();
        }
        if (ni != null) {
            list.add(ni);
        }
    }

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList() {
        return list;
    }

    @Override
    public String toString() {
        if (isInteger()) {
            return "" + v;
        } else {
            return "" + list;
        }
    }
}
