/*$Id: $
 --------------------------------------
  Skybility
 ---------------------------------------
  Copyright By Skybility ,All right Reserved
 * author                     date    comment
 * chenweijun@skybility.com  2015年10月22日  Created
 */
package com.cwjcsu.projecteuler.util;

/**
 * @see http://stackoverflow.com/questions/951848/java-array-sort-quick-way-to-get-a-sorted-list-of-indices-of-an-array
 * 不能改变data数组
 * @author atlas
 *
 */
public class IndexSorter2 {

	/**
	 * index[i]初始值为i 排序后，data[index[i]]<data[index[i+1]]
	 * 
	 * @param main
	 * @param index
	 */
	public static void quicksort(int[] data, int[] index) {
		quicksort(data, index, 0, index.length - 1);
	}

	// quicksort a[left] to a[right]
	public static void quicksort(int[] a, int[] index, int left, int right) {
		if (right <= left)
			return;
		int i = partition(a, index, left, right);
		quicksort(a, index, left, i - 1);
		quicksort(a, index, i + 1, right);
	}

	// partition a[left] to a[right], assumes left < right
	private static int partition(int[] a, int[] index, int left, int right) {
		int i = left - 1;
		int j = right;
		while (true) {
			while (less(a[index[++i]], a[index[right]]))
				// find item on left to swap
				; // a[right] acts as sentinel
			while (less(a[index[right]], a[index[--j]]))
				// find item on right to swap
				if (j == left)
					break; // don't go out-of-bounds
			if (i >= j)
				break; // check if pointers cross
			exch(a, index, i, j); // swap two elements into place
		}
		exch(a, index, i, right); // swap with partition element
		return i;
	}

	// is x < y ?
	private static boolean less(int x, int y) {
		return (x < y);
	}

	// exchange a[i] and a[j]
	private static void exch(int[] a, int[] index, int i, int j) {
//		int swap = a[index[i]];
//		a[i] = a[j];
//		a[j] = swap;
		int b = index[i];
		index[i] = index[j];
		index[j] = b;
	}

}
