package com.cwjcsu.learning.concurrent.concurrent_samples.pubsub;

public interface BlockingQ {

    public Object take() throws Exception;
    public void put(Object msg) throws Exception;
}
