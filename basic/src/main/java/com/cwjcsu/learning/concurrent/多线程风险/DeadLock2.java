package com.cwjcsu.learning.concurrent.多线程风险;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class DeadLock2 {

    static class BlockingQ
    {
        private Object notEmpty = new Object();
        private Object notFull = new Object();
        private Queue<Object> linkedList = new LinkedList<Object>();
        static final int maxLength = 10;
        
        public Object take() throws InterruptedException {
            //如果此时另一个线程调用的offer，并且在notFull.wait处等待
            //则无法获得notEmpty的同步锁，运行不到notFull.notifyAll,从而形成死锁。
            synchronized (notEmpty) {
                if (linkedList.size() == 0)
                    notEmpty.wait();
                synchronized (notFull) {
                    if (linkedList.size() == maxLength)
                        notFull.notifyAll();
                    return linkedList.poll();
                }
            }
        }
        public void offer(Object object) throws InterruptedException {
            synchronized (notEmpty) {
                if (linkedList.size() == 0)
                    notEmpty.notifyAll();
                synchronized (notFull) {
                    if (linkedList.size() == maxLength)
                        notFull.wait();     //只释放了notFull的同步锁，没有释放notEmpty的同步锁
                    linkedList.add(object);
                }
            }
        }
    }
    static final BlockingQ q = new BlockingQ();
    static final AtomicInteger seq = new AtomicInteger(0);
    static void offerOne() 
    {
        try
        {
            q.offer(new Integer(seq.incrementAndGet()));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    static class OfferThread extends Thread
    {
        public void run()
        {
            offerOne();
        }
    }
    static class TakeThread extends Thread
    {
        public void run()
        {
            try
            {
                for(;;)
                {
                    System.out.println(q.take());
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws Exception{
        
        for(int i=0;i<BlockingQ.maxLength;i++)
            offerOne();
        
        new OfferThread().start();
        Thread.sleep(100);
        new TakeThread().start();
        
    }

}
