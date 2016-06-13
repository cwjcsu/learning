package com.cwjcsu.learning.algorithm;

import java.util.Random;

/**
 * 
 * @author atlas
 * @date 2012-9-25
 */
public class ManWoman {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random r = new Random();
		// true for man, false for woman
		long man = 0;
		long wm = 0;
		for (int i = 0; i < 100000000; i++) {
			while (r.nextBoolean()) {//man
				man++;
			}
			wm++;
		}
		System.out.println("man/wm:"+(1.0*man/wm));
	}
}
