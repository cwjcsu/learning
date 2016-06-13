package com.cwjcsu.thirdparty;

import com.cwjcsu.common.Base64;

import java.util.Random;


/*$Id: $
 * author                     date    comment
 * cwjcsu@gmail.com  2015年9月22日  Created
 */
/**
 * 
 * @author atlas
 *
 */
public class EightBitUUID {
	static Random r = new Random();
	private static final char[] base = { 'A', 'B', 'C', 'D', 'E', 'F', 'G',
			'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
			'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
			'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
			'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', '+', '/' };

	public static void main(String[] args) {
		// System.out.println(Long.toString(Long.MAX_VALUE, 64));
		byte[] data = new byte[16];
		r.nextBytes(data);
		System.out.println(to64RadixString(data));
	}

	public static String shortUUID() {
		byte[] data = new byte[16];
		r.nextBytes(data);
		return Base64.encode(data);
	}

	public static String to64RadixString(long i) {
		int radix = 64;
		char[] buf = new char[65];
		int charPos = 64;
		boolean negative = (i < 0);
		if (!negative) {
			i = -i;
		}
		while (i <= -radix) {
			buf[charPos--] = base[(int) (-(i % radix))];
			i = i / radix;
		}
		buf[charPos] = base[(int) (-i)];

		if (negative) {
			buf[--charPos] = '-';
		}

		return new String(buf, charPos, (65 - charPos));
	}

	public static String to64RadixString(byte[] data) {
		int radix = 64;
		char[] buf = new char[data.length * 2];
		int charPos = 0;

		for (int i = 0; i < data.length; i++) {
			// 转换到[0,256]
			int d = data[i] - Byte.MIN_VALUE;
			buf[charPos++] = base[d % 64];
			buf[charPos++] = base[d & 0x0F];
		}
		return new String(buf);
	}

}
