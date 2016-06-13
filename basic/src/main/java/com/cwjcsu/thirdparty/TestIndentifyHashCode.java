package com.cwjcsu.thirdparty;/*$Id: $
 * author                     date    comment
 * cwjcsu@gmail.com  2015年8月27日  Created
 */
/**
 * 
 * @author atlas
 *
 */
public class TestIndentifyHashCode {

	public static void main(String[] args) {
		Integer i1 = new Integer(1234);
		Integer i2 = new Integer(1234);
		System.out.println(i1 == i2);
		System.out.println(i1.equals(i2));
		System.out.println(System.identityHashCode(i1) + ","
				+ System.identityHashCode(i2));
		
		System.out.println(Integer.class.isPrimitive());
	}

}
