package com.cwjcsu.projecteuler.p51_100;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class P76整数划分 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// System.out.println(f(100, 99));
		// System.out.println(f(5));
		p78();
	}

	/**
	 * 递归法， f(1,m)=1; f(n,1)=1; f(n,n)=1+f(n,n-1); f(n,m)=f(n-m,m)+f(n,m-1);
	 * 
	 * @return
	 */
	public static int f(int n, int m) {
		if (n == 1 || m == 1) {
			return 1;
		}
		if (n < m) {
			return f(n, n);
		} else if (n == m) {
			return 1 + f(n, n - 1);
		} else {
			return f(n - m, m) + f(n, m - 1);
		}
	}

	public static int f(int n) {
		return f(n, n);
	}

	// /===================== for p 78

	public static int f(int n, int m, Map<F, Integer> fs) {
		F f = new F(n, m);
		Integer value = fs.get(f);
		if (value != null) {
			return value;
		}
		if (n == 1 || m == 1) {
			value = 1;
		}
		if (n < m) {
			value = f(n, n);
		} else if (n == m) {
			value = 1 + f(n, n - 1);
		} else {
			value = f(n - m, m) + f(n, m - 1);
		}
		if (fs.size() < 1000000)
			fs.put(f, value);
		return value;
	}

	public static int f(int n, Map<F, Integer> fs) {
		return f(n, n, fs);
	}

	public static void p78() {
		int L = 1000000;
		Map<F, Integer> fs = new HashMap<F, Integer>(1000000);
		for (int i = 10;; i++) {
			if (f(i, fs) % L == 0) {
				System.out.println(i);
				break;
			}
		}
	}

	static class F {
		int n, m;

		public F(int n, int m) {
			super();
			this.n = n;
			this.m = m;
		}

		@Override
		public int hashCode() {
			return super.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof F)) {
				return false;
			}
			F f = (F) obj;
			return n == f.n && m == f.m;
		}

	}
}
