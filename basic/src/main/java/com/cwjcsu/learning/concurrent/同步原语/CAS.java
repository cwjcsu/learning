package com.cwjcsu.learning.concurrent.同步原语;

import com.cwjcsu.learning.concurrent.util.Tester;

public class CAS {
    private volatile int value;
    public int get()
    {
        return this.value;
    }
    public void set(int newVal)
    {
        this.value = newVal;
    }
    /*
     * expectVal 期望值
     * newVal   新值
     * 如果 当前值==期望值，那么设置新的值
     * return ： 是否设置成功（是否等于期望值）
     */
    public synchronized boolean compareAndSet(int expectVal,int newVal)
    {
        if(this.value != expectVal)
            return false;
        this.value = newVal;
        return true;
    }
    public int incrementAndGet()
    {
        for(;;)
        {
            int cur = this.value;
            int next = cur+1;
            if(this.compareAndSet(cur,next))
                return next;
        }
    }
    public int addAndGet(int delta)
    {
        for(;;)
        {
            int cur = this.value;
            int next = cur + delta;
            if(this.compareAndSet(cur,next))
                return next;
        }
    }
    
    
    public synchronized void increment()
    {
        this.value++;
    }
    
    
    
    
    
    static long time1 = 0;
    static long time2 = 0;
    void test()
    {
        System.out.println("increment");
        Runnable[] tasks = new Runnable[20];
        for(int i=0;i<tasks.length;i++)
        {
            tasks[i] = new Runnable()
            {
                  public void run()
                  {
                      for(int i=0;i<1000000;i++)
                          increment();
                  }
            };
        }
        time1+=Tester.runTest(tasks);
        System.out.println("increment2");
        for(int i=0;i<tasks.length;i++)
        {
            tasks[i] = new Runnable()
            {
                  public void run()
                  {
                      for(int i=0;i<1000000;i++)
                          incrementAndGet();
                  }
            };
        }
        time2+=Tester.runTest(tasks);
    }
    public static void main(String[] args)
    {
        for(int i=0;i<10;i++)
            new CAS().test();
        System.out.println("time1=" + time1);
        System.out.println("time2=" + time2);
        Tester.exit();
    }
}
