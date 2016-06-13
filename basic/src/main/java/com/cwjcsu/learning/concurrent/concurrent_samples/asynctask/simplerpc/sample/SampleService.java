package com.cwjcsu.learning.concurrent.concurrent_samples.asynctask.simplerpc.sample;

public class SampleService implements ISample 
{
    public String sayHello()
    {
        return "hello";
    }
    public double add(double a,double b)
    {
        return a+b;
    }
}
