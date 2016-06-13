package com.cwjcsu.learning.trial;
import java.util.BitSet;

/**
 * 
 * @author atlas
 * @date 2012-10-18
 */
public class TestBitSet {

	private static final int MB = 1024 ;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long m1 = Runtime.getRuntime().freeMemory() / MB;
//		BitSet[] sets = new BitSet[1024];
//		for (int i = 0; i < 1024; i++) {
//			sets[i] = new BitSet(Integer.MAX_VALUE);
//		}
		Object a=new BitSet(Integer.MAX_VALUE);
		long m2 = Runtime.getRuntime().freeMemory() / MB;
		System.out.println("used:" + (m2 - m1));
	}
}
