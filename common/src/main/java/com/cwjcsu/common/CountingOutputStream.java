package com.cwjcsu.common;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 
 * @author atlas
 * @date 2012-12-11
 */
public class CountingOutputStream extends FilterOutputStream {

	private long count = 0;

	public CountingOutputStream(OutputStream out) {
		super(out);
	}

	@Override
	public void write(byte[] b) throws IOException {
		super.write(b);
		count += b.length;
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		super.write(b, off, len);
		count += len;
	}

	@Override
	public void write(int b) throws IOException {
		super.write(b);
		count++;
	}

	public long getCount() {
		return count;
	}
}
