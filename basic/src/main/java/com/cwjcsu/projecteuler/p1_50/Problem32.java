package com.cwjcsu.projecteuler.p1_50;

import java.util.HashSet;
import java.util.Set;

import com.cwjcsu.projecteuler.util.Util;

/**
 * brute force
 * 
 * @author Sunny
 * 
 */
public class Problem32 {
	/**
	 * ֻ只可能是a 1 digit number times a 4 digit number or a 2 digit number times a 3
	 * digit number
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Set<Integer> p = new HashSet<Integer>();
		for (int i = 1; i <= 99; i++) {
			if (i <= 9) {
				for (int j = 1000; j < 9999; j++) {
					if (Util.isPandigital1_9("" + i + j + i * j)) {
						p.add(i * j);
					}
				}
			} else {
				for (int j = 100; j < 999; j++) {
					if (Util.isPandigital1_9("" + i + j + i * j)) {
						p.add(i * j);
					}
				}
			}
		}
		int s = 0;
		for (Integer i : p) {
			s += i;
		}
		System.out.println(s);
	}
}
