package com.cwjcsu.thirdparty;

import java.io.IOException;
import java.net.ServerSocket;

/*$Id: $
 * author   date   comment
 * cwjcsu@gmail.com  2016年3月9日  Created
*/

public class BindPort {

	public static void main(String[] args) {
		int port = 9999;
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("port " + port + " binded");
			Thread.sleep(1000L * 60 * 60 * 24);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					// ignore exception
				}
			}
		}
	}

}
