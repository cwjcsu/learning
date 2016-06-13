/*$Id: $
 * author   date   comment
 * cwjcsu@gmail.com  2015年10月26日  Created
*/
package com.cwjcsu.thirdparty.leetcode;

public class ListNode {
	public int val;
	public ListNode next;

	public ListNode(int x) {
		val = x;
	}

	public ListNode(int x, ListNode next) {
		this(x);
		this.next = next;
	}

	@Override
	public String toString() {
		return val + (next != null ? ("->" + next) : "");
	}
}
