package com.cwjcsu.projecteuler.p1_50;

import com.cwjcsu.projecteuler.util.Util;

/**
 * n�����ֵ��������pow(10,n)-1,�����ֵĽ׳˺͵����ֵ��362880*n �����Ƴ�n<=8
 * 
 * @author Sunny
 * 
 */
public class Problem34 {
	public static void main(String[] args) {
		int s = 0;
		for (int i = 3; i <= 9999999; i++) {
			if (i == getDigitsFactorial(i)) {
				s += i;
			}
		}
		System.out.println(s);
	}

	static int getDigitsFactorial(int i) {
		int[] d = Util.toDigit(i);
		int s = 0;
		for (int j = 0; j < d.length; j++) {
			s += Util.digitFactorial(d[j]);
		}
		return s;
	}
}
