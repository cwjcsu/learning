package com.cwjcsu.learning.network.broadcast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 * 
 * @author atlas
 * @date 2012-9-26
 */
public class Sender {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		 String msg = "Hello";  
		 InetAddress group = InetAddress.getByName("228.5.6.7");  
		 MulticastSocket s = new MulticastSocket(6789);  
		 s.joinGroup(group);  
		 DatagramPacket hi = new DatagramPacket(msg.getBytes(), msg.length(),  
		                             group, 6789);  
		 s.send(hi);  
		 // get their responses!  
		byte[] buf = new byte[1000];  
		 DatagramPacket recv = new DatagramPacket(buf, buf.length);  
		 s.receive(recv);  
		 // OK, I'm done talking - leave the group...  
		 s.leaveGroup(group);  
	}
	 
}
