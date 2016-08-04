package com.cwjcsu.projecteuler.p51_100;
import com.cwjcsu.projecteuler.util.Util;

public class Problem52 {
	public static void main(String[] args) {
		for (int i = 1;; i++) {
			int thiz = Util.getDigitPos(i);
			int j = 2;
			for (; j <= 6; j++) {
				if (thiz != Util.getDigitPos(i * j)) {
					break;
				}
			}
			if (j == 7) {
				System.out.println(i);
				for (j = 1; j <= 6; j++) {
					System.out.println(i * j);
				}
				break;
			}
		}
	}
}
