package com.cwjcsu.learning.trial;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 
 * @author atlas
 * @date 2013-5-9
 */
public class ByteIntArrayFileInputStream {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		testWriteByte(-2);
	}

	public static void testWriteByte(int aByte) throws IOException {
		File file = new File("test.b");
		file.delete();
		file.createNewFile();
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(aByte);
		fos.close();
		FileInputStream fis = new FileInputStream(file);
		int b = fis.read();
		System.out.println(aByte + "=>" + b + "," + (byte) b + ","
				+ (int) (byte) b);
		file.delete();
	}
	// -1:255
	// -2:254
}
