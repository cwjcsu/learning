package com.cwjcsu.learning.concurrent.concurrent_samples.asynctask.simplerpc.sample;

import java.util.HashMap;

import com.cwjcsu.learning.concurrent.concurrent_samples.asynctask.simplerpc.RPCServer;

public class MessageQueueManager implements IMessageQueueFactory 
{
    private HashMap<String,MessageQueue> queues = new HashMap<String,MessageQueue>();
    
    public String createQueue(String name) {
        return registerQueue(name).name;
    }
    public synchronized MessageQueue registerQueue(String name)
    {
        MessageQueue queue = queues.get(name);
        if(queue == null)
        {
            queue = new MessageQueue(name,100);
            queues.put(name,queue);
            RPCServer.registerService(queue.name,queue);
        }
        return queue;
    }
    public synchronized String[] getQueueNames() {
        Object[] qs = queues.values().toArray();
        String[] ar = new String[qs.length];
        for(int i=0;i<ar.length;i++)
            ar[i] = ((MessageQueue)qs[i]).name;
        return ar;
    }
    
}
