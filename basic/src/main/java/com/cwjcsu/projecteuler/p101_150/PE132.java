package com.cwjcsu.projecteuler.p101_150;

import java.math.BigInteger;

import com.cwjcsu.projecteuler.util.Tick;
import com.cwjcsu.projecteuler.util.Util;

/**
 * helpful wiki:http://en.wikipedia.org/wiki/Repunit
 * 
 * 
 * @author atlas
 * @date 2013-10-22
 */
public class PE132 {

	public static void main(String[] args) {
		Tick t = new Tick();
		int[] primes = Util.getPrimesBlow(1000000);
		int k = (int)Math.pow(10, 9);
		int found = 0;
		int sum = 0;
		int i=0;
		BigInteger TEN = BigInteger.valueOf(10);
		BigInteger exponent = BigInteger.valueOf(k);
		while(found <40){
			int prime = primes[i++];
			if(TEN.modPow(exponent, BigInteger.valueOf(9*prime)) .equals( BigInteger.ONE)){
				found++;
				sum+=prime;
			}
		}
		System.out.println(sum);
		System.out.println(t.elapsedTime());
	}
}
