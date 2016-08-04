package com.cwjcsu.projecteuler.p51_100;
import java.util.LinkedList;

import com.cwjcsu.projecteuler.util.Util;

public class Problem74 {
	//402
	public static void main(String[] args) {
		int LEN=60;
		int count=0;
		for (int d = 1; d <= 1000000; d++) {
			if(LEN==getChianLength(d, LEN)){
				count++;
			}
		}
		System.out.println(count);
	}

	static int getChianLength(int d, int maxLength) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(d);
		while (true) {
			d = sumOfFactorialOfDigits(d);
			if (!list.contains(d)) {
				list.addLast(d);
				if (list.size() > maxLength) {
					return list.size();
				}
			} else {
				break;
			}
		}
		return list.size();
	}

	static void help(int d) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(d);
		while (true) {
			d = sumOfFactorialOfDigits(d);
			if (!list.contains(d)) {
				list.addLast(d);
			} else {
				break;
			}
		}
		System.out.println(list + ",last:" + d);
	}

	static int sumOfFactorialOfDigits(int d) {
		int[] ds = Util.toDigit(d);
		int s = 0;
		for (int i = 0; i < ds.length; i++) {
			s += Util.digitFactorial(ds[i]);
		}
		return s;
	}
}
