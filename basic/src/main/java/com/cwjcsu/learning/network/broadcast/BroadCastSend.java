package com.cwjcsu.learning.network.broadcast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * 
 * @author atlas
 * @date 2012-9-26
 */
public class BroadCastSend {
	 /** 
     * @param args 
     * @throws UException  
     */  
    public static void main ( String [] args ) throws Exception  
    {  
          int port = 6789;  
           String sendMessage="你好";  
          InetAddress inetAddress = InetAddress.getByName("228.5.6.7");  
          DatagramPacket datagramPacket = new DatagramPacket(sendMessage.getBytes(), sendMessage.length(), inetAddress, port);  
          MulticastSocket multicastSocket = new MulticastSocket();  
          multicastSocket.send(datagramPacket);  

    }  
}
