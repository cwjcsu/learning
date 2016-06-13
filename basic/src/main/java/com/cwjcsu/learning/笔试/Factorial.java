package com.cwjcsu.learning.笔试;

import java.math.BigInteger;

/**
 * 
 * @author atlas
 * @date 2013-5-3
 */
public class Factorial {
	 public static void main(String[] args) {
		BigInteger I = BigInteger.ONE;
		for(int i=2;i<=1000;i++){
			I = I.multiply(BigInteger.valueOf(i));
		}
		System.out.println(I.toString());
		System.out.println("000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000".length());
	}
}
