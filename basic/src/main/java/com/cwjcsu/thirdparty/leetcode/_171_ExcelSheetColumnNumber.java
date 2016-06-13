/*$Id: $
 * author                     date    comment
 * cwjcsu@gmail.com  2015年10月26日  Created
 */
package com.cwjcsu.thirdparty.leetcode;

/**
 * 
 * @author atlas
 *
 */
public class _171_ExcelSheetColumnNumber {
	public static int titleToNumber(String s) {
		int n = 0;
		int m = 1;
		for (int i = s.length() - 1; i >= 0; i--) {
			char c = s.charAt(i);
			int col = c - 'A' + 1;
			n += m * col;
			m *= 26;
		}
		return n;
	}

	public static void main(String[] args) {
		System.out.println(titleToNumber("AB"));
	}

}
