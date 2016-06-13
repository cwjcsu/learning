package com.cwjcsu.learning.concurrent.util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Tester 
{
    static final ExecutorService threadPool = Executors.newCachedThreadPool();
    public static long runTest(Runnable[] tasks)
    {
        return runTest(tasks,true,true);
    }
    public static long runTest(Runnable[] tasks,final boolean syncStart,boolean printTime)
    {
        try
        {
            final long[] time = new long[]{System.currentTimeMillis()};
            final CountDownLatch end = new CountDownLatch(tasks.length); 
            final CyclicBarrier begin = new CyclicBarrier(tasks.length,new
                    Runnable()
            {
                public void run()
                {
                    time[0] = System.currentTimeMillis();
                }
            });
            for(Runnable task: tasks)
            {
                final Runnable x = task;
                threadPool.execute(new Runnable()
                {
                    public void run()
                    {
                        try
                        {
                            if(syncStart)
                                begin.await();
                            x.run();
                        }
                        catch(Exception ex)
                        {
                            ex.printStackTrace();
                            begin.reset();
                        }
                        finally
                        {
                            end.countDown();
                        }
                    }
                });
            }
            end.await();
            long timeUsed = System.currentTimeMillis() - time[0];
            if(printTime)
                System.out.println("\tused time : " + timeUsed + " ms.");
            return timeUsed;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            exit();
            return 0;
        }
    }
    public static void exit()
    {
        threadPool.shutdown();
        System.exit(0);
    }
}
