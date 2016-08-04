/*$Id: $
 --------------------------------------
  Skybility
 ---------------------------------------
  Copyright By Skybility ,All right Reserved
 * author   date   comment
 * Atlas  2015年10月11日  Created
*/
package com.cwjcsu.projecteuler.p300_400;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PE345 {

	public static int[][] M = { //
			{ 7, 53, 183, 439, 863 }, //
			{ 497, 383, 56, 79, 973 }, //
			{ 287, 63, 343, 169, 583 }, //
			{ 627, 343, 773, 959, 943 }, //
			{ 767, 473, 103, 699, 303 } };

	public static int [][] M1 = {
			 {7,53,183,439,863,497,383,563,79,973,287,63,343,169,583}
			,{627,343,773,959,943,767,473,103,699,303,957,703,583,639,913}
			,{447,283,463,29,23,487,463,993,119,883,327,493,423,159,743}
			,{217,623,3,399,853,407,103,983,89,463,290,516,212,462,350}
			,{960,376,682,962,300,780,486,502,912,800,250,346,172,812,350}
			,{870,456,192,162,593,473,915,45,989,873,823,965,425,329,803}
			,{973,965,905,919,133,673,665,235,509,613,673,815,165,992,326}
			,{322,148,972,962,286,255,941,541,265,323,925,281,601,95,973}
			,{445,721,11,525,473,65,511,164,138,672,18,428,154,448,848}
			,{414,456,310,312,798,104,566,520,302,248,694,976,430,392,198}
			,{184,829,373,181,631,101,969,613,840,740,778,458,284,760,390}
			,{821,461,843,513,17,901,711,993,293,157,274,94,192,156,574}
			,{34,124,4,878,450,476,712,914,838,669,875,299,823,329,699}
			,{815,559,813,459,522,788,168,586,966,232,308,833,251,631,107}
			,{813,883,451,509,615,77,281,613,459,205,380,274,302,35,805}
	};

	public static void main(String[] args) {
		System.out.println(maxMatrixSum(M1));
	}

	public static int maxMatrixSum(int[][] M) {
		return maxMatrixSum(M, new HashSet<Integer>(), new HashSet<Integer>());
	}

	public static int maxMatrixSum(int[][] M, Set<Integer> rows, Set<Integer> cols) {
		int s = 0;
		int L = M.length;
		for (int i = 0; i < L; i++) {
			if (rows.contains(i)) {
				continue;
			}
			for (int j = 0; j < L; j++) {
				if (cols.contains(j)) {
					continue;
				}
				if (rows.size() == L - 1) {
					return M[i][j];
				}
				Set<Integer> rows1 = new HashSet<Integer>(rows);
				Set<Integer> cols1 = new HashSet<Integer>(cols);
				rows1.add(i);
				cols1.add(j);
				int tmp = M[i][j]
						+ maxMatrixSum(M, Collections.unmodifiableSet(rows1), Collections.unmodifiableSet(cols1));
				if (tmp > s) {
					s = tmp;
				}
			}
		}
		return s;
	}
}
