package com.cwjcsu.learning.trial;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * 
 * @author atlas
 * @date 2013-3-27
 */
public class ReadDisk {

	static int GB = 1024 * 1024 * 1024;

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		if (args.length != 1) {
			System.err.println("ReadDisk path");
			System.exit(1);
			return;
		}
		File f = new File(args[0]);
		byte[] data = new byte[10240];
		try {
			out("AbsPath", f.getAbsolutePath());
			out("Length", f.length());
			out("LastModified", f.lastModified());
			out("Exists", f.exists());
			out("FreeSpace", f.getFreeSpace());
			out("FreeSpaceGB", f.getFreeSpace() * 1.0 / GB);

			out("UsableSpace", f.getUsableSpace());
			out("UsableSpaceGB", f.getUsableSpace() * 1.0 / GB);

			out("TotalSpace", f.getTotalSpace());
			out("TotalSpaceGB", f.getTotalSpace() * 1.0 / GB);
			out("isFile", f.isFile());
			out("isDir", f.isDirectory());
			out("isAbsolute", f.isAbsolute());
			out("canRead", f.canRead());
			out("canWrite", f.canWrite());
			RandomAccessFile is = new RandomAccessFile(f, "rw");
			int ret = is.read(data);
			out("ReadReturn", ret);
			out("readData", sum(data));
			FileChannel fc = is.getChannel();
			out("FileChannel.size",fc.size());
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	static int sum(byte[] data) {
		int s = 0;
		for (byte b : data) {
			s += b;
		}
		return s;
	}

	static void out(String msg, Object out) {
		System.out.println(msg + ":" + out);
	}

}
