package com.cwjcsu.common;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 有的时候需要从一个输入流里面读取不超过一定长度的内容
 * @author atlas
 * @date 2012-12-11
 */
public class LengthLimitedInputStream extends FilterInputStream {

	private int count = 0;
	private int length = 0;

	/**
	 * 只可以从InputStream里面读取length的长度
	 * 
	 * @param in
	 * @param length
	 */
	public LengthLimitedInputStream(InputStream in, int length) {
		super(in);
		this.length = length;
	}

	@Override
	public int read() throws IOException {
		int ava = checkAndReturn(1);
		if (ava > 0) {
			int read = super.read();
			if (read > 0)
				count++;
			return read;
		}
		return -1;
	}

	@Override
	public int read(byte[] b) throws IOException {
		int ava = checkAndReturn(b.length);
		if (ava > 0) {
			int read = super.read(b, 0, ava);
			if (read > 0) {
				count += read;
			}
			return read;
		}
		return -1;
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		int ava = checkAndReturn(len);
		if (ava > 0) {
			int read = super.read(b, off, ava);
			if (read > 0) {
				count += read;
			}
			return read;
		}
		return -1;
	}

	/**
	 * 返回可以实际读取的长度，如果空间足够则返回readLength，否则返回仅剩的空间或者0（空间不足）
	 * 
	 * @param readLength
	 * @return
	 */
	private int checkAndReturn(int readLength) {
		return Math.min(length - count, readLength);
	}

	public int getCount() {
		return count;
	}
}
