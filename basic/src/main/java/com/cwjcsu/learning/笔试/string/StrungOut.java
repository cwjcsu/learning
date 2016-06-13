package com.cwjcsu.learning.笔试.string;

/**
 * 
 * @author atlas
 * @date 2013-4-22
 */
public class StrungOut {
	public static void main(java.lang.String[] args) {
		String s = new String("Hello world");
		System.out.println(s);
	}
}

class String {
	private final java.lang.String s;

	public String(java.lang.String s) {
		this.s = s;
	}

	public java.lang.String toString() {
		return s;
	}
}
