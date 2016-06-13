package com.cwjcsu.learning.concurrent.concurrent_samples.barrier;


public class Test2 {

    static final int THREAD_COUNT = 10;
    static final SimpleBarrier start_monitor = new SimpleBarrier(10,
            new Runnable()
    {
        public void run()
        {
            System.out.println("all ready");
        }
    });
    static final CountDown finish_monitor = new CountDown(THREAD_COUNT);
    static class TestThread extends Thread
    {
        int index;
        
        public TestThread(int index) {
            super();
            this.index = index;
        }

        public void run()
        {
            try
            {
                System.out.println("Thread#"+index + " ready.");
                start_monitor.await();
                System.out.println("Thread#"+index + " wake up.");
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            finish_monitor.countDown();
        }
    }
    
    public static void main(String[] args) throws Exception 
    {
        for(int i=0;i<THREAD_COUNT;i++)
            new TestThread(i).start();
        finish_monitor.await();
        System.out.println("all finished.");
    }

}
