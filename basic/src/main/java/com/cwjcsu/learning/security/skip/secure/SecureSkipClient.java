package com.cwjcsu.learning.security.skip.secure;

import com.cwjcsu.common.Base64;
import com.cwjcsu.learning.security.x509.gen.KeyInfo;
import com.cwjcsu.learning.security.x509.gen.Reader;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;


/**
 * 
 * @author atlas
 * @date 2012-9-6
 */
public class SecureSkipClient {
	// bob
	public static void main(String[] args) throws Exception {
		String host = "localhost";
		int port = Integer.parseInt("1111");
		// Open the network connection.
		Socket s = new Socket(host, port);
		DataOutputStream out = new DataOutputStream(s.getOutputStream());
		DataInputStream in = new DataInputStream(s.getInputStream());
		KeyInfo info = Reader.read("resource/atlas-bob.jks", "bob", "bob");
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
