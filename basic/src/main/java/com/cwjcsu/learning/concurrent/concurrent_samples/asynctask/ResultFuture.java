package com.cwjcsu.learning.concurrent.concurrent_samples.asynctask;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ResultFuture {
    final ReentrantLock lock = new ReentrantLock();
    final Condition cond = lock.newCondition();
    volatile Result result = null;
    public Result getResultNoWait()
    {
        return this.result;
    }
    public boolean setResult(Result r)
    {
        r.getClass();
        if(this.result != null)
            return false;
        lock.lock();
        try
        {
            if(this.result != null)
                return false;
            this.result = r;
            cond.signalAll();
        }
        finally
        {
            lock.unlock();
        }
        //do jobs need cleanup.
        return true;
    }
    public boolean cancel()
    {
        return setResult(new Result(ResultType.CANCELED,null));
    }
    public boolean isCanceled()
    {
        Result r = this.getResultNoWait();
        return r != null && r.type == ResultType.CANCELED;
    }
    public boolean isDone()
    {
        return this.getResultNoWait() != null;
    }
    public boolean timeout()
    {
        return  setResult(new Result(ResultType.TIMEOUT,null));
    }
    public boolean result_ok(Object value)
    {
        return setResult(new Result(ResultType.RESULT_OK,value));
    }
    public boolean result_error(Throwable err)
    {
        err.getClass();
        return setResult(new Result(ResultType.RESULT_ERR,err));
    }
    public Result get() throws InterruptedException
    {
        lock.lock();
        try
        {
            for(;;)
            {
                Result r = this.getResultNoWait();
                if(r != null)
                    return r;
                cond.await();
            }
        }
        finally
        {
            lock.unlock();
        }
    }
}
