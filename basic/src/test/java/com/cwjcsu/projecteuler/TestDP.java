package com.cwjcsu.projecteuler;

import com.cwjcsu.projecteuler.dp.Knapsack;
import com.cwjcsu.projecteuler.dp.LongestOrderedSubsequence;
import junit.framework.TestCase;


public class TestDP extends TestCase {
	public static void main(String[] args) {
		testLongestOrderedSubsequence();
	}

	public void testP01() {
		int[] w = { 21, 33, 18, 16, 30 };
		int[] c = { 5, 3, 4, 6, 2 };
		int V = 100;
		assertEquals(18, Knapsack.knapsack01(V, c, w));
	}

	public void testP0n() {
		int[] w = { 21, 33, 18, 16, 30 };
		int[] c = { 5, 3, 4, 6, 2 };
		int V = 100;
		assertEquals(36, Knapsack.knapsack0n(V, c, w));
	}

	public static void testLongestOrderedSubsequence() {
		int[] s = { 1, 7, 3, 5, 9, 4, 8,10 };
		System.out.println(LongestOrderedSubsequence.los(s));
	}
}
