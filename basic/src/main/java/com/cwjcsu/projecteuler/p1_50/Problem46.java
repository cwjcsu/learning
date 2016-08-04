package com.cwjcsu.projecteuler.p1_50;

public class Problem46 {
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
 
	public static void main(String[] args)
	{
		int result = 7;
		boolean ok = false;
 
		while( !ok )
		{		
			result += 2;
 
			ok = true;
 
			if( isPrime( result ) )
			{
				ok = false;
				continue;
			}
 
			for( int p=2; p<result; p++ )
			{
				if( isPrime( p ) )
				{
					int r = result - p;
					int k = r / 2;
					double s = Math.round( Math.sqrt(k) );
					s = s * s - k;
 
					if( k*2 == r && s < 0.000001 && s > -0.000001 )
					{
						ok = false;
						break;
					}
				}
			}
		}
 
		System.out.println( result );
	}
}
