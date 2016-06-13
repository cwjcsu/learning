package com.cwjcsu.learning.concurrent.concurrent_samples.barrier;

public class CountDown {
    protected int count;

    public CountDown(int count) {
        super();
        this.count = count;
    }
    public synchronized void await() throws InterruptedException
    {
        while(this.count > 0)
            this.wait();
    }
    public synchronized void countDown()
    {
        if(this.count > 0)
            this.count--;
        if(this.count == 0)
            this.notifyAll();
    }
    public synchronized int getCount()
    {
        return this.count;
    }
}
