package com.cwjcsu.thirdparty.javaeye;

import java.util.Arrays;
import java.util.Collections;

/**
 * 
 * @author atlas
 * @date 2013-4-17
 */
public class Ex24 {
	public static void main(String[] args) {
		int i, n = 100;
		Integer[] arr = Collections.nCopies(n, 0).toArray(new Integer[0]);
		// int[] array = {0};
		for (int j = 0; j < n; j++) {
			arr[j] = (int) (Math.random() * 365 + 1);
			// System.out.print(j + "   " + arr[j] + "\n");
		}
		Arrays.sort(arr); // sort the arr
		// String arrq = (Arrays.toString(arr));
		System.out.println("\n");

		Duplicate(arr, n);
	}

	static void Duplicate(Integer[] arr, int n) {
		int j = 0;
		for (int i = 0; i < n - 1; i++) {

			if (arr[i] != arr[i + 1]) {
				System.out.println(" at i   (" + i + ")    print arr[i] "
						+ arr[i]);
			} else {
				j = j + 1;
				System.out.println("  j =  " + j + "  at i (" + i
						+ ")  arr[i] is double at date  " + arr[i]);
			}
		}
	}
}
