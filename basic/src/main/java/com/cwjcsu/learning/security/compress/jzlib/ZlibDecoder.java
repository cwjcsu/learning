/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.cwjcsu.learning.security.compress.jzlib;

import com.cwjcsu.learning.compress.jzlib.JZlib;
import com.cwjcsu.learning.compress.jzlib.ZStream;

/**
 * Decompresses a {@link ChannelBuffer} using the deflate algorithm.
 * 
 * @apiviz.landmark
 * @apiviz.has org.jboss.netty.handler.codec.compression.ZlibWrapper
 */
public class ZlibDecoder {

	private final ZStream z = new ZStream();
	private byte[] dictionary;
	private volatile boolean finished;

	/**
	 * Creates a new instance with the default wrapper ({@link ZlibWrapper#ZLIB}
	 * ).
	 * 
	 * @throws CompressionException
	 *             if failed to initialize zlib
	 */
	public ZlibDecoder() {
		this(ZlibWrapper.ZLIB);
	}

	/**
	 * Creates a new instance with the specified wrapper.
	 * 
	 * @throws CompressionException
	 *             if failed to initialize zlib
	 */
	public ZlibDecoder(ZlibWrapper wrapper) {
		if (wrapper == null) {
			throw new NullPointerException("wrapper");
		}

		synchronized (z) {
			int resultCode = z
					.inflateInit(ZlibUtil.convertWrapperType(wrapper));
			if (resultCode != JZlib.Z_OK) {
				ZlibUtil.fail(z, "initialization failure", resultCode);
			}
		}
	}

	/**
	 * Creates a new instance with the specified preset dictionary. The wrapper
	 * is always {@link ZlibWrapper#ZLIB} because it is the only format that
	 * supports the preset dictionary.
	 * 
	 * @throws CompressionException
	 *             if failed to initialize zlib
	 */
	public ZlibDecoder(byte[] dictionary) {
		if (dictionary == null) {
			throw new NullPointerException("dictionary");
		}
		this.dictionary = dictionary;

		synchronized (z) {
			int resultCode;
			resultCode = z.inflateInit(JZlib.W_ZLIB);
			if (resultCode != JZlib.Z_OK) {
				ZlibUtil.fail(z, "initialization failure", resultCode);
			}
		}
	}

	/**
	 * Returns {@code true} if and only if the end of the compressed stream has
	 * been reached.
	 */
	public boolean isClosed() {
		return finished;
	}

	class ByteArray {
		ByteArray() {

		}

		public ByteArray(byte[] in) {
		}

		byte[] data;
		int startIndex;
		int endIndex;
	}

	protected ByteArray decode(byte[] in) throws Exception {
		if (finished) {
			return new ByteArray(in);
		}

		ByteArray ba = new ByteArray();
		synchronized (z) {
			try {
				z.next_in = in;
				z.next_in_index = 0;
				z.avail_in = in.length;

				// Configure output.
				byte[] out = new byte[in.length << 1];
				z.next_out = out;
				z.next_out_index = 0;
				z.avail_out = out.length;

				loop: for (;;) {
					// Decompress 'in' into 'out'
					int resultCode = z.inflate(JZlib.Z_SYNC_FLUSH);
					if (z.next_out_index > 0) {
						// out, 0, z.next_out_index
						z.avail_out = out.length;
						ba.data=out;
						ba.startIndex=0;
						ba.endIndex=z.next_out_index;
					}
					z.next_out_index = 0;

					switch (resultCode) {
					case JZlib.Z_NEED_DICT:
						if (dictionary == null) {
							ZlibUtil.fail(z, "decompression failure",
									resultCode);
							return null;
						} else {
							resultCode = z.inflateSetDictionary(dictionary,
									dictionary.length);
							if (resultCode != JZlib.Z_OK) {
								ZlibUtil.fail(z,
										"failed to set the dictionary",
										resultCode);
							}
						}
						break;
					case JZlib.Z_STREAM_END:
						finished = true; // Do not decode anymore.
						z.inflateEnd();
						break loop;
					case JZlib.Z_OK:
						break;
					case JZlib.Z_BUF_ERROR:
						if (z.avail_in <= 0) {
							break loop;
						}
						break;
					default:
						ZlibUtil.fail(z, "decompression failure", resultCode);
					}
				}

				// return
			} finally {
				z.next_in = null;
				z.next_out = null;
			}
		}
		return ba;
	}
}
