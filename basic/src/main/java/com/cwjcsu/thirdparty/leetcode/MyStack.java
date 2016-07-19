package com.cwjcsu.thirdparty.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Atlas on 2016/7/19.
 */
public class MyStack {
    List<Integer> queues = new ArrayList<Integer>();
    // Push element x onto stack.
    public void push(int x) {
        queues.add(x);
    }

    // Removes the element on top of the stack.
    public void pop() {
        queues.remove(queues.size()-1);
    }

    // Get the top element.
    public int top() {
        return queues.get(queues.size() - 1);
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return queues.isEmpty();
    }
}
