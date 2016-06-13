package com.cwjcsu.learning.concurrent.concurrent_samples.asynctask.simplerpc.sample;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

public class MessageQueue implements IMessageSender,IMessageReceiver 
{
    final AtomicLong idSeq = new AtomicLong(0);
    final BlockingQueue queue;
    final String name;
    public MessageQueue(String name,int maxSize)
    {
        this.name = name;
        this.queue = new LinkedBlockingQueue(maxSize);
    }
    @Override
    public Message receive() throws Exception {
        return (Message)queue.take();
    }

    @Override
    public synchronized Message  send(String text) throws Exception {
        Message msg = new Message(idSeq.incrementAndGet(),text);
        queue.put(msg);
        return msg;
    }

}
