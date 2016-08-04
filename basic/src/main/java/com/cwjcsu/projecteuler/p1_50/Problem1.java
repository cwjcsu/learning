package com.cwjcsu.projecteuler.p1_50;
public class Problem1 {
	// 267333
	public static void main(String[] args) {
		int s = 0;
		for (int i = 3; i < 1000; i++) {
			if ((i % 3 == 0) || i % 5 == 0)
				s += i;
		}
		System.out.println(s);
	}
}
