package com.cwjcsu.projecteuler.p201_250;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

/**
 * 1_2_3_4_5_6_7_8_9_0格式的平方数，由于末尾是0，则肯定是00，那么可以去掉10倍了，
 * 即考虑1_2_3_4_5_6_7_8_9格式的数，从最大的开根号数开始往下遍历直到找到为止。超过long范围，要用BigInteger
 * 
 * @author atlas
 * @date 2013-4-25
 */
public class PE206 {
	public static void main(String[] args) {
		long s = System.nanoTime();
		BigInteger N = new BigInteger("19293949596979899");
		int max = (int) Math.sqrt(N.doubleValue()) + 1;//平方根可能的最大值
		for (long i = max;; i--) {
			BigInteger I = BigInteger.valueOf(i);
			I = I.pow(2);
			if (I.toString().matches("1.2.3.4.5.6.7.8.9")) {//平方数匹配当前模式，则找到目标
				System.out.println(i * 10);
				break;
			}
		}
		System.out.println("Time consumed "
				+ TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - s) + " ms");
	}
}
