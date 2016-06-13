package com.cwjcsu.learning.concurrent.concurrent_samples.pubsub;

import java.util.concurrent.LinkedBlockingQueue;

public class ConcurrentBlockingQ implements BlockingQ 
{
    private final LinkedBlockingQueue queue;
    public ConcurrentBlockingQ(int maxSize)
    {
        this.queue = new LinkedBlockingQueue(maxSize);
    }
    @Override
    public void put(Object msg) throws Exception {
        queue.put(msg);
    }
    @Override
    public Object take() throws Exception 
    {
        return queue.take();
    }
    
}
