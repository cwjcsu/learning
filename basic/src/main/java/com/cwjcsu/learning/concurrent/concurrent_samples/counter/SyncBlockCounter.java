package com.cwjcsu.learning.concurrent.concurrent_samples.counter;

public class SyncBlockCounter extends NoSafeCounter {

    public synchronized long append(long delta) {
        return super.append(delta);
    }
    public String toString()
    {
        return "Counter(同步块)";
    }
}
