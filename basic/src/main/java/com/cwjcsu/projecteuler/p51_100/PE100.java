package com.cwjcsu.projecteuler.p51_100;

/**
 * http://www.mathblog.dk/project-euler-100-blue-discs-two-blue/
 * 就是一个二元方程，可以求出递推通项：http://www.alpertron.com.ar/QUAD.HTM，牛B 假设n是盘总数，b是绿盘数，
 * 可以求出递推式为：<br/>
 * b<sub>k+1</sub>=3b<sub>k</sub>+2n<sub>k</sub> -2;
 * n<sub>k+1</sub>=4b<sub>k</sub>+3n<sub>k</sub>-3;
 *
 * @author atlas
 * @date 2013-4-26
 */
public class PE100 {

	/**
	 * There are 756872327473 blues
	 * @param args
	 */
	public static void main(String[] args) {
		long b = 15;
		long n = 21;
		long target = 1000000000000L;

		while (n < target) {
			long btemp = 3 * b + 2 * n - 2;
			long ntemp = 4 * b + 3 * n - 3;
			b = btemp;
			n = ntemp;
		}
		System.out.println(b);
	}

}
