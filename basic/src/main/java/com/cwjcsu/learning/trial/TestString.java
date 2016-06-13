package com.cwjcsu.learning.trial;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author atlas
 * @date 2012-11-12
 */
public class TestString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out
				.println(String
						.format("execute command on ha node %s failed .Tried for all polycyclic cluster.unreachable %d,timedout %d,suspected %d.",
								1, 3, 1234, 12));
		
		List <? super Integer> list = null;
		
		list=  new ArrayList<Integer>();
		list=  new ArrayList<Number>();
		list=  new ArrayList<Comparable<Integer>>();
		list=  new ArrayList<Object>();
	}
}
