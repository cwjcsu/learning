package com.cwjcsu.learning.concurrent.concurrent_samples.onceonly;

import java.util.concurrent.atomic.AtomicBoolean;

public class CloseSupport 
{
    private volatile boolean closed = false;
    public boolean isClosed()
    {
        return this.closed;
    }
    public void close()
    {
        if(closed)
            return;
        synchronized(this)
        {
            if(closed)
                return;
            closed = true;
        }
        //do close jobs
    }
}
class CloseSupport2
{
    final AtomicBoolean closed = new AtomicBoolean(false);
    public boolean isClosed()
    {
        return closed.get();
    }
    public void close()
    {
        if(!closed.compareAndSet(false,true))
            return;
        //do close jobs
    }
}