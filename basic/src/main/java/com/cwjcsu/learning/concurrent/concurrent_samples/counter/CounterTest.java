package com.cwjcsu.learning.concurrent.concurrent_samples.counter;

public class CounterTest {

    static class TestThread extends Thread
    {
        final Counter counter;
        final int times;
        final long delta;
        public TestThread(Counter counter, int times, long delta) {
            super();
            this.counter = counter;
            this.times = times;
            this.delta = delta;
        }
        public void run()
        {
            for(int i=0;i<times;i++)
                counter.append(delta);
        }
    }
    static final int REPEAT_TIMES = 1000000;
    static final int DELTA = 1;
    static final int THREAD_COUNT = 32;
    static void test(Counter counter)
    {
        try
        {
            Thread.sleep(1000);
            long t0 = System.currentTimeMillis();
            Thread[] ts = new Thread[THREAD_COUNT];
            for(int i=0;i<ts.length;i++)
                ts[i] = new TestThread(counter,REPEAT_TIMES,i%2==0?DELTA:-DELTA);
            for(int i=0;i<ts.length;i++)
                ts[i].start();
            for(int i=0;i<ts.length;i++)
                ts[i].join();
            t0 = System.currentTimeMillis()-t0;
            System.out.println(counter + "\tused time " + t0 + " ms, counter value = " + counter.get());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        for(int i=0;i<10;i++)
        {
            test(new NoSafeCounter());//没有同步
            test(new SyncBlockCounter());//同步块
            test(new ReentrantLockCounter());//ReentrantLock
            test(new SimCASCounter());//模拟CAS
            test(new AtomicCounter());//Atomic
            test(new SpinLockCounter());//自旋锁
        }
    }

}
