package com.cwjcsu.learning.concurrent.concurrent_samples.asynctask.simplerpc.sample;

import com.cwjcsu.learning.concurrent.concurrent_samples.asynctask.simplerpc.RPCServer;

public class Server {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try
        {
            MessageQueueManager qm = new MessageQueueManager();
            RPCServer.registerService(ISample.SERVICE_NAME,new SampleService());
            RPCServer.registerService(IMessageQueueFactory.DEFAULT_SERVICE_NAME,qm);
            
            RPCServer.main();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }

}
