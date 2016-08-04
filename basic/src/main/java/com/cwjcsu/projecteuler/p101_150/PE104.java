package com.cwjcsu.projecteuler.p101_150;

import com.cwjcsu.projecteuler.util.Util;

/**
 * http://www.mathblog.dk/project-euler-104-fibonacci-pandigital/
 * 
 * ������Ӧ�ÿɽ�
 * 
 * Fibonacci���м���http://en.wikipedia.org/wiki/Fibonacci_number<br/>
 * ���������ʽ��
 * 
 * @author atlas
 * @date 2013-4-26
 */
public class PE104 {
	static final int ONE_BILLION = 1000000000;
	static final double LOG10 = Math.log(10);

	public static void main(String[] args) {
		long fIm1, fIm2;
		int fI;
		fIm1 = 1;
		fIm2 = 1;
		double logPhi = Math.log(0.5 * (Math.sqrt(5.0) + 1)) / LOG10;
		double logSqrt5 = 0.5 * Math.log(5) / LOG10;
		double logFib = 2 * logPhi - logSqrt5;
		int i = 2;
		String s;
		do {
			fI = (int) (fIm1 + fIm2) % ONE_BILLION;
			fIm2 = fIm1;
			fIm1 = fI;
			i++;
			logFib += logPhi;
			logFib = fracPart(logFib);
			s = String.valueOf(fI);
			if (s.length() > 9)
				s = s.substring(s.length() - 9, s.length());
		} while (!Util.isPandigital1_9(fI)
				|| !Util.isPandigital1_9(findLeftEnd(logFib)));

		System.out.println("The answer is " + (i));
		System.out.println("Left end:  " + findLeftEnd(logFib));
		System.out.println("Right end: " + s);
	}

	private static double fracPart(double x) {
		return x - (long) x;
	}

	private static int findLeftEnd(double logFib) {
		long leftEnd = (long) (Math.exp(LOG10 * logFib) * 1.0E8);
		return (int) leftEnd;
	}

}
