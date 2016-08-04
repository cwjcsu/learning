/*$Id: $
 --------------------------------------
  Skybility
 ---------------------------------------
  Copyright By Skybility ,All right Reserved
 * author                     date    comment
 * chenweijun@skybility.com  2015年10月22日  Created
 */
package com.cwjcsu.projecteuler.util;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 
 * @author atlas
 *
 */
public class IndexSorter {
	final Integer[] index;
	final int[] data;

	/**
	 * index[i]初始值为i
	 * 
	 * 用index 记录data里面的索引从小到到，即
	 * 
	 * data[index[i]] <= data[index[i+1]]
	 * 
	 * @param index
	 * @param data
	 */
	public IndexSorter(Integer[] index, int[] data) {
		super();
		this.index = index;
		this.data = data;
	}

	public void sort() {
		Arrays.sort(index, new Comparator<Integer>() {
			@Override
			public int compare(final Integer o1, final Integer o2) {
				return data[o1] - data[o2];
			}
		});
	}
}