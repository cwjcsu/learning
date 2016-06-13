package com.cwjcsu.learning.network.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.SocketAddress;

/**
 * 
 * @author atlas
 * @date 2012-9-28
 */
public class MulticastSniffer {
	  public static void main(String[] args) {
		  	args = new String[]{"all-systems.mcast.net","2222"};
//		  args = new String[]{"224.0.0.3","2222"};
		    InetAddress group = null;
		    int port = 0;
		    // read the address from the command line
		    try {
		      group = InetAddress.getByName(args[0]);

		      port = Integer.parseInt(args[1]);

		    }  // end try

		    catch (Exception ex) {

		      // ArrayIndexOutOfBoundsException, NumberFormatException,

		      // or UnknownHostException

		      System.err.println(

		       "Usage: java MulticastSniffer multicast_address port");

		      System.exit(1);

		    }

		    MulticastSocket ms = null;

		    try {
		    	SocketAddress address = new InetSocketAddress(port);
		      ms = new MulticastSocket(address);

		      ms.joinGroup(group);

		      byte[] buffer = new byte[8192];

		      while (true) {

		        DatagramPacket dp = new DatagramPacket(buffer, buffer.length);

		        ms.receive(dp);

		        String s = new String(dp.getData( ));

		        System.out.println(s);

		      }

		    }

		    catch (IOException ex) {

		      System.err.println(ex);

		    }

		    finally {

		      if (ms != null) {

		        try {

		          ms.leaveGroup(group);

		          ms.close( );

		        }

		        catch (IOException ex) {} 

		      }

		    } 
		  }
}
