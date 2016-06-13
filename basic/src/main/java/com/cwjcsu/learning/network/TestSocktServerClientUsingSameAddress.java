package com.cwjcsu.learning.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * 一个socket server bind到一个ip:port，然后这个进程又发起socket请求到其他地址，local bind
 * 这个ip:port,可行吗？
 * <br/>
 * 不可行
 * 
 * @author atlas
 * @date 2013-5-16
 */
public class TestSocktServerClientUsingSameAddress {

	public static void main(String[] args) throws IOException, InterruptedException {
		// client bind server1 connect server2
		InetAddress addr = InetAddress.getByName("192.168.9.44");
		InetSocketAddress server1 = new InetSocketAddress(addr, 7000);
		InetSocketAddress server2 = new InetSocketAddress(addr, 7001);

		Server s1 = new Server(server1);
		s1.bind();

		Server s2 = new Server(server2);
		s2.bind();

		InetSocketAddress server10 = new InetSocketAddress(addr, 0);
		Client c = new Client(server10, server2);
		
		c.connect();
		
		Thread.sleep(10000);
	}

}
