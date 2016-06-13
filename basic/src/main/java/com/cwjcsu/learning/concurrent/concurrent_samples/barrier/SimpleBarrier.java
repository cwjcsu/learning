package com.cwjcsu.learning.concurrent.concurrent_samples.barrier;

public class SimpleBarrier {
    private final int barrier_count;
    private int wait_count = 0;
    private Runnable barrierAction;
    public SimpleBarrier(int count,Runnable barrierAction)
    {
        this.barrier_count = count;
        this.barrierAction = barrierAction;
    }
    public SimpleBarrier(int count)
    {
        this(count,null);
    }
    
    public synchronized void await() throws InterruptedException
    {
        this.wait_count++;
        if(this.wait_count == this.barrier_count)
        {
            if(this.barrierAction != null)
                this.barrierAction.run();
            this.notifyAll();
        }
        else
        {
            while(this.wait_count < this.barrier_count)
            {
                this.wait();
            }
        }
    }
    
}
