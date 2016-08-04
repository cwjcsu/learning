package com.cwjcsu.projecteuler.p51_100;

import java.util.BitSet;

/**
 * ����һ������a1a2...an��
 * �򾭹�һ����������ֻ���a1<sup>2</sup>+a2<sup>2</sup>+...+an<sup>2</sup
 * >,�����9<sup>2</sup>*n=81*n,<br/>
 * ����ֻҪ��0��81*n��Щ����֮�����д���ֻ��Ҫ����(digitsSquareSum)һ�ξͻ��䵽������䡣
 * 
 * @author atlas
 * 
 */
public class PE92 {
	static int[] X = new int[10];
	static {
		for (int i = 0; i < 10; i++) {
			X[i] = i * i;
		}
	}
	public static int digitsSquareSum(int x) {
		int s = 0;
		while (x > 0) {
			s += X[x % 10];
			x /= 10;
		}
		return s;
	}
	public static void main(String[] args) {
		int N = 10000000;
		int n = 7 * 81;
		BitSet base = new BitSet(n);
		int x = 0;
		int count = 0;
		//N�Ľ��������1
		for (int i = 1; i < N; i++) {
			x = i;
			if (i <= n) {
				while (x != 89 && x != 1) {
					x = digitsSquareSum(x);
				}
				if (x == 89) {
					base.set(i);
					count++;
				}
			} else {
				x = digitsSquareSum(x);
				if (base.get(x)) {
					count++;
				}
			}
		}
		System.out.println(count);
	}
}
