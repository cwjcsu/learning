/*$Id: $
 --------------------------------------
  Skybility
 ---------------------------------------
  Copyright By Skybility ,All right Reserved
 * author   date   comment
 * Atlas  2015年10月12日  Created
*/
package com.cwjcsu.projecteuler.p300_400;

public class PE346 {

	public static void main(String[] args) {

	}
	public static long sumRepunit(long n){
		return 0;
	}
	
	public static boolean isRepunit(long n) {
		long sum = 0;
		int c = 0;
		for (int i = 0; i <= 10; i++) {
			String s = Long.toString(n, 2);
			c++;
			if (c >= 2) {
				return true;
			}
		}
		return false;
	}

	public static boolean is1111(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != '1') {
				return false;
			}
		}
		return true;
	}
}
