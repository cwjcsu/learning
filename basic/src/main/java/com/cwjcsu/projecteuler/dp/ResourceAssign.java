package com.cwjcsu.projecteuler.dp;


/**
 * n个资源分配到m个项目上，i项目分配j个资源可获益a[i,j],求最大总效益。<br>
 *
 * 当为第i个项目计算最优时，只需将分配给第i-1个项目、剩下的留给当前项目得到的各种方案，和只分配给当前项目的方案中取最大值即可；
 * 而第一个项目就是只分配给自身的解。
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
		// 资源数
		int n = A[0].length;
		// m个项目
		int m = A.length;
		// m[j] 前面那个项目(i-1)分配完j+1个资源后能获得的最大利益，
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