package com.cwjcsu.learning.security.compress.jzlib;

import com.cwjcsu.learning.security.compress.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;


/**
 * 
 * @author atlas
 * @date 2012-9-7
 */
public class TestServer extends Test {
	public static void main(String[] args) throws IOException {
		int port = Integer.parseInt("1111");
		// Wait for a connection.
		ServerSocket ss = new ServerSocket(port);
		// System.out.println("Listening on port " + port + "...");
		Socket s = ss.accept();
		OutputStream out = s.getOutputStream();
		InputStream in = s.getInputStream();
		if (compressed) {
			out = new DeflaterOutputStream(out);
			in = new InflaterInputStream(in);
		}
		copy(in, out);
	}

}
