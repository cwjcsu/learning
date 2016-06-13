package com.cwjcsu.learning.concurrent.concurrent_samples.asynctask.simplerpc;

import java.lang.reflect.Method;

public class Util {
    static void close(Object x)
    {
        try
        {
            if(x != null)
            {
                Method m = x.getClass().getMethod("close");
                m.invoke(x);
            }
        }
        catch(Exception _)
        {
            
        }
    }
}
