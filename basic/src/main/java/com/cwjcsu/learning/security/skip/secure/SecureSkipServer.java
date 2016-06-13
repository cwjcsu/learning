package com.cwjcsu.learning.security.skip.secure;

import com.cwjcsu.common.Base64;
import com.cwjcsu.learning.security.x509.gen.KeyInfo;
import com.cwjcsu.learning.security.x509.gen.Reader;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * 
 * @author atlas
 * @date 2012-9-6
 */
public class SecureSkipServer {
	public static void main(String[] args) throws Exception {
		int port = Integer.parseInt("1111");
		// Wait for a connection.
		ServerSocket ss = new ServerSocket(port);
		System.out.println("Listening on port " + port + "...");
		Socket s = ss.accept();
		DataOutputStream out = new DataOutputStream(s.getOutputStream());
		DataInputStream in = new DataInputStream(s.getInputStream());
		KeyInfo info = Reader
				.read("resource/atlas-alice.jks", "alice", "alice");
		SecureKeyExchanger exchanger = new SecureKeyExchanger(out, in,
				info.getPrivateKey(), info.getCertificate(),
				info.getCaCertificate());
		exchanger.init();
		exchanger.sendDHPublicKey();
		exchanger.receiveDHPublicKey();
		byte[] secret = exchanger.generateKey();
		// Clean up.
		out.close();
		in.close();
		// Print out the secret value
		System.out.println(Base64.encode(secret));

	}
}
