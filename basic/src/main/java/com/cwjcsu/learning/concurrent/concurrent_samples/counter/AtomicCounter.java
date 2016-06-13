package com.cwjcsu.learning.concurrent.concurrent_samples.counter;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicCounter extends AtomicLong implements Counter{

    public long append(long delta) {
        //return super.addAndGet(delta);
        for (;;) {
            long current = get();
            long next = current + delta;
            if (compareAndSet(current, next))
                return next;
            Thread.yield();
        }
    }
    public String toString()
    {
        return "Counter(Atomic)";
    }
}
