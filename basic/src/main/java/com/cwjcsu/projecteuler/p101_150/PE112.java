package com.cwjcsu.projecteuler.p101_150;

/**
 * 
 * @author atlas
 * @date 2013-4-25
 */
public class PE112 {
	public static void main(String[] args) {
		System.out.println(isBouncy(12221));
		int bcCount = 0;
		for (int i = 1; i <= 21780; i++) {
			if (isBouncy(i))
				bcCount++;
		}
		System.out.println(bcCount);
		System.out.println(bcCount * 1.0 / 21780);

	}

	static boolean isBouncy(int x) {
		if (x <= 100) {
			return false;
		}
		int d1 = x % 10;
		x /= 10;
		int d2 = x % 10;
		/*
		 * d1-d2-d3˳�� 0:δȷ��˳�� 1������ -1������
		 */
		int o = 0;
		while (x > 0) {
			x /= 10;
			int d3 = x % 10;
			if (o < 0 && d3 > d2) {
				return true;
			} else if (o > 0 && d3 < d2) {
				return true;
			}
			if (o == 0) {
				o = d2 - d1;
			}
			d1 = d2;
			d2 = d3;
		}
		return false;
	}

	/**
	 * һ����9�������
	 * 
	 * @param d1
	 * @param d2
	 * @param d3
	 * @return -1 ����1������0����ȣ�-2��Bouncy number
	 */
	public static int check(int d1, int d2, int d3) {
		if (d1 <= d2 && d2 <= d3) {
			if (d1 < d3) {// d1<d2<=d3; d1<=d2<d3; d1<d2<d3
				return -1;
			} else {// d1=d2=d3
				return 0;
			}
		} else if (d1 >= d2 && d2 >= d3) {
			if (d1 > d3) {// d1>d2>=d3; d1>=d2>d3; d1>d2>d3
				return 1;
			}
		}
		// d1>d2,d2<d3; d1<d2,d2>d3;
		return -2;
	}
}
