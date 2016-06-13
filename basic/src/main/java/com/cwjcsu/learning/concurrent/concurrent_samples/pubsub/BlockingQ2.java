package com.cwjcsu.learning.concurrent.concurrent_samples.pubsub;

import java.util.LinkedList;

public class BlockingQ2 implements BlockingQ 
{
    final LinkedList list = new LinkedList();
    final int maxSize;
    
    public BlockingQ2(int maxSize) {
        super();
        this.maxSize = maxSize;
    }

    public synchronized void put(Object msg) throws InterruptedException 
    {
        while(list.size()>=this.maxSize)
            this.wait();
        list.addLast(msg);
        if(list.size() == 1)
            this.notifyAll();
    }
    
    public synchronized Object take() throws InterruptedException
    {
        while(list.size() == 0)
            this.wait();
        Object msg = list.removeFirst();
        if(list.size() == this.maxSize-1)
            this.notifyAll();
        return msg;
    }
}
