package com.cwjcsu.learning.trial;
import java.lang.reflect.Method;

/**
 * 
 * @author atlas
 * @date 2012-12-17
 */
public class TestMethodParameter {

	public static class A{
		public void methodA(String param1){
			
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Method m = A.class.getMethod("methodA", new Class[]{String.class});
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
