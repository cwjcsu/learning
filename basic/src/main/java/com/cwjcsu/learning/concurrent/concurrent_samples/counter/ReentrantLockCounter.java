package com.cwjcsu.learning.concurrent.concurrent_samples.counter;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounter extends NoSafeCounter {

    final ReentrantLock lock = new ReentrantLock();
    @Override
    public long append(long delta) {
        lock.lock();
        try
        {
            return super.append(delta);
        }
        finally
        {
            lock.unlock();
        }
    }
    public String toString()
    {
        return "Counter(ReentrantLock)";
    }
}
