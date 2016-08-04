package com.cwjcsu.projecteuler.p1_50;

public class Problem19 {
	public static boolean isLeapYear( int year )
	{
		if( year%400 == 0 )
		{
			return true;
		}
		else if( year%100 == 0 )
		{
			return false;
		}
		else if( year%4 == 0 )
		{
			return true;
		}
		else
		{
			return false;
		}
	}
 
	public static void main(String[] args) 
	{	
		int result = 0;
		int year = 1900;
		int month = 1;
		int[] monthsDaysNumber = {0,31,28,31,30,31,30,31,31,30,31,30,31};
		int weekday = 1;
 
		for( ; year<=2000; year++ )
		{
			for( ; month<=12; month++ )
			{
				int daysNumber = monthsDaysNumber[month];
				if( month == 2 && isLeapYear(year) )
				{
					daysNumber++;
				}
 
				for( int i=0; i<daysNumber; i++ )
				{
 
					if( year > 1900 && i == 0 && weekday == 7 )
					{
						result++;
					}
 
					weekday++;					
					if( weekday > 7 )
					{
						weekday = 1;
					}
				}
			}
			month = 1;
		}
 
		System.out.println( result );
	}
}
