package com.cwjcsu.projecteuler.p1_50;
import java.math.BigInteger;
import java.util.HashMap;

public class Problem29 {
	static Object dummy = new Object();

	public static void main(String[] args) {
		HashMap<BigInteger, Object> got = new HashMap<BigInteger, Object>();
		for (int i = 2; i <= 100; i++) {
			for (int j = 2; j <= 100; j++) {
				BigInteger I = BigInteger.valueOf(i).pow(j);
				got.put(I, dummy);
			}
		}
		System.out.println(got.size());
	}
}
