package com.cwjcsu.learning.concurrent.concurrent_samples.pubsub;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQ4 implements BlockingQ 
{
    final LinkedList list = new LinkedList();
    final ReentrantLock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();
    final AtomicBoolean closed = new AtomicBoolean(false);
    final int maxSize;
    
    public BlockingQ4(int maxSize) {
        super();
        this.maxSize = maxSize;
    }
    public boolean isClosed()
    {
        return closed.get();
    }
    public void close()
    {
        if(!closed.compareAndSet(false,true))
            return;
        lock.lock();
        try
        {
            notFull.signalAll();
            notEmpty.signalAll();
        }
        finally
        {
            lock.unlock();
        }
    }
    
    public void put(Object msg) throws Exception 
    {
        lock.lock();
        try
        {
            while(list.size() >= maxSize && !isClosed())
                notFull.await();
            if(isClosed())
                throw new Exception("closed");
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
            while(list.size() == 0 && !isClosed())
                notEmpty.await();
            if(list.size()>0)
            {
                Object msg = list.removeFirst();
                if(list.size() == maxSize - 1)
                    notFull.signal();
                return msg;
            }
            else
            {
                assert this.isClosed();
                return null;
            }
        }
        finally
        {
            lock.unlock();
        }
    } 
}
