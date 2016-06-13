package com.cwjcsu.learning.concurrent.concurrent_samples.asynctask.simplerpc.sample;

public interface IMessageReceiver 
{
    public Message receive() throws Exception;
}
