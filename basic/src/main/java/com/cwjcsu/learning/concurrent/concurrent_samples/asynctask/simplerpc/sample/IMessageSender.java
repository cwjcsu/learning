package com.cwjcsu.learning.concurrent.concurrent_samples.asynctask.simplerpc.sample;

public interface IMessageSender 
{
    public Message send(String text) throws Exception;
}
