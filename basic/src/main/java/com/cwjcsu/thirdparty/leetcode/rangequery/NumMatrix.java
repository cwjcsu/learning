/*$Id: $
 * author                     date    comment
 * cwjcsu@gmail.com  2015年11月12日  Created
 */
package com.cwjcsu.thirdparty.leetcode.rangequery;

/**
 * 304 算法描述
 *
 * sum矩阵保存的每一行都是一个求和行，即sum[i][j]=matrix[i][0]+matrix[i][1]+...+matrix[i][j]
 * 于是，求中间子矩阵的和，就转化成子矩阵每一行求其中一段的和，从而转换成NumArray.java描述的算法
 *
 * @author atlas
 *
 */
public class NumMatrix {

	int[][] matrix;
	int[][] sum;

	public NumMatrix(int[][] matrix) {
		this.matrix = matrix;
		this.sum = new int[this.matrix.length][];
		for (int i = 0; i < matrix.length; i++) {
			int[] row = matrix[i];
			int[] sumRow = new int[row.length];
			this.sum[i] = sumRow;
			for (int j = 0; j < row.length; j++) {
				int prev = j > 0 ? sumRow[j-1] : 0;
				sumRow[j] = prev + row[j];
			}
		}

	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		int sum = 0;
		for (int i = row1; i <= row2; i++) {
			int[] sumRow = this.sum[i];
			if (col1 == 0) {
				sum += sumRow[col2];
			} else {
				sum += (sumRow[col2] - sumRow[col1 - 1]);
			}
		}
		return sum;
	}

}
