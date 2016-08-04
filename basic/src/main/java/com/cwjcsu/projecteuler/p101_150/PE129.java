package com.cwjcsu.projecteuler.p101_150;

import java.math.BigInteger;

/**
 * 
 * @author atlas
 * @date 2013-10-23
 */
public class PE129 {

	public static void main(String[] args) {
		BigInteger K = BigInteger.valueOf(11);
		BigInteger TEN = BigInteger.valueOf(10L);
		for(int k=1000001;;k++){
			for(int n=3;;n+=2){
				if(n%5==0){
					continue;
				}
				if(TEN.modPow(BigInteger.valueOf(k), BigInteger.valueOf(9*n)).equals(BigInteger.ONE)){
					System.out.println(n);
					return;
				}
			}
		}
				
	}
}
