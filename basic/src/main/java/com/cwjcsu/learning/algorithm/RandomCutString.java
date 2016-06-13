/*$Id: $
 * author                     date    comment
 * cwjcsu@gmail.com  2015年5月15日  Created
 */
package com.cwjcsu.learning.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 用编程的方式验证：<br/>
 * 一根1米长的绳子，随机切N刀，变成(N+1)根绳子，最短的一根绳子长度的期望是1/(N+1)^2
 * 
 * @author atlas
 *
 */
public class RandomCutString {
	/**
	 * 随机切n刀以后，返回最短绳子的长度
	 * 
	 * @param n
	 * @return
	 */
	public static double cut(int n) {
		List<Double> cutPoints = new ArrayList<Double>();
		cutPoints.add(0.0);
		cutPoints.add(1.0);
		for (int i = 0; i < n; i++) {
			cutPoints.add(Math.random());
		}
		Collections.sort(cutPoints);
		double min = 1;
		for (int i = 0; i < cutPoints.size() - 1; i++) {
			Double v1 = cutPoints.get(i);
			Double v2 = cutPoints.get(i + 1);
			min = Math.min(min, Math.abs(v1 - v2));
		}
		return min;
	}

	public static void main(String[] args) {
		int n = 100000;
		double except = 1.0 / ((n + 1) * (n + 1));
		double actual = cut(n);
		System.out.println(n + "=>" + except + " : "
				+ Math.abs(except - actual));
//		for (n = 10000; n < 10001; n++) {
//			double except = 1.0 / ((n + 1) * (n + 1));
//			double actual = cut(n);
//			System.out.println(n + "=>" + except + " : "
//					+ Math.abs(except - actual));
//		}
	}
}
