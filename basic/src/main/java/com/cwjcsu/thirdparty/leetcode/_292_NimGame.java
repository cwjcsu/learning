/*$Id: $
 * author                     date    comment
 * cwjcsu@gmail.com  2015年10月26日  Created
 */
package com.cwjcsu.thirdparty.leetcode;

/**
 * 解析：如果是4的倍数，那么不管，我拿掉多少（1,2,3），对方拿掉(3,2,1)，总是能保持每一轮剩下4的倍数个，从而最后被他拿，从而他赢。
 * 
 * @author atlas
 *
 */
public class _292_NimGame {

	public boolean canWinNim(int n) {
		return n % 4 != 0;
	}

	public static void main(String[] args) {

	}
}
