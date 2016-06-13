package com.cwjcsu.thirdparty.javaeye;
/**
 * 
 * @author atlas
 * @date 2013年11月18日
 */

public class Yield01 {  
   public static void main(String[] args) {  
       YieldFirst yf = new YieldFirst();  
       YieldSecond ys = new YieldSecond();  
       yf.start();  
       ys.start();  
   }  
}  
 
class YieldFirst extends Thread {  
   @Override  
   public void run() {  
       for (int i = 0; i < 20; i++) {  
           System.out.println("第一个线程第" + (i + 1) + "次运行.");  
           // 让当前线程暂停  
           Thread.yield();  
       }  
   }  
}  
 
class YieldSecond extends Thread {  
   @Override  
   public void run() {  
       for (int i = 0; i < 20; i++) {  
           System.out.println("第二个线程第" + (i + 1) + "次运行.");  
           // 让当前线程暂停  
           Thread.yield();  
       }  
   }  
}  
