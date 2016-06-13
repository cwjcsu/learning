package com.cwjcsu.learning.trial;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

/**
 * 
 * @author atlas
 * @date 2013-3-27
 */
public class ReadWriteDisk {

	static int GB = 1024 * 1024 * 1024;

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		if (args.length != 2) {
			System.err.println("ReadDisk path size");
			System.exit(1);
			return;
		}
		File f = new File(args[0]);
		byte[] data = new byte[10240];
		long size = Long.parseLong(args[1]);
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

			data = new byte[1024];
			is.seek(size - 1024);
			Random r = new Random();
			r.nextBytes(data);
			int sum1 = sum(data);
			is.write(data);
			is.close();
			is = new RandomAccessFile(f, "r");
			data = new byte[1024];
			is.seek(size - 1024);
			ret = is.read(data);
			out("WriteSum", sum1);
			out("ReadReturn", ret);
			out("readData", sum(data));
			is.seek(size);
			out("SeekSize:",is.read());
			is.seek(size+1);
			out("SeekSize+1:",is.read());
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
