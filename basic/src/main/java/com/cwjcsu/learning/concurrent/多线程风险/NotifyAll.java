package com.cwjcsu.learning.concurrent.多线程风险;

public class NotifyAll {

    static final Object monitor = new Object();
    static volatile int current_index = 0;
    static class WorkThread extends Thread {
        int index;
        public WorkThread(int index) {
            this.index = index;
        }
        public void run() {
            try {
                //等待current_index==this.index
                synchronized (monitor) {
                    while (current_index != this.index)
                        monitor.wait();
                }
                System.out.println(this.index);
                //设置下一个index
                synchronized (monitor) {
                    current_index++;
                    monitor.notifyAll();
                }
            }
            catch (Exception _) {
            }
        }
    }
    public static void main(String[] args) throws Exception {
        Thread[] ts = new Thread[100];
        for (int i = 0; i < ts.length; i++) {
            ts[i] = new WorkThread(i);
            ts[i].start();
        }
        for (int i = 0; i < ts.length; i++)
            ts[i].join();
    }
}
