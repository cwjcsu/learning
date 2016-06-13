/*$Id: $
 * author                     date    comment
 * cwjcsu@gmail.com  2015年11月2日  Created
 */
package com.cwjcsu.thirdparty.leetcode;

import java.util.Stack;

/**
 * 
 * @author atlas
 *
 */
public class MinStack {

	private Stack<Integer> data = new Stack<Integer>();
	private Stack<Integer> min = new Stack<Integer>();

	private Integer currentMin;

	public void push(int x) {
		data.push(x);
		if (currentMin == null) {
			currentMin = x;
		} else {
			currentMin = Math.min(currentMin, x);
		}
		min.push(currentMin);
	}

	public void pop() {
		data.pop();
		min.pop();
		if (min.size() > 0) {
			currentMin = min.peek();
		} else {
			currentMin = null;
		}
	}

	public int top() {
		return data.peek();
	}

	public int getMin() {
		return min.peek();
	}

}
