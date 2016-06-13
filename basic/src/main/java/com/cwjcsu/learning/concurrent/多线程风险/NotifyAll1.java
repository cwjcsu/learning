package com.cwjcsu.learning.concurrent.多线程风险;

import com.cwjcsu.learning.concurrent.util.Tester;
/*
 * 多个线程轮流运行
 * current_index的那个线程运行，其他线程等待
 * 运行的线程计数器减1后，把current_index设置到下一个线程，并激活（所有线程或只激活下一个线程）
 */
public class NotifyAll1 {

    static volatile int current_index;
    static volatile int remain_times;
    static final int REPEAT_TIMES = 100000;
    static final int THREAD_COUNT = 10;
    static WorkThread[] ts = new WorkThread[THREAD_COUNT];
    static final Object global_monitor = new Object();
    static class WorkThread implements Runnable {
        int index;
        Object monitor;
        public WorkThread(int index,Object monitor) {
            this.index = index;
            this.monitor = monitor;
        }
        public void setToken()
        {
            synchronized(monitor)
            {
                current_index = this.index;
                monitor.notifyAll();
            }
        }
        public void waitToken() throws InterruptedException
        {
            synchronized(monitor)
            {
                while(current_index != this.index)
                    monitor.wait();
            }
        }
        public void run() {
            try {
                while(remain_times > 0)
                {
                    this.waitToken();//等待current_index==this.index
                    remain_times--;//计数器-1
                    int next_index = (current_index+1)%THREAD_COUNT;
                    ts[next_index].setToken(); //current_index 设置到下一个Thread
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    //privateMonitor: true :每个线程都使用单独的同步锁
    //                false:所有线程使用同一个同步锁
    static void test(boolean privateMonitor) {
        remain_times = REPEAT_TIMES;
        current_index = 0;
        for (int i = 0; i < ts.length; i++)
        {
            Object monitor = privateMonitor?new Object():global_monitor;
            ts[i] = new WorkThread(i,monitor);
        }
        Tester.runTest(ts);
    }
    public static void main(String[] args) {
        System.out.println("重复次数：" + REPEAT_TIMES);
        System.out.println("线程数：   " + THREAD_COUNT);
        for(int i=0;i<10;i++)
        {
            System.out.println("所有线程使用同一个同步锁");
            test(false);
            System.out.println("每个线程使用单独的同步锁 ");
            test(true);
        }
        Tester.exit();
    }

}
