package com.cwjcsu.learning.笔试._1;
/**
 * 
 * @author atlas
 * @date 2013-4-23
 */
public class LockDemo {

	private Object lock = new Object();
	
	private boolean ready = false;
	
	void aMethod(){
		synchronized (lock) {
			while(!ready){
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
	}
	 
}
