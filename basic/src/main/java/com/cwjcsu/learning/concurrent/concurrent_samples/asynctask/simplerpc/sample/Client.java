package com.cwjcsu.learning.concurrent.concurrent_samples.asynctask.simplerpc.sample;

import com.cwjcsu.learning.concurrent.concurrent_samples.asynctask.simplerpc.RPCClient;

public class Client {

    /**
     * @param args
     */
    public static void main(String[] args) 
    {
        try
        {
            RPCClient client = new RPCClient();
            ISample sample = client.createService(ISample.SERVICE_NAME,ISample.class);
            System.out.println(sample.sayHello());
            double a = 1;
            double b = 2;
            double c = sample.add(a,b);
            System.out.println(c);
            long t0 = System.currentTimeMillis();
            int times = 1000;
            for(int i=0;i<times;i++)
                c = sample.add(a,b);
            long t1 = System.currentTimeMillis();
            System.out.println("ISample.add " + times + " times, used time " + (t1-t0) + " ms.");
            client.close();
        }
        catch(Throwable ex)
        {
            ex.printStackTrace();
        }

    }

}
