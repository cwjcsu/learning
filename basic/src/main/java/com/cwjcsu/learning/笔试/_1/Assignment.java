package com.cwjcsu.learning.笔试._1;

/**
 * 
 * @author atlas
 * @date 2013-4-23
 */
public class Assignment {
	public static void main(String[] a){
		int tricky = 0;
		for (int i = 0; i < 3; i++)
			tricky += tricky++;
		System.out.println(tricky);
	}
	//7
	//0*
	//

}
