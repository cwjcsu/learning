package com.cwjcsu.learning.concurrent.concurrent_samples.counter;

import java.util.concurrent.atomic.AtomicBoolean;

public class SpinLockCounter extends NoSafeCounter {
    final AtomicBoolean lock = new AtomicBoolean(false);

    void lock()
    {
        while(!lock.compareAndSet(false,true))
            Thread.yield();
    }
    void unlock()
    {
        lock.set(false);
    }
    @Override
    public long append(long delta) {
        lock();
        try
        {
            return super.append(delta);
        }
        finally
        {
            unlock();
        }
    }
    public String toString()
    {
        return "Counter(自旋锁)";
    }
}
