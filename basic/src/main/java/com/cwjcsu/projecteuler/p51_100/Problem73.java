package com.cwjcsu.projecteuler.p51_100;

import com.cwjcsu.projecteuler.util.Util;

/**
 * There are some implementations using the Farey sequence to speed up the
 * search, but didn't see the need for this problem.
 * 
 * @author Sunny
 * 
 */
public class Problem73 {

	public static void main(String[] args) {
		do2();
	}

	/**
	 * 7295372
	 */
	static void do2() {
		int N = 12000;
		int sum = 0;
		for (int d = 5; d <= N; d++) {
			int n1 = d / 3 + 1;
			int n2 = (d - 1) / 2;

			for (int n = n1; n <= n2; n++) {
				if (Util.gcd(n, d) == 1) {
					sum++;
				}
			}
		}
		System.out.println(sum);
	}
}

class RPF {
	int n;
	int d;

	public RPF(int n, int d) {
		this.n = n;
		this.d = d;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof RPF))
			return false;
		RPF rpf = (RPF) obj;
		return this.n == rpf.n && this.d == rpf.d;
	}
}
