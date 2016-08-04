package com.cwjcsu.projecteuler.p51_100;

import com.cwjcsu.projecteuler.util.Util;

public class Problem69 {

	public static void main(String[] args) {
		int n = 1000000;
		int[] phi = Util.phi(n);
		double Max = 0;
		int N = 0;
		for (int i = 3; i < phi.length; i++) {
			double t = 1D * i / phi[i];
			if (t > Max) {
				Max = t;
				N = i;
			}
		}
		System.out.println(N);
	}
}
