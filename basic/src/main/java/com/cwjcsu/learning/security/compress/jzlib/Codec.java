package com.cwjcsu.learning.security.compress.jzlib;


import com.cwjcsu.learning.compress.jzlib.JZlib;
import com.cwjcsu.learning.compress.jzlib.ZStream;

/**
 * 
 * @author atlas
 * @date 2012-9-8
 */
public class Codec {
	ZStream z = new ZStream();
	
	byte[] dictionary;
	
	 private volatile boolean finished;
	
	class ByteArray{
		byte[] data;
		int startIndex;
		int endIndex;
	}

	public byte[] encode(byte[] in) {
		try {
			synchronized (z) {
				z.next_in = in;
				z.next_in_index = 0;
				z.avail_in = in.length;

				byte[] out = new byte[(int) Math.ceil(in.length * 1.001) + 12];
				z.next_out = out;
				z.next_out_index = 0;
				z.avail_out = out.length;
				z.next_out = out;
				z.next_out_index = 0;
				z.avail_out = out.length;
				int resultCode = z.deflate(JZlib.Z_SYNC_FLUSH);
				if (resultCode != JZlib.Z_OK) {
					// ZlibUtil.fail(z, "compression failure", resultCode);
					// throw
				}

				if (z.next_out_index != 0) {
					// [0,z.next_out_index]
					return out;
				} else {
					return new byte[0];// nothing.
				}
			}
		} finally {
			z.next_in = null;
			z.next_out = null;
		}
	}

	protected ByteArray decode(byte[] in) throws Exception {

		if(finished){
			//return in;//
		}
		synchronized (z) {
			try {
				// Configure input.

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
						ByteArray ba = new ByteArray();
						ba.data = out;
						ba.startIndex=0;
						ba.endIndex=z.next_out_index;
						return ba;
					}
					z.next_out_index = 0;

					switch (resultCode) {
					case JZlib.Z_NEED_DICT:
						if (dictionary == null) {
//							ZlibUtil.fail(z, "decompression failure",
//									resultCode);
							//exception.
						} else {
							resultCode = z.inflateSetDictionary(dictionary,
									dictionary.length);
							if (resultCode != JZlib.Z_OK) {
//								ZlibUtil.fail(z,
//										"failed to set the dictionary",
//										resultCode);
								//exception.
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
//						ZlibUtil.fail(z, "decompression failure", resultCode);
					}
				}

				if ( z.next_out_index != 0) {
					ByteArray ba = new ByteArray();
					ba.data = out;
					ba.startIndex=0;
					ba.endIndex=z.next_out_index;
					return ba;
				} else {
					return null;
				}
			} finally {
				z.next_in = null;
				z.next_out = null;
			}
		}
	}
}
