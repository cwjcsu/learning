package com.cwjcsu.learning.concurrent.concurrent_samples.asynctask.simplerpc.sample;

public interface ISample {
    public static final String SERVICE_NAME = "SampleService";
    
    public String sayHello();
    public double add(double a, double b);
    
     
}
