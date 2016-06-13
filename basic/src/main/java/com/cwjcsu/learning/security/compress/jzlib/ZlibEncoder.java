package com.cwjcsu.learning.security.compress.jzlib;

import com.cwjcsu.learning.compress.jzlib.JZlib;
import com.cwjcsu.learning.compress.jzlib.ZStream;

import java.util.concurrent.atomic.AtomicBoolean;


/**
 * Compresses a byte array using the deflate algorithm.
 * 
 */
public class ZlibEncoder {

	private static final byte[] EMPTY_ARRAY = new byte[0];

	private final ZStream z = new ZStream();
	private final AtomicBoolean finished = new AtomicBoolean();


	/**
	 * Creates a new zlib encoder with the default compression level ({@code 6}
	 * ), default window bits ({@code 15}), default memory level ({@code 8}),
	 * and the default wrapper ({@link ZlibWrapper#ZLIB}).
	 * 
	 * @throws CompressionException
	 *             if failed to initialize zlib
	 */
	public ZlibEncoder() {
		this(6);
	}

	/**
	 * Creates a new zlib encoder with the specified {@code compressionLevel},
	 * default window bits ({@code 15}), default memory level ({@code 8}), and
	 * the default wrapper ({@link ZlibWrapper#ZLIB}).
	 * 
	 * @param compressionLevel
	 *            {@code 1} yields the fastest compression and {@code 9} yields
	 *            the best compression. {@code 0} means no compression. The
	 *            default compression level is {@code 6}.
	 * 
	 * @throws CompressionException
	 *             if failed to initialize zlib
	 */
	public ZlibEncoder(int compressionLevel) {
		this(ZlibWrapper.ZLIB, compressionLevel);
	}

	/**
	 * Creates a new zlib encoder with the default compression level ({@code 6}
	 * ), default window bits ({@code 15}), default memory level ({@code 8}),
	 * and the specified wrapper.
	 * 
	 * @throws CompressionException
	 *             if failed to initialize zlib
	 */
	public ZlibEncoder(ZlibWrapper wrapper) {
		this(wrapper, 6);
	}

	/**
	 * Creates a new zlib encoder with the specified {@code compressionLevel},
	 * default window bits ({@code 15}), default memory level ({@code 8}), and
	 * the specified wrapper.
	 * 
	 * @param compressionLevel
	 *            {@code 1} yields the fastest compression and {@code 9} yields
	 *            the best compression. {@code 0} means no compression. The
	 *            default compression level is {@code 6}.
	 * 
	 * @throws CompressionException
	 *             if failed to initialize zlib
	 */
	public ZlibEncoder(ZlibWrapper wrapper, int compressionLevel) {
		this(wrapper, compressionLevel, 15, 8);
	}

	/**
	 * Creates a new zlib encoder with the specified {@code compressionLevel},
	 * the specified {@code windowBits}, the specified {@code memLevel}, and the
	 * specified wrapper.
	 * 
	 * @param compressionLevel
	 *            {@code 1} yields the fastest compression and {@code 9} yields
	 *            the best compression. {@code 0} means no compression. The
	 *            default compression level is {@code 6}.
	 * @param windowBits
	 *            The base two logarithm of the size of the history buffer. The
	 *            value should be in the range {@code 9} to {@code 15}
	 *            inclusive. Larger values result in better compression at the
	 *            expense of memory usage. The default value is {@code 15}.
	 * @param memLevel
	 *            How much memory should be allocated for the internal
	 *            compression state. {@code 1} uses minimum memory and {@code 9}
	 *            uses maximum memory. Larger values result in better and faster
	 *            compression at the expense of memory usage. The default value
	 *            is {@code 8}
	 * 
	 * @throws CompressionException
	 *             if failed to initialize zlib
	 */
	public ZlibEncoder(ZlibWrapper wrapper, int compressionLevel,
			int windowBits, int memLevel) {
		if (compressionLevel < 0 || compressionLevel > 9) {
			throw new IllegalArgumentException("compressionLevel: "
					+ compressionLevel + " (expected: 0-9)");
		}
		if (windowBits < 9 || windowBits > 15) {
			throw new IllegalArgumentException("windowBits: " + windowBits
					+ " (expected: 9-15)");
		}
		if (memLevel < 1 || memLevel > 9) {
			throw new IllegalArgumentException("memLevel: " + memLevel
					+ " (expected: 1-9)");
		}
		if (wrapper == null) {
			throw new NullPointerException("wrapper");
		}
		if (wrapper == ZlibWrapper.ZLIB_OR_NONE) {
			throw new IllegalArgumentException("wrapper '"
					+ ZlibWrapper.ZLIB_OR_NONE + "' is not "
					+ "allowed for compression.");
		}

		synchronized (z) {
			int resultCode = z.deflateInit(compressionLevel, windowBits,
					memLevel, ZlibUtil.convertWrapperType(wrapper));
			if (resultCode != JZlib.Z_OK) {
				ZlibUtil.fail(z, "initialization failure", resultCode);
			}
		}
	}

	/**
	 * Creates a new zlib encoder with the default compression level ({@code 6}
	 * ), default window bits ({@code 15}), default memory level ({@code 8}),
	 * and the specified preset dictionary. The wrapper is always
	 * {@link ZlibWrapper#ZLIB} because it is the only format that supports the
	 * preset dictionary.
	 * 
	 * @param dictionary
	 *            the preset dictionary
	 * 
	 * @throws CompressionException
	 *             if failed to initialize zlib
	 */
	public ZlibEncoder(byte[] dictionary) {
		this(6, dictionary);
	}

	/**
	 * Creates a new zlib encoder with the specified {@code compressionLevel},
	 * default window bits ({@code 15}), default memory level ({@code 8}), and
	 * the specified preset dictionary. The wrapper is always
	 * {@link ZlibWrapper#ZLIB} because it is the only format that supports the
	 * preset dictionary.
	 * 
	 * @param compressionLevel
	 *            {@code 1} yields the fastest compression and {@code 9} yields
	 *            the best compression. {@code 0} means no compression. The
	 *            default compression level is {@code 6}.
	 * @param dictionary
	 *            the preset dictionary
	 * 
	 * @throws CompressionException
	 *             if failed to initialize zlib
	 */
	public ZlibEncoder(int compressionLevel, byte[] dictionary) {
		this(compressionLevel, 15, 8, dictionary);
	}

	/**
	 * Creates a new zlib encoder with the specified {@code compressionLevel},
	 * the specified {@code windowBits}, the specified {@code memLevel}, and the
	 * specified preset dictionary. The wrapper is always
	 * {@link ZlibWrapper#ZLIB} because it is the only format that supports the
	 * preset dictionary.
	 * 
	 * @param compressionLevel
	 *            {@code 1} yields the fastest compression and {@code 9} yields
	 *            the best compression. {@code 0} means no compression. The
	 *            default compression level is {@code 6}.
	 * @param windowBits
	 *            The base two logarithm of the size of the history buffer. The
	 *            value should be in the range {@code 9} to {@code 15}
	 *            inclusive. Larger values result in better compression at the
	 *            expense of memory usage. The default value is {@code 15}.
	 * @param memLevel
	 *            How much memory should be allocated for the internal
	 *            compression state. {@code 1} uses minimum memory and {@code 9}
	 *            uses maximum memory. Larger values result in better and faster
	 *            compression at the expense of memory usage. The default value
	 *            is {@code 8}
	 * @param dictionary
	 *            the preset dictionary
	 * 
	 * @throws CompressionException
	 *             if failed to initialize zlib
	 */
	public ZlibEncoder(int compressionLevel, int windowBits, int memLevel,
			byte[] dictionary) {
		if (compressionLevel < 0 || compressionLevel > 9) {
			throw new IllegalArgumentException("compressionLevel: "
					+ compressionLevel + " (expected: 0-9)");
		}
		if (windowBits < 9 || windowBits > 15) {
			throw new IllegalArgumentException("windowBits: " + windowBits
					+ " (expected: 9-15)");
		}
		if (memLevel < 1 || memLevel > 9) {
			throw new IllegalArgumentException("memLevel: " + memLevel
					+ " (expected: 1-9)");
		}
		if (dictionary == null) {
			throw new NullPointerException("dictionary");
		}

		synchronized (z) {
			int resultCode;
			resultCode = z.deflateInit(compressionLevel, windowBits, memLevel,
					JZlib.W_ZLIB); // Default: ZLIB format
			if (resultCode != JZlib.Z_OK) {
				ZlibUtil.fail(z, "initialization failure", resultCode);
			} else {
				resultCode = z.deflateSetDictionary(dictionary,
						dictionary.length);
				if (resultCode != JZlib.Z_OK) {
					ZlibUtil.fail(z, "failed to set the dictionary", resultCode);
				}
			}
		}
	}

	public void close() {
		finishEncode();
	}

	public boolean isClosed() {
		return finished.get();
	}

	protected Object encode(byte[] in) throws Exception {
		if (finished.get()) {
			return in;
		}
		synchronized (z) {
			try {
				// Configure input.
				z.next_in = in;
				z.next_in_index = 0;
				z.avail_in = in.length;

				// Configure output.
				byte[] out = new byte[(int) Math.ceil(in.length * 1.001) + 12];
				z.next_out = out;
				z.next_out_index = 0;
				z.avail_out = out.length;

				// Note that Z_PARTIAL_FLUSH has been deprecated.
				int resultCode = z.deflate(JZlib.Z_SYNC_FLUSH);
				if (resultCode != JZlib.Z_OK) {
					ZlibUtil.fail(z, "compression failure", resultCode);
				}

				if (z.next_out_index != 0) {
					// out, 0, z.next_out_index
					return null;
				} else {
					return EMPTY_ARRAY;
				}
			} finally {
				z.next_in = null;
				z.next_out = null;
			}
		}
	}

	class ByteArray {
		byte[] data;
		int startIndex;
		int endIndex;
	}

	private ByteArray finishEncode() {
		// if (!finished.compareAndSet(false, true)) {
		// if (evt != null) {
		// ctx.sendDownstream(evt);
		// }
		// return Channels.succeededFuture(ctx.getChannel());
		// }

		ByteArray ba = new ByteArray();

		synchronized (z) {
			try {
				// Configure input.
				z.next_in = EMPTY_ARRAY;
				z.next_in_index = 0;
				z.avail_in = 0;

				// Configure output.
				byte[] out = new byte[32]; // room for ADLER32 + ZLIB / CRC32 +
											// GZIP header
				z.next_out = out;
				z.next_out_index = 0;
				z.avail_out = out.length;

				// Write the ADLER32 checksum (stream footer).
				int resultCode = z.deflate(JZlib.Z_FINISH);
				if (resultCode != JZlib.Z_OK
						&& resultCode != JZlib.Z_STREAM_END) {
					throw ZlibUtil.exception(z, "compression failure", resultCode);
				} else if (z.next_out_index != 0) {
					ba.data = out;
					ba.startIndex = 0;
					ba.endIndex = z.next_out_index;
					return ba;
				} else {
					// Note that we should never use a SucceededChannelFuture
					// here just in case any downstream handler or a sink wants
					// to notify a write error.
					// return empty;
					return null;
				}
			} finally {
				z.deflateEnd();
				z.next_in = null;
				z.next_out = null;
			}
		}
	}
}
