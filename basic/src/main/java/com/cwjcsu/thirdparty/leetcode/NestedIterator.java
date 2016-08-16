/*
 * author               date               comment
 * chenweijun@skybility.com   2016/8/16 10:36       Created
 * All rights reserved.
 */

package com.cwjcsu.thirdparty.leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Atlas
 */
public class NestedIterator implements Iterator<Integer> {
    private List<Integer> data = new ArrayList<Integer>();
    private Iterator<Integer> iterator;

    public NestedIterator(List<NestedInteger> nestedList) {
        for (NestedInteger ni : nestedList) {
            flattern(ni);
        }
        iterator = data.iterator();
    }

    private void flattern(NestedInteger ni) {
        if (ni.isInteger()) {
            data.add(ni.getInteger());
        } else {
            for (NestedInteger subNi : ni.getList()) {
                flattern(subNi);
            }
        }
    }

    @Override
    public Integer next() {
        return iterator.next();
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    public void remove() {

    }
}