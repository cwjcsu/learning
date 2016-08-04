package com.cwjcsu.projecteuler.p1_50;
// 1+3+7+13+21+31
// 1 +5+9+17+25+37
public class Problem28 {
	public static void main(String[] args) {
		do1(1001);//669171001
	}

	static void do1(int e) {
		int M = e * e;
		int s = 0;
		int d1 = 1;
		int d2 = 1;
		for (int i = 0;; i++) {
			d1 = d1 + i * 2;
			if (d1 > M) {
				break;
			}
			System.out.println(d1);
			s += d1;
		}
		for (int i = 1;; i++) {
			d2 = d2 + (i / 2) * 4;
			if (d2 >M) {
				break;
			}
			System.out.println(d2);			
			s += d2;
		}
		System.out.println("S:"+(s-1));
	}
	
}
