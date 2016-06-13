package com.cwjcsu.learning.concurrent.同步原语;

public class WaitNotify 
{
    private boolean ready = false;
    public void waitForReady() throws InterruptedException
    {
        synchronized(this) //获得同步锁
        {
            
            while(!ready) //在循环里等待条件满足是个好习惯，虽然这里不是必要的。
            {
              //not ready, 条件不满足 
                this.wait();    //等待
                /*
                 *  //等待
                     1: 保存锁状态 
                     2: 释放同步锁
                     3: 线程等待
                     
                     //被唤醒
                     1: 重新竞争获得同步锁
                     2：恢复锁状态
                 
                 */
            }
        }          
    }
    public void setReady()
    {
        synchronized(this) //获得同步锁
        {
            this.ready = true;
            this.notifyAll(); //唤醒所有等待线程
        }
    }
}
