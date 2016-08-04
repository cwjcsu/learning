package com.cwjcsu.projecteuler.p51_100;

import com.cwjcsu.projecteuler.util.Util;

/**
 * 很好的算法：http://www.mathblog.dk/project-euler-91-right-angle-triangles-quadrant/
 *
 * @author atlas
 * @date 2013-4-26
 */
public class PE91 {
	public static void main(String[] args) {
		int size = 50;
		int result = size * size * 3;
		for (int x = 1; x <= size; x++) {
			for (int y = 1; y <= size; y++) {
				int fact = Util.gcd(x, y);
				result += Math.min(y * fact / x, (size - x) * fact / y) * 2;
			}
		}
		System.out.println(result);
	}

}
