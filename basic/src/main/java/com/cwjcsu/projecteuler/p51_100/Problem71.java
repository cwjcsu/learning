package com.cwjcsu.projecteuler.p51_100;
public class Problem71 {
	public static void main(String[] args) {
		do1();
	}
	//Max:0.42857128571385716,3/7=0.42857142857142855,N/D:428570/999997
	static void do1() {
		double Max = 0;
		int N = 0, D = 0;
		for (int i = 1; i <= 1000000; i++) {
			int n = (int) Math.ceil(3d * i / 7) - 1;
//			if (Util.gcd(n, i) != 1) {
//				continue;
//			}
			double tmp = 1d * n / i;
			if (tmp > Max) {
				Max = tmp;
				N = n;
				D = i;
			}
		}
		System.out.println("Max:" + Max + ",3/7=" + (3d / 7) + ",N/D:" + N + "/"
				+ D);
	}
}
