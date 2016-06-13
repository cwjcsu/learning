package com.cwjcsu.learning.security.skip;

import com.cwjcsu.common.Base64;
import com.cwjcsu.learning.security.skip.secure.KeyExchanger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * 
 * @author atlas
 * @date 2012-9-2
 */
public class SkipServer {
	public static void main(String[] args) throws Exception {
		int port = Integer.parseInt("1111");
		// Wait for a connection.
		ServerSocket ss = new ServerSocket(port);
//		System.out.println("Listening on port " + port + "...");
		Socket s = ss.accept();
		DataOutputStream out = new DataOutputStream(s.getOutputStream());
		DataInputStream in = new DataInputStream(s.getInputStream());
		KeyExchanger exchanger = new KeyExchanger(out, in);
		exchanger.exchange();
		System.out.println(Base64.encode(exchanger.generateKey()));

	}
}
