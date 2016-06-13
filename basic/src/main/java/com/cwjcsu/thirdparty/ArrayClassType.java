package com.cwjcsu.thirdparty;/*$Id: $
 * author                     date    comment
 * cwjcsu@gmail.com  2015年7月20日  Created
 */
/**
 * 
 * @author atlas
 *
 */
public class ArrayClassType {

	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		System.out.println(byte[].class.getName());
	    try {
	        Class clazz = Class.forName("[B");
	        System.out.println(byte[].class==clazz);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	    
	    System.out.println(String[].class);
	    
	    
	    Class c = Class.forName("[Ljava.lang.String;");
	    
	    String[] xx = (String[])c.newInstance();
	    System.out.println(xx.length);
	}
	
	
}
