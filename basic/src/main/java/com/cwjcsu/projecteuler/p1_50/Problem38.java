package com.cwjcsu.projecteuler.p1_50;
/*
 * i n d 4d 2 >5000 3d 3 <334 2d - --
 * 
 * @author Sunny
 * 932718654
 */
public class Problem38 {
	public static boolean is9BitsPandigital( String number )
	{
		if( number.length() != 9 )
		{
			return false;
		}
 
		for( int i=number.length()-1; i>=0; i-- )
		{
			for( int j=0; j<i; j++ )
			{
				if( number.charAt(i) == number.charAt(j) || number.charAt(i) == '0' || number.charAt(j) == '0' )
				{
					return false;
				}
			}
		}
 
		return true;
 
	}
 
	public static String getPandigital( int number )
	{
		String s = String.valueOf( number );
		int counter = 2;
 
		while( s.length() < 9 )
		{
			s += String.valueOf( number*counter );
			counter++;
		}
 
		if( is9BitsPandigital(s) )
		{
			return s;
		}
 
		return null;
	}
 
	public static void main(String[] args) 
	{
		int result = 0;
		String s;
		int tmp;
 
		for( int i=1; i<99999; i++ )
		{
			s = getPandigital( i );
 
			if( s != null )
			{
				tmp = Integer.valueOf( s );
 
				if( tmp > result )
				{
					result = tmp;
				}
			}
		}
 
		System.out.println( result );
	}

	
}
