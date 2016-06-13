package com.cwjcsu.learning.concurrent.concurrent_samples.pubsub;

import java.util.LinkedList;

public class BlockingQ1 implements BlockingQ
{
    final LinkedList list = new LinkedList();
    public synchronized void put(Object msg) 
    {
        list.addLast(msg);
        this.notify();
    }
    
    public synchronized Object take() throws InterruptedException
    {
        while(list.size() == 0)
            this.wait();
        return list.removeFirst();
    }
}
