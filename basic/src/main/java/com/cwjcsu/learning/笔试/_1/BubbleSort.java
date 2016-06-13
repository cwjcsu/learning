package com.cwjcsu.learning.笔试._1;

/**
 * 
 * @author atlas
 * @date 2013-4-24
 */
public class BubbleSort {
	static void bubbleSort(int[] a) {
		int temp;
		for (int i = 0; i < a.length; ++i) {
			for (int j = i; j < a.length - 1; ++j) {
				if (a[j] > a[j + 1]) {
					temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
	}

	int binarySearch(int[] a, int value) {
		int low = 0;
		int high = a.length - 1;
		int mid = 0;
		while (low <= high) {
			mid = (low + high) / 2;
			if (a[mid] == value)
				return mid;
			else if (a[mid] > value)
				high = mid - 1;
			else
				low = mid + 1;
		}
		return -1;
	}
}
