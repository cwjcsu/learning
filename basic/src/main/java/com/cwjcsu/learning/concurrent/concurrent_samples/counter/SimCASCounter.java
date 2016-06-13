package com.cwjcsu.learning.concurrent.concurrent_samples.counter;

import java.util.concurrent.locks.ReentrantLock;

public class SimCASCounter extends NoSafeCounter {

    static final boolean USE_REENTRANTLOCK = true; 
    private final ReentrantLock lock = new ReentrantLock();
    private boolean compareAndSet(long expect,long newVal)
    {
        boolean r;
        if(USE_REENTRANTLOCK)
        {
            lock.lock();
            r = compareAndSet0(expect,newVal);
            lock.unlock();
        }
        else
        {
            synchronized(this)
            {
                r = compareAndSet0(expect,newVal);
            }
        }
        return r;
    }
    private boolean compareAndSet0(long expect,long newVal)
    {
        if(this.value != expect)
            return false;
        this.value = newVal;
        return true;
    }
    public long append(long delta) {
        for(;;)
        {
            long cur = this.value;
            long newVal = cur + delta;
            if(this.compareAndSet(cur,newVal))
                return newVal;
        }
    }
    public String toString()
    {
        return "Counter(模拟CAS)";
    }
}
