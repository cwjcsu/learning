package com.cwjcsu.projecteuler.util;

/**
 * ����s��Ǯ�ҵ���ֵ(s1<s2<s3<...)�� ����ÿ��Ǯ�Ҷ����㹻��ģ���ô�ж����з�ʽ��������ϳ�n
 * 
 * @param n
 * @param m
 * @return
 */
public class CoinChange {
	/**
	 * 
	 * @param n
	 *            ��Ҫ��ɵ�Ǯ������
	 * @param m
	 *            s�п���ʹ�õ���ֵ���������ޣ�Ҳ���Ǵ�0-m����ʾ����ֵ������ʹ�á�
	 *            <P>
	 *            �㷨�ĵݹ��߼��������ģ����п��ܵ������C(n,m)���Էֳ����ࣺ��1�����û�а�����ֵs[m]��Ǯ�ң���2��
	 *            ������ٰ���һ����ֵΪs[m]��Ǯ�ң�����C(n,m)=C(n,m-1)+C(n-s[m],m)
	 *            </p>
	 * @param s
	 * @return
	 */
	static int count(int n, int m, int[] s) {
		if (n == 0)
			return 1;
		if (n < 0)
			return 0;
		if (m < 0 && n >= 1)
			return 0;
		return count(n, m - 1, s) + count(n - s[m], m, s);
	}

	public static void main(String[] args) {
		p77();
	}

	// 73682
	public static void p36() {
		int[] s = { 1, 2, 5, 10, 20, 50, 100, 200 };
		System.out.println(count(200, s.length - 1, s));
	}

	public static void p77() {
		int[] s = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53,
				59, 61, 67, 71, 73, 79 };
		for (int t = 11;; t++) {
			int r = count(t, s.length - 1, s);
			if (r > 5000) {
				System.out.println(t);
				break;
			}
		}
	}
}
