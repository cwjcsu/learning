package com.cwjcsu.learning.concurrent.concurrent_samples.asynctask.simplerpc.sample;

public interface IMessageQueueFactory 
{
    public static final String DEFAULT_SERVICE_NAME = "MessageQueueFactory";
    public String createQueue(String name);
    public String[] getQueueNames();
}
