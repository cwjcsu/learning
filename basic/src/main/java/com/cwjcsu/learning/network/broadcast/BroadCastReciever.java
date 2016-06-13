package com.cwjcsu.learning.network.broadcast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 * 
 * @author atlas
 * @date 2012-9-26
 */
public class BroadCastReciever {
	 /** 
     * @param args 
     * @throws UnknownHostException  
     */  
    public static void main ( String [] args ) throws Exception  
    {  
          InetAddress group = InetAddress.getByName("228.5.6.7");  
          MulticastSocket s = new MulticastSocket(6789);   
          byte[] arb = new byte[1024];  
          s.joinGroup(group);//加入该组  
          while(true){  
               DatagramPacket datagramPacket =new DatagramPacket(arb,arb.length);  
               s.receive(datagramPacket);  
               System.out.println(arb.length);  
               System.out.println(new String(arb));   
          }  
    }  
}
