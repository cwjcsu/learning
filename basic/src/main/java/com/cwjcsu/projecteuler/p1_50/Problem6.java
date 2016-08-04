package com.cwjcsu.projecteuler.p1_50;
public class Problem6 {
	public static void main(String[] args) {
		int a=25502500;
		System.out.println(diff(100));
	}

	static long diff(int n) {
		long s = 0;
		int b = 0;
		for (int i = 1; i <= n; i++) {
			s += i * i;
			b += i;
		}
		return b*b - s;

	}
}
