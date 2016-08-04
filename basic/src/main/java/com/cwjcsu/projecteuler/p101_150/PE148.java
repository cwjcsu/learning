package com.cwjcsu.projecteuler.p101_150;

/**
 * see http://fbarbuto.wordpress.com/2011/07/16/problem-148/
 * 
 * If n (the row number, starting with 0) is written in base 7, that is, as
 * a0*7⁰ + a1*7¹ + a2*7² + … + an*7n, the number of non-divisibles in that row
 * is equal to (a0 + 1)*(a1 + 1)*(a2 + 1)* … *(an + 1). Example: take row 8992.
 * In base 7, the number 8992 is represented as 35134. There are therefore
 * (3+1)*(5+1)*(1+1)*(3+1)*(4+1) = 960 non-divisibles by seven in row 8992.
 * 
 * @author atlas
 * @date 2013-10-23
 */
public class PE148 {

	// 2129970655314432
	public static void main(String[] args) {
		int N = 1000000000;
		long sum = 0;
		for (int i = 0; i < N; i++) {
			String I = Integer.toString(i, 7);
			sum += sumUp(I);
		}
		System.out.println(sum);
	}

	public static int sumUp(String I) {
		int s = 1;
		for (int i = 0; i < I.length(); i++) {
			s *= (I.charAt(i) - '0' + 1);
		}
		return s;
	}
}
