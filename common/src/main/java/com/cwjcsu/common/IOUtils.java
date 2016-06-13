package com.cwjcsu.common;

import com.cwjcsu.common.exec.ExecuteResult;
import com.cwjcsu.common.exec.LocalCommandService;

import java.io.*;

/**
 * 
 * @author atlas
 * @date 2012-10-9
 */
public class IOUtils {
	public static void copy(InputStream is, OutputStream os) throws IOException {
		byte[] buffer = new byte[1024];
		int read = 0;
		while ((read = is.read(buffer)) != -1) {
			os.write(buffer, 0, read);
		}
	}

	public static void copy(File from, File to) throws IOException {
		if (from.equals(to))
			return;
		if (!to.exists()) {
			to.createNewFile();
		}
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(from);
			os = new FileOutputStream(to);
			copy(is, os);
		} catch (IOException e) {
			throw e;
		} finally {
			closeQuietly(is);
			closeQuietly(os);
		}
	}

	public static void closeQuietly(Closeable c) {
		try {
			if (c != null)
				c.close();
		} catch (IOException e) {
		}
	}

	public static String getStreamContent(InputStream is, String charset)
			throws IOException {
		return getStreamContent(is, charset, -1);
	}

	public static byte[] getStreamContentAsBytes(InputStream is)
			throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream(8196);
		copy(is, os);
		return os.toByteArray();
	}

	public static String getStreamContent(InputStream is, String charset,
			int buffsize) throws IOException {
		if (charset == null) {
			charset = "UTF-8";
		}
		if (buffsize <= 0) {
			buffsize = 10240;
		}
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream(buffsize);
			copy(is, os);
			return os.toString(charset);
		} catch (IOException e) {
			throw e;
		}
	}

	public static String getStreamContent(InputStream is) throws IOException {
		return getStreamContent(is, null, -1);
	}

	public static String getStreamContent(InputStream is, int buffsize)
			throws IOException {
		return getStreamContent(is, null, buffsize);
	}

	public static void copyWriter(BufferedReader input, StringWriter writer)
			throws IOException {
		char[] cbuf = new char[1024];
		int read = 0;
		while ((read = input.read(cbuf)) != -1) {
			writer.write(cbuf, 0, read);
		}
	}

	public static int readInt(InputStream input) throws IOException {
		byte[] bytes = new byte[4];
		input.read(bytes);
		int len = ((bytes[0] << 24) & 0xff000000)
				| ((bytes[1] << 16) & 0x00ff0000)
				| ((bytes[2] << 8) & 0x0000ff00) | ((bytes[3]) & 0x000000ff);
		return len;
	}

	public static void writeInt(OutputStream input, int v) throws IOException {
		byte[] bytes = new byte[4];
		bytes[0] = (byte) (v >>> 24);
		bytes[1] = (byte) (v >>> 16);
		bytes[2] = (byte) (v >>> 8);
		bytes[3] = (byte) (v >>> 0);
		input.write(bytes);
	}

	/**
	 * 优先采用下面方法复制文件： 1，linux mv命令； 2，Java API renameTo； 3，流拷贝；
	 * 
	 * @param from
	 * @param to
	 * @throws IOException
	 * @return 1,2,3分别代表最终用上述哪种方式完成拷贝
	 */
	public static int safeMove(File from, File to) throws IOException {
		if (OsUtil.isUnix()) {
			ExecuteResult er = LocalCommandService.getInstance().executeCommand(_.$("mv -f -T {} {}",
					from.getCanonicalPath(), to.getCanonicalPath()).split("\\s++"),5000);
			if (er.getExitCode() == 0) {
				return 1;
			} else {
				_.COMMON.error("mv fail-{}:{}", er.getFailType(),er.getErrorOut());
			}
		}
		// in windows,this will fail
		if (from.renameTo(to)) {
			return 2;
		}
		copy(from, to);
		from.delete();
		return 3;
	}
}
