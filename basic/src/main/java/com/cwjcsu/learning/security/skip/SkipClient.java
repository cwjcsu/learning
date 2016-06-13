package com.cwjcsu.learning.security.skip;

import com.cwjcsu.common.Base64;
import com.cwjcsu.learning.security.skip.secure.KeyExchanger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;


/**
 * 
 * @author atlas
 * @date 2012-9-2
 */
public class SkipClient {
	public static void main(String[] args) throws Exception {
		String host = "localhost";
		int port = Integer.parseInt("1111");
		// Open the network connection.
		Socket s = new Socket(host, port);
		DataOutputStream out = new DataOutputStream(s.getOutputStream());
		DataInputStream in = new DataInputStream(s.getInputStream());
		KeyExchanger exchanger = new KeyExchanger(out, in);
		exchanger.exchange();
		System.out.println(Base64.encode(exchanger.generateKey()));
	}
}
