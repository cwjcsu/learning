/*$Id: $
 * author                     date    comment
 * cwjcsu@gmail.com  2015年11月2日  Created
 */
package com.cwjcsu.thirdparty.jobbole;

/**
 * 
 * @author atlas
 *
 */
public class Thread9802 implements Runnable {
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new Thread9802());
		t.start();
		System.out.print("m1");
		t.join();
		System.out.print("m2");
		//r1m1r2m2
		//m1r1r2m2
	}

	public void run() {
		System.out.print("r1");
		System.out.print("r2");
	}
}
