package com.cwjcsu.learning.concurrent.concurrent_samples.asynctask.simplerpc.sample;

import com.cwjcsu.learning.concurrent.concurrent_samples.asynctask.simplerpc.RPCClient;

public class MessageProducerTest {

    static final String QUEUE_NAME = "MessageQueue/TestQueue";
    public static void main(String[] args) {
        try
        {
            RPCClient rpc = new RPCClient();
            String serviceName = rpc.createService(IMessageQueueFactory.DEFAULT_SERVICE_NAME,IMessageQueueFactory.class).createQueue(QUEUE_NAME);
            IMessageSender sender = rpc.createService(serviceName,IMessageSender.class);
            for(int i=0;i<100;i++)
            {
                String text = "Message#" + i;
                Message msg = sender.send(text);
                System.out.println("send message to " + QUEUE_NAME + " " + msg);
            }
            rpc.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
