package com.cwjcsu.learning.concurrent.同步原语;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AwaitSinal 
{
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition readyCond = lock.newCondition();
    
    private boolean ready = false;
    public void waitForReady() throws InterruptedException
    {
        lock.lock();
        try
        {
            while(!ready)
            {
                readyCond.await();
            }
        }
        finally 
        {
            lock.unlock();
        }
    }
    public void setReady()
    {
        lock.lock();
        try
        {
            this.ready = true;
            this.notifyAll();
        }
        finally //使用try finally是个好习惯，虽然这里不是必要的
        {
            lock.unlock();
        }
    }
}

