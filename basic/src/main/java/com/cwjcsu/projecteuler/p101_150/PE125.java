package com.cwjcsu.projecteuler.p101_150;

import java.util.BitSet;

import com.cwjcsu.projecteuler.util.Util;

/**
 http://www.mathblog.dk/project-euler-125-square-sums-palindromic/
 方案1：生成所有回文数，然后判断是否是平方和，
 方案2：生成所有数的平方和，判断是否是回文数

 方案2，暴力法
 *
 * @author atlas
 * @date 2013-4-26
 */
public class PE125 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int N = 100000000;
		int sqrtLimit = (int)Math.sqrt(N)+1;
		long sum = 0;
		BitSet bs = new BitSet(N);
		for (int i = 1; i <= sqrtLimit; i++) {
		    int n = i*i;
		    for (int j = i + 1; j <= sqrtLimit; j++) {
		        n += j * j;
		        if (n > N)
		        	break;
		        if (Util.isPalindrome(n) && !bs.get(n) ) {
		            sum += n;
		            bs.set(n);                
		        }
		 
		    }
		}
		System.out.println(sum);
	}
}
