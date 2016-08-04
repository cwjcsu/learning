package com.cwjcsu.projecteuler.p1_50;

import java.math.BigInteger;
import java.util.Set;
import java.util.TreeSet;

public class Problem45 {
	public static void main(String[] args)
	{
		String result = null;
		Set setPentagonal = new TreeSet();
		Set setHexagonal = new TreeSet();
 
		for( int i=166; i<99999; i++ )
		{
			BigInteger tmp1 = new BigInteger( String.valueOf(i) );
			BigInteger tmp2 =  tmp1.multiply( new BigInteger( String.valueOf(3) ) );
			tmp2 = tmp2.subtract( new BigInteger( String.valueOf(1) ) );
			tmp1 = tmp1.multiply( tmp2 );
			tmp1 = tmp1.divide( new BigInteger( String.valueOf(2) ) );
 
			setPentagonal.add( tmp1.toString() );
		}
 
		for( int i=144; i<99999; i++ )
		{
			BigInteger tmp1 = new BigInteger( String.valueOf(i) );
			BigInteger tmp2 =  tmp1.multiply( new BigInteger( String.valueOf(2) ) );
			tmp2 = tmp2.subtract( new BigInteger( String.valueOf(1) ) );
			tmp1 = tmp1.multiply( tmp2 );
 
			setHexagonal.add( tmp1.toString() );
		}
 
		for( int i=285; i<Integer.MAX_VALUE; i++ )
		{
			BigInteger tmp1 = new BigInteger( String.valueOf(i) );
			tmp1 = tmp1.multiply( tmp1.add( new BigInteger( String.valueOf(1) ) ) );
			tmp1 = tmp1.divide( new BigInteger( String.valueOf(2) ) );
			String tmp = tmp1.toString();
 
			if( setPentagonal.contains(tmp) && setHexagonal.contains(tmp) )
			{
				result = tmp;
				break;
			}
		}
 
		System.out.println( result );
	}
	public static void main1(String[] args) {
		long Max = (long) ((Math.sqrt(Long.MAX_VALUE) * 5 + 1) / 6);
		for (long p = 166; p < Max; p++) {
			long c = p * (2 * p - 1);
			if (isPentagonal(c)) {
				System.out.println(c);
				break;
			}
		}
	}

	static boolean isHexagonal(long d) {
		double n = Math.sqrt((d << 3) + 1) + 1;
		return n == (long) n;
	}

	static boolean isTriangle(long d) {
		int n = (int) Math.ceil(Math.sqrt(2L * d)) - 1;
		return n * (n + 1) == d;
	}

	public static boolean isPentagonal(long number) {
		number = 24 * number + 1;
		double s = Math.sqrt(number);
		number = (long) s;

		if (s - number > 0.0000001 || s - number < -0.0000001) {
			return false;
		}

		number++;

		if (number % 6 != 0) {
			return false;
		}

		return true;
	}
}
