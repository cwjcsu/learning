/*$Id: $
 --------------------------------------
  Skybility
 ---------------------------------------
  Copyright By Skybility ,All right Reserved
 * author   date   comment
 * Atlas  2015年10月12日  Created
*/
package com.cwjcsu.projecteuler.p51_100;

import com.cwjcsu.projecteuler.util.Util;

public class PE86 {

	/**
	 * http://oeis.org/A143714
	 * 
	 * (a+b)^2 + n^2 is a square a<=b<=n
	 * 
	 * 把立方体目标点所在的三个面分别推倒与起点构成一个长方形，可以用勾股定理得到。
	 * 
	 * taking a cube of size (x y z) with x <= y <= z, the shortest path will
	 * always be path(x,y,z) = sqrt( (x+y)^2 + z^2 )（很容易证明）
	 * 
	 * so the result for any M is counting all the different instances for x y z
	 * satisfying 1 <= x <= y <= z <= M where path(x,y,z) is an integer
	 * 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {// 1818,暴力法
		int S = 1000000;
		System.out.println(find(S));
	}

	public static long find(int S) {
		int sum = 0;
		for (int n = 1;; n++) {
			// loop 1<=a<=b<=n
			for (int a = 1; a <= n; a++) {
				for (int b = a; b <= n; b++) {
					long s = (a + b) * (a + b) + n * n;
					if (Util.isSquare(s)) {
						sum++;
					}
					if (sum >= S) {
						return n;
					}
				}
			}
		}
	}

	public static long find2(int S) {
		int sum = 0;
		for (int n = 1;; n++) {// set i = a+b,then 2<= i <=2*n 	
			// loop from 2 ~ 2*n
			for (int i = 2; i <= 2 * n; i++) {
				long s = i * i + n * n;
				if (Util.isSquare(s)) {
					sum++;
				}
				if (sum >= S) {
					return n;
				}
			}
		}
	}
}
