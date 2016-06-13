package com.cwjcsu.learning.security.compress;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 
 * @author atlas
 * @date 2012-9-7
 */
public class Test {
	protected static boolean compressed=true;
	public static void copy(InputStream is, OutputStream os) throws IOException {
		byte[] buffer = new byte[10240];
		int read = 0;
		while ((read = is.read(buffer)) != -1) {
			os.write(buffer, 0, read);
		}
	}
	
	
}
