package com.cwjcsu.learning.concurrent.concurrent_samples.asynctask.simplerpc.sample;

public class Message implements java.io.Serializable{
    public final long id;
    public final String text;
    public Message(long id, String text) {
        super();
        this.id = id;
        this.text = text;
    }
    public String toString()
    {
        return "Message(id=" + id + ",text=" + text + ")";
    }
}
