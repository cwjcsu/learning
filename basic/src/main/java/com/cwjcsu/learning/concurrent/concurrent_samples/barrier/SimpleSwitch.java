package com.cwjcsu.learning.concurrent.concurrent_samples.barrier;

public class SimpleSwitch 
{
    private volatile boolean value;
    public boolean get()
    {
        return this.value;
    }
    public synchronized void set(boolean b)
    {
        this.value = b;
        this.notifyAll();
    }
    public synchronized void waitFor() throws InterruptedException
    {
        while(!this.value)
            this.wait();
    }
}
