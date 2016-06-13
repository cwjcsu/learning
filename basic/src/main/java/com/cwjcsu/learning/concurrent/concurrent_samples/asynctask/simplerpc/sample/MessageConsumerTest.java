package com.cwjcsu.learning.concurrent.concurrent_samples.asynctask.simplerpc.sample;

import com.cwjcsu.learning.concurrent.concurrent_samples.asynctask.simplerpc.RPCClient;

public class MessageConsumerTest {
    public static void main(String[] args)
    {
        try
        {
            RPCClient rpc = new RPCClient();
            String serviceName = rpc.createService(IMessageQueueFactory.DEFAULT_SERVICE_NAME,IMessageQueueFactory.class).createQueue(
                    MessageProducerTest.QUEUE_NAME);
            IMessageReceiver receiver = rpc.createService(serviceName,IMessageReceiver.class);
            for(;;)
            {
                Object msg = receiver.receive();
                System.out.println("message received from " + MessageProducerTest.QUEUE_NAME + " " + msg);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
