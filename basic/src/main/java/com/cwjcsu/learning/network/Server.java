package com.cwjcsu.learning.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author atlas
 * @date 2012-11-9
 */
public class Server implements Runnable {

	private InetSocketAddress address;

	private ServerSocket server;

	public Server(InetAddress addr, int port) {
		address = new InetSocketAddress(addr, port);
	}

	public Server(InetSocketAddress address) {
		super();
		this.address = address;
	}

	public void bind() throws IOException {
		server = new ServerSocket();
		server.bind(address);
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		while (true) {
			Socket client;
			try {
				client = server.accept();
				if (client != null)
					System.out.println(client.getRemoteSocketAddress()
							+ " is connecting");
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}

}
