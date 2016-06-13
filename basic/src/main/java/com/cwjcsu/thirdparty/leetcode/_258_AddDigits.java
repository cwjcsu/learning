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
public class _258_AddDigits {

	public int addDigits(int num) {
		int sum = 0;
		while (num > 0) {
			sum += num % 10;
			num /= 10;
		}
		if (sum <= 9) {
			return sum;
		}
		return addDigits(sum);
	}
}
