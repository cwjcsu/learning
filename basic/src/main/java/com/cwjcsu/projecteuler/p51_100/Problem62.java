package com.cwjcsu.projecteuler.p51_100;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.HashMap;

import com.cwjcsu.projecteuler.util.QuickSort;
import com.cwjcsu.projecteuler.util.Util;

///50277061720282888384
public class Problem62 {
	static Object dummy = new Object();
	static Comparator<Integer> comparator = new Comparator<Integer>() {
		@Override
		public int compare(Integer o1, Integer o2) {
			return o1 - o2;
		}
	};

	static void do1() {
		BigInteger i = BigInteger.valueOf(123);
		BigInteger c = i.pow(3);
		HashMap<String, N> got = new HashMap<String, N>();
		for (;; i = i.add(BigInteger.ONE), c = i.pow(3)) {
			int[] d = Util.toDigit(c.toString(10));
			QuickSort.quickSort(d);
			String thiz = Util.asString(d);
			N n = got.get(thiz);
			if (n != null) {
				n.count++;
				n.min = n.min.min(c);
			} else {
				n = new N();
				n.count = 1;
				n.min = c;
				got.put(thiz, n);
			}
			if (n.count >= 5) {
				System.out.println("Min:" + n.min + ",Key " + thiz);
				break;
			}
		}
	}

	public static void main(String[] args) {
		do1();
	}

}

class N {
	int count = 0;
	BigInteger min = BigInteger.ZERO;
}
