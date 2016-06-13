package com.cwjcsu.learning.concurrent.多线程风险;

import com.cwjcsu.learning.concurrent.util.Tester;

public class LostNotify {
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
    public /* synchronized */void waitForReady() throws InterruptedException {
        while (!this.isReady())
            /*
             * 在没有同步保护的情况下，如果setReady在此时运行，
             * notifyAll可能先于wait执行，wait将永远等待，不会被唤醒。
             */
            this.await();
    }

    static void test() {
        Runnable[] ts = new Runnable[2];
        final LostNotify x = new LostNotify();

        //Ready线程
        ts[0] = new Runnable() {
            public void run() {
                x.setReady();
            }
        };
        //WaitReady线程
        for(int i=1;i<ts.length;i++)
        {
            ts[i] = new Runnable() {
                public void run() {
                    try {
                        x.waitForReady();
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            };
        }
        Tester.runTest(ts,false,false);
    }
    public static void main(String[] args) {
        for (int i = 0;; i++) {
            System.out.println(i);
            test();
        }
    }
}
