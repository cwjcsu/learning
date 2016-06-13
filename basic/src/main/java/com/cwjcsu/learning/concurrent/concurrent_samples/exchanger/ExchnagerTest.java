package com.cwjcsu.learning.concurrent.concurrent_samples.exchanger;

import java.util.concurrent.Exchanger;
/*
 * 模拟图形显示DMA应用，把媒体文件显示到屏幕上的过程
 * ImageBuffer：交换缓冲区
 * Display： 显示设备
 * GraphApp: 图形应用
 * 
 */
public class ExchnagerTest {

    //媒体文件时间长度，毫秒
    static final long MEDIA_TIME = 10000L;//10 seconds
    
    //显示设备输出一个画面需要的时间，毫秒
    static final long FRAME_DISPLAY_TIME = 10;
    
    //应用程序绘制一个画面需要的时间，毫秒
    static final long FRAME_BUILD_TIME = 20;
    
    //包含一个画面的图形数据
    static class ImageBuffer
    {
        
    }
    
    //模拟工作延时
    static void doWork(long ms) throws Exception
    {
        ms*=1000000;
        long t0 = System.nanoTime();
        while(System.nanoTime()-t0 < ms)
            Thread.yield();
    }
    
    //显示设备
    static class Display extends Thread
    {
        //交换器
        Exchanger<ImageBuffer> exchanger;
        
        //当前数据缓冲区
        ImageBuffer currentBuffer;
        public Display(Exchanger<ImageBuffer> exchanger, ImageBuffer currentBuffer) {
            super();
            this.exchanger = exchanger;
            this.currentBuffer = currentBuffer;
        }
        //输出一个画面到屏幕
        void displayToScrrent(ImageBuffer buffer) throws Exception
        {
            doWork(FRAME_DISPLAY_TIME);
        }
        public void run()
        {
            try
            {
                long last_time = System.currentTimeMillis();
                long count = 0;
                for(;;)
                {
                    //从应用程序获得需要显示的画面数据
                    this.currentBuffer = exchanger.exchange(this.currentBuffer);
                    if(this.currentBuffer == null)
                        break;
                    //输出到显示器
                    this.displayToScrrent(this.currentBuffer);
                    
                    //计算刷新频率
                    long current_time = System.currentTimeMillis();
                    count ++;
                    if(current_time - last_time >= 1000)
                    {
                        long refresh_rate = count * 1000L / (current_time-last_time);
                        System.out.println("refresh rate : " + refresh_rate + " per second.");
                        count = 0;
                        last_time = current_time;
                    }
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
    //图形应用
    static class GraphApp extends Thread
    {
        //交换器
        Exchanger<ImageBuffer> exchanger;
        
        //当前数据缓冲区
        ImageBuffer currentBuffer;
        
        //媒体时间长度
        long media_time;
        public GraphApp(Exchanger<ImageBuffer> exchanger, ImageBuffer currentBuffer,long media_time) {
            super();
            this.exchanger = exchanger;
            this.currentBuffer = currentBuffer;
            this.media_time = media_time;
        }
        //绘制一个画面到buffer
        //timeOffseset时间索引
        boolean buildNextImage(ImageBuffer buffer,long timeOffset) throws Exception
        {
            if(timeOffset > media_time)
                return false;
            doWork(FRAME_BUILD_TIME);
            return true; 
        }
        public void run()
        {
            try
            {
                long t0 = System.currentTimeMillis();
                for(;;)
                {
                    if(!this.buildNextImage(this.currentBuffer,System.currentTimeMillis()-t0))
                        break;
                    this.currentBuffer = exchanger.exchange(this.currentBuffer);
                }
                exchanger.exchange(null);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        ImageBuffer[] exchangeBuffers = new ImageBuffer[]{new ImageBuffer(),new ImageBuffer()};
        Exchanger<ImageBuffer> exchanger = new Exchanger<ImageBuffer>();
        Thread t1 = new Display(exchanger,exchangeBuffers[0]);
        Thread t2 = new GraphApp(exchanger,exchangeBuffers[1],MEDIA_TIME);
        t1.start();
        t2.start();
    }

}
