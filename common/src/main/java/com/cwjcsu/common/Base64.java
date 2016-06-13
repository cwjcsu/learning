package com.cwjcsu.common;

/**
 * Base64 编码解码算法
 * 
 * @author atlas
 */
public class Base64 {
	public static String encode(byte[] raw) {
		return org.apache.commons.codec.binary.Base64.encodeBase64String(raw);
	}

	public static byte[] decode(String base64) {
		return org.apache.commons.codec.binary.Base64.decodeBase64(base64);
	}

}