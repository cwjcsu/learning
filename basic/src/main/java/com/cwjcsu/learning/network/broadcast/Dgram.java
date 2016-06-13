package com.cwjcsu.learning.network.broadcast;

import java.net.DatagramPacket;
import java.net.InetAddress;

/**
 * 
 * @author atlas
 * @date 2012-9-26
 */
public class Dgram {
	public static DatagramPacket toDatagram(String s, InetAddress destIA,
			int destPort) {
		// Deprecated in Java 1.1, but it works:
		byte[] buf = s.getBytes();
		// The correct Java 1.1 approach, but it's
		// Broken (it truncates the String):
		// byte[] buf = s.getBytes();
		return new DatagramPacket(buf, buf.length, destIA, destPort);
	}

	public static String toString(DatagramPacket p) {
		// The Java 1.0 approach:
		// return new String(p.getData(),
		// 0, 0, p.getLength());
		// The Java 1.1 approach:
		return new String(p.getData());
	}
}
