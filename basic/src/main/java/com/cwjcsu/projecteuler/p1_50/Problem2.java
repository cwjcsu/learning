package com.cwjcsu.projecteuler.p1_50;
import com.cwjcsu.projecteuler.util.Util;

public class Problem2 {
	public static void main(String[] args) {
		System.out.println(getEvenSum(4000000));
	}
//4613732
	public static long getEvenSum(int exceed) {
		int prev = 1, curr = 2;
		long sum = 0;
		while (curr <= exceed) {
//			System.out.println(curr);
			if (Util.isEven(curr)) {
				sum += curr;
			}
			int temp = prev + curr;
			prev = curr;
			curr = temp;
		}
		return sum;
	}
}
