package com.cwjcsu.learning.concurrent.concurrent_samples.pubsub;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQ3 implements BlockingQ 
{
    final LinkedList list = new LinkedList();
    final ReentrantLock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();
    final int maxSize;
    
    public BlockingQ3(int maxSize) {
        super();
        this.maxSize = maxSize;
    }

    public void put(Object msg) throws InterruptedException 
    {
        lock.lock();
        try
        {
            while(list.size() >= maxSize)
                notFull.await();
            list.addLast(msg);
            if(list.size() == 1)
                notEmpty.signal();
        }
        finally
        {
            lock.unlock();
        }
    }
    public Object take() throws InterruptedException
    {
        lock.unlock();
        try
        {
            while(list.size() == 0)
                notEmpty.await();
            Object msg = list.removeFirst();
            if(list.size() == maxSize - 1)
                notFull.signal();
            return msg;
        }
        finally
        {
            lock.unlock();
        }
    } 
}
