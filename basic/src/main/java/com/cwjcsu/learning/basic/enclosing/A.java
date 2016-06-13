/*$Id: $
 * author                     date    comment
 * cwjcsu@gmail.com  2015年10月9日  Created
 */
package com.cwjcsu.learning.basic.enclosing;

/**
 * 
 * @author atlas
 *
 */
public class A {

	public int i;

	public A(int i) {
		this.i = i;
	}

	public class B {

		public B(){
			System.out.println("XXX");
		}
	}
}
