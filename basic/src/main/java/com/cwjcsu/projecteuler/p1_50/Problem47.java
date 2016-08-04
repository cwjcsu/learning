package com.cwjcsu.projecteuler.p1_50;

public class Problem47 {
	public static boolean isPrime( int number )
	{
		if( number < 2 )
		{
			return false;
		}
		else if ( number == 2 )
		{
			return true;
		}
		else
		{
			for( long i=2; i<=Math.sqrt(number); i++ )
			{
				if( number%i == 0 )
				{
					return false;
				}
			}
		}
 
		return true;
	}
 
	public static int distinctPrimeFactors( int number )
	{
		int f = 0;
		int p = 2;
		boolean changed = true;
 
		while( !isPrime( number ) )
		{		
			if( number % p == 0 )
			{
				number /= p;
 
				if( changed )
				{
					f++;
				}
 
				changed = false;
			}
			else
			{
				p++;
				changed = true;
			}
		}
 
		f++;
 
		return f;
	}
 
	public static void main(String[] args)
	{
		int result = 647;
 
		while( true )
		{
			if( distinctPrimeFactors(result) == 4 && distinctPrimeFactors(result+1) == 4 && distinctPrimeFactors(result+2) == 4 && distinctPrimeFactors(result+3) == 4 )
			{
				break;
			}
 
			result++;
		}
 
		System.out.println( result );
	}
}
