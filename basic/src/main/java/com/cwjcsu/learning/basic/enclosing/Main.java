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
public class Main {

	public static void main(String[] args) {
        class C extends A.B {
        	A enclosingInstance;
            public C(A enclosingInstance) {
                enclosingInstance.super();
                this.enclosingInstance =enclosingInstance;
            }

            public void show() {
                System.out.println(enclosingInstance.i);
            }
        }
        A myA = new A(2);
        C myC = new C(myA);
        myC.show();
    }
}
