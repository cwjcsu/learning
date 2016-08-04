package com.cwjcsu.projecteuler.dp;

/**
 * n����Դ���䵽m����Ŀ�ϣ�i��Ŀ����j����Դ�ɻ���a[i,j],�������Ч�档<br>
 * 
 * ��Ϊ��i����Ŀ��������ʱ��ֻ�轫�������i-1����Ŀ��ʣ�µ�������ǰ��Ŀ�õ��ĸ��ַ�������ֻ�������ǰ��Ŀ�ķ�����ȡ���ֵ���ɣ�
 * ����һ����Ŀ����ֻ���������Ľ⡣
 * 
 * @author Sunny
 * 
 */
public class ResourceAssign {
	public static void main(String[] args) {
		int[][] A = { 
				{ 3, 4, 5, 7, 10 }, 
				{ 5, 7, 8, 8, 11 },
				{ 1, 2, 4, 8, 11 } };
		System.out.println(maxBenefit(A));
	}

	public static int maxBenefit(int[][] A) {
		// ��Դ��
		int n = A[0].length;
		// m����Ŀ
		int m = A.length;
		// m[j] ǰ���Ǹ���Ŀ(i-1)������j+1����Դ���ܻ�õ�������棬
		int[][] M = new int[2][n];
		System.arraycopy(A[0], 0, M[0], 0, n);
		
		int N = n - 1;
		for (int i = 1; i < m; i++,N--) {
			for (int j = 0; j < N; j++) {
				M[i % 2][N - (j + 1)] = A[i][N - (j + 1)] + M[(i + 1) % 2][j];
			}
		}

		return 0;
	}
}
