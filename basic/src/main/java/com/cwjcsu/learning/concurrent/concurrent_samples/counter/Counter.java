package com.cwjcsu.learning.concurrent.concurrent_samples.counter;

public interface Counter {
    public abstract long append(long delta);
    public abstract long get();
}
