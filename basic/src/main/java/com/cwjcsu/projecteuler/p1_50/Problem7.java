package com.cwjcsu.projecteuler.p1_50;
import java.math.BigInteger;
//104759
public class Problem7 {
	public static void main(String[] args) {
		BigInteger n = BigInteger.valueOf(1);
		int i = 0;
		while (i < 10001) {
			n=n.nextProbablePrime();
			i++;
		}
		System.err.println(n.longValue());
	}
}
