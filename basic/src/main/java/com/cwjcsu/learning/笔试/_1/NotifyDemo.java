package com.cwjcsu.learning.笔试._1;


/**
 * 
 * @author atlas
 * @date 2013-4-23
 */
public class NotifyDemo {
	private boolean ready = false;
    public synchronized boolean isReady() {
        return this.ready;
    }
    public synchronized void setReady() {
        this.ready = true;
        this.notifyAll();
    }
    public synchronized void await() throws InterruptedException {
        this.wait();
    }
    public  void waitForReady() throws InterruptedException {
        while (!this.isReady())
            this.await();
        System.out.println("Ready");
    }

    public static void main(String[] a) throws InterruptedException {
        final NotifyDemo x = new NotifyDemo();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                x.setReady();
            }
        });
        Thread t2 = new Thread(new Runnable() {
                public void run() {
                        try {
							x.waitForReady();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
                }
            });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
