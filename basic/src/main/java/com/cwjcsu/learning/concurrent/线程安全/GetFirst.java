package com.cwjcsu.learning.concurrent.线程安全;

import java.util.Vector;
import java.util.concurrent.atomic.AtomicLong;

public class GetFirst {

    public static Object getFirst(Vector list)
    {
        if(list.size() == 0)
            return null;
        
        
        return list.remove(0);
    }
    
    
    
    static final Vector list = new Vector();
    static final AtomicLong counter = new AtomicLong(0);
    static class TestThread extends Thread
    {
        public void run()
        {
            try
            {
                for(;;)
                {
                    counter.incrementAndGet();
                    list.add(new Object());
                    while(getFirst(list)!=null);
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                System.err.println(counter.get());
                System.exit(-1);
            }
        }
    }
    public static void main(String[] args) throws Exception 
    {
        new TestThread().start();
        new TestThread().start();
    }

}
