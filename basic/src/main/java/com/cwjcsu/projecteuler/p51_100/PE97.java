package com.cwjcsu.projecteuler.p51_100;
 

import java.math.BigInteger;
//8739992577
public class PE97 {
	public static void main(String[] args) {
		BigInteger I = BigInteger.valueOf(28433);
		I=I.shiftLeft(7830457).add(BigInteger.ONE).mod(BigInteger.valueOf(10000000000L));
		System.out.println(I.toString(10));
	}
}
