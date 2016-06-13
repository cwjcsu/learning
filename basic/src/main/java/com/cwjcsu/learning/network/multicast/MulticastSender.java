package com.cwjcsu.learning.network.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

/**
 * 
 * @author atlas
 * @date 2012-9-28
 */
public class MulticastSender {
	  public static void main(String[] args) {
//		 	args = new String[]{"224.0.0.3","2222","3"};
		 	args = new String[]{"all-systems.mcast.net","2222"};
		    InetAddress ia = null;

		    int port = 0;

		    byte ttl = (byte) 1;

		  
		    // read the address from the command line

		    try {

		      ia = InetAddress.getByName(args[0]);

		      port = Integer.parseInt(args[1]);

		      if (args.length > 2) ttl = (byte) Integer.parseInt(args[2]);

		    }

		    catch (Exception ex)  {

		      System.err.println(ex);

		      System.err.println(

		       "Usage: java MulticastSender multicast_address port ttl");

		      System.exit(1);

		    }

		    byte[] data = "Here's some multicast data\r\n".getBytes( );

		    DatagramPacket dp = new DatagramPacket(data, data.length, ia, port);

		    try {

		      MulticastSocket ms = new MulticastSocket( );

		      ms.joinGroup(ia);
		      
		      ms.setTimeToLive(ttl);

		      for (int i = 1; i < 10; i++) {

		        ms.send(dp);

		      }
		      
		      try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      ms.leaveGroup(ia);

		      ms.close( );

		    }

		    catch (SocketException ex) {

		      System.err.println(ex);

		    }

		    catch (IOException ex) {

		      System.err.println(ex);

		    }  

		  }

}
