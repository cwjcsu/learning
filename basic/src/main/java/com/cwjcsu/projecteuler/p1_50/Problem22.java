package com.cwjcsu.projecteuler.p1_50;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Problem22 {

	public static void main(String[] args) throws Exception
	{	
		int result = 0;
		BufferedReader r = new BufferedReader( new FileReader("names.txt") );
		String s = r.readLine();;
		int score = 0;
		String[] names = s.split(",");
 
		for( int i=0; i<names.length; i++ )
		{
			names[i] = names[i].substring( 1, names[i].length()-1 );
		}
 
		Arrays.sort(names);
 
		for( int i=0; i<names.length; i++ )
		{
			score = 0;
 
			for( int j=0; j<names[i].length(); j++ )
			{
				score += (names[i].charAt(j) - 'A') + 1;
			}
 
			result += score*(i+1);
		}
 
		System.out.println( result );
	}
}
