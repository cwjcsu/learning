package com.cwjcsu.learning.concurrent.多线程风险;

public class DeadLock1 {
    static final Object A = new Object();
    static final Object B = new Object();
    static void method1(String msg)
    {
        synchronized(A)
        {
            synchronized(B)
            {
                System.out.println(msg);
            }
        }
    }
    static void method2(String msg)
    {
        synchronized(B)
        {
            synchronized(A)
            {
                System.out.println(msg);
            }
        }
    }
    static class Thread1 extends Thread
    {
        public void run()
        {
            for(int i=0;;i++)
            {
                method1("Thread1#" + i);
            }
        }
    }
    static class Thread2 extends Thread
    {
        public void run()
        {
            for(int i=0;;i++)
            {
                method2("Thread2#" + i);
            }
        }
    }
    public static void main(String[] args)
    {
        new Thread1().start();
        new Thread2().start();
    }
}
