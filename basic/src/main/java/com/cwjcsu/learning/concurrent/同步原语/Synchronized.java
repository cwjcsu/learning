package com.cwjcsu.learning.concurrent.同步原语;

import java.util.Vector;

public class Synchronized 
{
    //同步块：在代码中同步对象实例
    public static <T> T getFirstElement(Vector<T> list)
    {
        synchronized(list)
        {
            if(list.size() > 0 )
                return list.remove(0);
            return null;
        }
    }
    
    
    int value = 0;
    //同步非静态方法,同步对象是this
    public synchronized void append1(int delta)
    {
        this.value += delta;
    }
    public void append2(int delta)
    {
        synchronized(this)
        {
            this.value += delta;
        }
    }
    
    static int staticValue = 0;
    //同步静态方法，同步对象是类实例
    public synchronized static void appendStatic(int delta)
    {
        staticValue += delta;
    }
    public static void appendStatic2(int delta)
    {
        synchronized(Synchronized.class)
        {
            staticValue += delta;
        }
    }
}
