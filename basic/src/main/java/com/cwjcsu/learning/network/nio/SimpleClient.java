package com.cwjcsu.learning.network.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

/**
 * 
 * @author atlas
 * @date 2012-9-27
 */
public class SimpleClient {
	public static void main(String[] args) throws IOException {
		SocketAddress rama = new InetSocketAddress("localhost", 1025);
		SocketChannel client = SocketChannel.open(rama);
		ByteBuffer buffer = ByteBuffer.allocate(74);
		WritableByteChannel out = Channels.newChannel(System.out);
		while (client.read(buffer) != -1) {
			buffer.flip();
			out.write(buffer);
			buffer.clear();
		}
	}
}
