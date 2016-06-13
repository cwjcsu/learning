/*$Id: $
 * author                     date    comment
 * cwjcsu@gmail.com  2015年11月10日  Created
 */
package com.cwjcsu.learning.tricks;

/**
 * 打印当前执行过程中的方法
 * 
 * http://stackoverflow.com/questions/442747/getting-the-name-of-the-current-
 * executing-method
 * 
 * @author atlas
 *
 */
public class CurrentExecutingMethod {

	public static void main(String[] args) {
		CurrentExecutingMethod a = new CurrentExecutingMethod();
		a.aMethodShowMySelf();
		a.aMethodShowMySelf2();
		System.out.println(a.aMethodShowMySelf3(0));
		System.out.println(a.aMethodShowMySelf3(1));
	}

	public void aMethodShowMySelf() {
		String name = new Object() {
		}.getClass().getEnclosingMethod().getName();
		System.out.println("executing in : " + name);
	}

	public void aMethodShowMySelf2() {
		class Local {
		}
		;
		String name = Local.class.getEnclosingMethod().getName();
		System.out.println("executing in : " + name);
	}

	/*
	 * I use JRE 6 and gives me incorrect method name.
It works if I write ste[2 + depth].getMethodName().

0 is getStackTrace(),
1 is getMethodName(int depth) and
2 is invoking method.
	 */
	public String aMethodShowMySelf3(int depth) {
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		return  ste[ste.length - 1 - depth].getMethodName();  
	}
}
