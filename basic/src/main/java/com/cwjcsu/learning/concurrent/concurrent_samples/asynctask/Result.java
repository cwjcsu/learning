package com.cwjcsu.learning.concurrent.concurrent_samples.asynctask;

public class Result {
    public final ResultType type;
    public final Object value;
    public Result(ResultType type, Object value) {
        super();
        this.type = type;
        this.value = value;
    }
    
}
