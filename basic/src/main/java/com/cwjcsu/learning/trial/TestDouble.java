package com.cwjcsu.learning.trial;
/**
 * 
 * @author atlas
 * @date 2012-10-18
 */
public class TestDouble {
	public static void main(String[] args) {
		System.out.println(new Double(12.1234) == new Double(12.1234));
		System.out.println(new Double(12.1234).equals(new Double(12.1234)));
	}
}
