package com.cwjcsu.projecteuler;


import org.junit.Test;
import com.cwjcsu.projecteuler.util.QuickSort;
import com.cwjcsu.projecteuler.util.Util;

import static org.junit.Assert.assertEquals;


public class TestUtil {
	static TestUtil t = new TestUtil();

	public static void main(String[] args) {
		t.testIsPermutation();
	}

	@Test
	public void testSumOfdigitsString() {
		String d1 = "12341234";
		int d2 = 12341234;
		assertEquals(Util.sumOfDigits(d1), 20);
	}

	public void testIsPermutation() {
		System.out.println(Util.isPermutation(287109, 791801));
	}

	public void testSumOfdigitsInt() {
		int d2 = 12341234;
		assertEquals(Util.sumOfDigits(d2), 20);
	}

	public void testCheckTruncatable() {
		assertEquals(true, Util.checkPrimeTruncatable(3797));
	}

	public void testIsPandigital1_9() {
		assertEquals(Util.isPandigital1_9(987654321), true);
		assertEquals(Util.isPandigital1_9(123456781), false);
	}

	public void testQuitSort() {
		int[] a = { 10, 32, 1, 9, 5, 7, 12, 0, 4, 3 }; // array
		QuickSort.quickSort(a, 0, a.length - 1);
		Util.print(" ", a);
		System.out.println(Util.asString(a));
	}

	@Test
	public void testGetDivisorCount() {
		System.out.println(Util.getDivisorCount(Util.getPrimeDivisors(378000, Util.getPrimesBlow(378000))));
	}

	@Test
	public void testPowerMod() {
		long n = 23;
		long p = 5;
		long m = 7;
		System.out.println(Util.powerMod(n, p, m) == Util.powerMod2(n, p, m));
		
		System.out.println(Util.powerMod(999, 999, 100000000000L));
	}
}
