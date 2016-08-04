package com.cwjcsu.projecteuler.p51_100;

/**
 * http://blog.dreamshire.com/2011/01/23/project-euler-problem-64-solution/
 * �������һ����������ĵط���Ӧ������ÿ�η����һ��������ʣ�µ�����ܳ���1������Ҫ����0����ô�ֽⷽʽֻ��һ�֣����ҿ����ñ�̵ķ�ʽȷ���ֽ�
 * 
 * @author Sunny 1322
 */
public class Problem64 {
	public static void main(String[] args) {
		int N = 10000;
		int sum = 0;
		boolean debug = false;
		for (int i = 2; i <= N; i++) {
			if (i == 23) {
				System.out.println(i);
				debug = true;
			} else {
				debug = false;
			}
			int limit;
			int r = limit = (int) Math.sqrt(i);
			if (limit * limit == i) {
				continue;
			}
			int k = 1;
			int period = 0;
			/**
			 * (sqrt(i)-r)/k
			 */
			while (k != 1 || period == 0) {
				k = (i - r * r) / k;
				r = ((limit + r) / k) * k - r;// ?
				period += 1;
				if (debug) {
					System.out.println("K:" + k + ",R:" + r);
				}
			}
			if (period % 2 == 1) {
				sum += 1;
			}
		}
		System.out.println(sum);
	}
}
