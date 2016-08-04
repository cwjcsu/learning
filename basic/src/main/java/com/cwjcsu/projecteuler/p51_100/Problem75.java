package com.cwjcsu.projecteuler.p51_100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.cwjcsu.projecteuler.util.Util;

/**
 * http://baike.baidu.com/view/148142.htm
 * 
 * @author sunny
 * 
 */
public class Problem75 {

	/**
	 * 暴力法1,没利用任何性质，估计要几天才能算法。
	 * 
	 * @return
	 */
	public static int do2() {
		int L = 1500000;// 平方超出了
		int s = 0;
		for (int l = 12; l <= L; l++) {
			int got = 0;
			if (l % 1000 == 0) {
				System.out.println(l);
			}
			int a1 = 1, a2 = l / 3;
			LOOP: for (int a = a1; a <= a2; a++) {
				int b1 = a + 1, b2 = l / 2;
				for (int b = b1; b <= b2; b++) {
					int c1 = Math.max(b + 1, l / 3);
					int c2 = l - 3;
					for (int c = c1; c <= c2; c++) {
						if (Util.gouGuShu(a, b, c)) {
							got++;
							if (got > 1) {
								break LOOP;
							}
						}
					}
				}
			}
			if (got == 1) {
				s += 1;
			}
		}
		return s;
	}

	/**
	 * a = m^2 − n^2, b = 2mn, c = m^2 + n^2 <br>
	 * L=a+b+c=2mn+2m^2=2m(m+n) ,fail,因为上面的条件：1,可以导出所有勾股素数和部分派生勾股数，但不能导出所有派生勾股数
	 * 
	 * @param args
	 */
	public static int do1() {

		int L = 1500000;
		int count = 0;
		for (int l = 120; l <= L; l += 2) {
			int innerCount = 0;
			boolean got = false;
			int mb = Util.maxIntLesserThan(Math.sqrt(l / 2));
			int ma = Util.minIntGreaterThan(Math.sqrt(l) / 2);
			int B = l / 2;
			for (int m = ma; m <= mb; m++) {
				if (B % m == 0) {
					innerCount++;
					if (innerCount > 1) {
						break;
					}

				}
			}
			got = (innerCount == 1);
			if (got) {
				count += 1;
			}
		}
		return count;
	}

	/**
	 * OK, a = m^2 − n^2, b = 2mn, c = m^2 + n^2 <br>
	 * L=2m(m+n)=>m<sqrt(L/2);n<m,
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Map<Integer, ABC> dict = new HashMap<Integer, ABC>();
		int L = 1500000;
		int M = Util.maxIntLesserThan(Math.sqrt(L / 2));
		for (int m = 1; m <= M; m++) {
			for (int n = 1; n < m; n++) {
				int a = m * m - n * n;
				int b = 2 * m * n;
				int c = m * m + n * n;
				int l = a + b + c;
				int k = L / l;
				for (int i = 1; i <= k; i++) {
					int[] abc = { i * a, i * b, i * c };
					Arrays.sort(abc);
					check(i, l, abc, dict);
				}
			}
		}
		int count = 0;
		for (ABC abc : dict.values()) {
			count += abc.got;
		}
		System.out.println(count);
	}

	private static void check(int i, int l, int[] abc, Map<Integer, ABC> dict) {
		int ll = i * l;
		ABC tmp = dict.get(ll);
		if (tmp != null) {
			if (tmp.got == 1) {
				int[] tmp1 = tmp.abc;
				for (int x = 0; x < 3; x++) {
					if (tmp1[x] != abc[x]) {
						tmp.got = 0;
						break;
					}
				}
			}
		} else {
			dict.put(ll, new ABC(abc));
		}
	}

	static class ABC {
		public ABC(int[] abc) {
			this.abc = abc;
		}

		int got = 1;
		int[] abc;

	}
}
