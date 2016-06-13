package com.cwjcsu.learning.concurrent.concurrent_samples.counter;

public class NoSafeCounter implements Counter {

    protected long value = 0;
    public long append(long delta) {
        return this.value += delta;
    }

    
    public long get() {
        return this.value;
    }

    public String toString() {
        return "Counter(线程不安全)";
    }
    
}
