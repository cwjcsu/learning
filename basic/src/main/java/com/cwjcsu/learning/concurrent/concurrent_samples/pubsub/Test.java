package com.cwjcsu.learning.concurrent.concurrent_samples.pubsub;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Test {

    static final int QUEUE_SIZE = 10;
    static final int MESSAGE_COUNT = 100;
    static final long MESSAGE_PRODUCE_TIME = 100;
    static final long MESSAGE_CONSUME_TIME = 20;
    //static final BlockingQ messageQueue = new ConcurrentBlockingQ(QUEUE_SIZE);
    //static final BlockingQ messageQueue = new BlockingQ2(QUEUE_SIZE);
    static final BlockingQueue messageQueue = new LinkedBlockingQueue(QUEUE_SIZE);
    static void doWork(long ms) throws Exception
    {
        ms*=1000000;
        long t0 = System.nanoTime();
        while(System.nanoTime()-t0 < ms)
            Thread.yield();
    }
    public static class ProducerThread extends Thread
    {
        public void run()
        {
            try
            {
                System.out.println("start send message.");
                for(int i=0;i<MESSAGE_COUNT;i++)
                {
                    doWork(MESSAGE_PRODUCE_TIME);
                    Object msg = "MSG#" + i;
                    messageQueue.put(msg);
                }
                System.out.println("finish send message.");
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
    public static class ConsumerThread extends Thread
    {
        
        public void run()
        {
            try
            {
                long t = System.currentTimeMillis();
                for(int i=0;i<MESSAGE_COUNT;i++)
                {
                    Object msg = messageQueue.take();
                    doWork(MESSAGE_CONSUME_TIME);
                    System.out.println("consume message " + msg);
                }
                t = System.currentTimeMillis()-t;
                System.out.println("finish consume messages, used time " + t + " ms.");
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
    public static void main(String[] args) 
    {
        new ConsumerThread().start();
        new ProducerThread().start();
    }

}
