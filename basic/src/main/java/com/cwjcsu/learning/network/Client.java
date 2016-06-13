package com.cwjcsu.learning.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 
 * @author atlas
 * @date 2012-11-9
 */
public class Client {

	private InetSocketAddress local;
	private InetSocketAddress remote;

	public Client(InetAddress localAddr, int localPort, InetAddress remoteAddr,
			int remotePort) {
		local = new InetSocketAddress(localAddr, localPort);
		remote = new InetSocketAddress(remoteAddr, remotePort);
	}

	public Client(InetSocketAddress local, InetSocketAddress remote) {
		super();
		this.local = local;
		this.remote = remote;
	}

	public void connect() throws IOException {
		Socket socket = new Socket();
		socket.bind(local);
		socket.connect(remote, 1000);

	}
}
