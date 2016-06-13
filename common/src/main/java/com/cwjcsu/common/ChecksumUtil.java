package com.cwjcsu.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

/**
 * Utilities of calculating file/string hash checksum, hash types support "md5"
 * "sha1" "sha256" "sha384" & "sha512"<br>
 * not supported yet:ripemd160,tiger192,haval-6-160. this algorithm maybe used
 * in rpm hash.
 * 
 * @author atlas
 * 
 */
public class ChecksumUtil {
	private static Map<String, String> TypeMap = new HashMap<String, String>();
	static {
		TypeMap.put("md5", "MD5");
		TypeMap.put("md2", "MD2");
		TypeMap.put("sha1", "SHA1");
		TypeMap.put("sha256", "SHA-256");
		TypeMap.put("sha384", "SHA-384");
		TypeMap.put("sha512", "SHA-512");
	}

	public static String getFileMD5Checksum(String file) {
		return getFileChecksum(file, "md5");
	}

	public static String getFileSha1Checksum(String file) {
		return getFileChecksum(file, "sha1");
	}

	public static String getFileSha256Checksum(String file) {
		return getFileChecksum(file, "sha256");
	}

	public static String getFileSha384Checksum(String file) {
		return getFileChecksum(file, "sha384");
	}

	public static String getFileSha512Checksum(String file) {
		return getFileChecksum(file, "sha512");
	}

	/**
	 * Get file hash checksum of specified algorithm.
	 * 
	 * @param fileName
	 * @param algorithm
	 *            the hash algorithm among "md5" "sha1" "sha256" "sha384"
	 *            "sha512"
	 * @return hash value in hex string.
	 * @throws Exception
	 *             when algorithm not found or file io error.
	 */
	public static String getFileChecksum(String fileName, String algorithm) {
		String type = TypeMap.get(algorithm);
		if (type == null)
			type = algorithm;
		InputStream fis = null;
		try {
			File f = new File(fileName);
			if (!f.isFile() || !f.canRead()) {
				throw new IOException("file not exists or can't be read "
						+ fileName);
			}
			fis = new FileInputStream(f);
			byte[] buffer = new byte[65536];
			MessageDigest alg = MessageDigest.getInstance(type);
			int numRead = 0;
			while ((numRead = fis.read(buffer)) > 0) {
				alg.update(buffer, 0, numRead);
			}
			return StringUtils.bytesToHex(alg.digest());
		} catch (Exception e) {
			throw new RuntimeException(
					"Problem caculating file checksum using algorithm "
							+ algorithm, e);
		} finally {
			if (fis != null)
				try {
					fis.close();
				} catch (IOException e) {
					// ignore
				}
		}
	}

	public static String getSha1Checksum(Object... args) {
		return getChecksum(TypeMap.get("sha1"), args);
	}

	public static String getMd5Checksum(Object... args) {
		return getChecksum(TypeMap.get("md5"), args);
	}

	/**
	 * calculating checksum and encode the result byte array to hex string.
	 * 
	 * @param algorithm
	 * @param args
	 *            should not consists of null value.
	 * @return
	 */
	public static String getChecksum(String algorithm, Object... args) {
		String type = TypeMap.get(algorithm);
		if (type == null)
			type = algorithm;
		try {
			MessageDigest alg = MessageDigest.getInstance(type);
			return StringUtils.bytesToHex(checksum(alg,args));
		} catch (Exception e) {
			throw new RuntimeException(
					"Problem caculating file checksum using algorithm "
							+ algorithm, e);
		}
	}

	static byte[] checksum(MessageDigest md, Object... args) {
		for (Object arg : args) {
			byte[] input = null;
			if (arg.getClass().equals(byte[].class)) {
				input = (byte[]) arg;
			} else {
				try {
					input = arg.toString().getBytes("UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					input = arg.toString().getBytes();
				}
			}
			md.update(input);
		}
		return md.digest();
	}

	/**
	 * calculating checksum and return bytes
	 * 
	 * @param algorithm
	 * @param args
	 *            should not consists of null value.
	 * @return
	 */
	public static byte[] getChecksumAsBytes(String algorithm, Object... args) {
		String type = TypeMap.get(algorithm);
		if (type == null)
			type = algorithm;
		try {
			MessageDigest alg = MessageDigest.getInstance(type);
			return checksum(alg, args);
		} catch (Exception e) {
			throw new RuntimeException(
					"Problem caculating file checksum using algorithm "
							+ algorithm, e);
		}
	}
}
