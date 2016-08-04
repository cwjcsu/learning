package com.cwjcsu.projecteuler.p101_150;

import java.util.BitSet;

import com.cwjcsu.projecteuler.util.Util;

/**
 * ����http://www.mathblog.dk/project-euler-131-primes-perfect-cube/
 * ���Ƶ��������Եó�p=(i+1)<sup>3</sup>-i<sup>3</sup>, ����i�ĵ����ģ�
 * ��i=577ʱ�����Եõ�p=1000519��������Ҫ���i=1��577��
 * 
 * @author atlas
 * @date 2013-4-28
 */
public class PE131 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int N = 1000000;
		BitSet primes = Util.getPrimeDecisionBlow2(N);
		int c = 0;
		for (int i = 1; i < 577; i++) {
			if (primes.get((int) (Util.power(i + 1, 3) - Util.power(i, 3))))
				c++;
		}
		System.out.println(c);
	}

}
