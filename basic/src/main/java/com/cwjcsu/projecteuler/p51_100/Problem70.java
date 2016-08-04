package com.cwjcsu.projecteuler.p51_100;

import com.cwjcsu.projecteuler.util.Util;

public class Problem70 {
	/**
	 * 8319823
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int n = 10000000;
		int[] phi = Util.phi(n);
		double Min = 1000;
		int N = 0;
		for (int i = 2; i < phi.length; i++) {
			int phii = phi[i];
			if (Util.isPermutation(phii, i)) {
				double t = 1D * i / phi[i];
				if (t < Min) {
					Min = t;
					N = i;
				}
			}
		}
		System.out.println("n,phi(n),n/phi[n]:" + N + "," + phi[N] + "," + Min);
	}

	static boolean isPermutation(int n1, int n2) {
		int[] thisd = new int[10];
		while (n1 > 0) {
			thisd[n1 % 10]++;
			n1 /= 10;
		}
		while (n2 > 0) {
			thisd[n2 % 10]--;
			n2 /= 10;
		}
		for (int i = 0; i < thisd.length; i++) {
			if (thisd[i] != 0) {
				return false;
			}
		}
		return true;
	}
}
