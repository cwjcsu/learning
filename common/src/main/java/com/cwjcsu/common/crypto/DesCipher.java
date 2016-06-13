package com.cwjcsu.common.crypto;

import com.cwjcsu.common.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.UnsupportedEncodingException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

public final class DesCipher {
	private Cipher ecipher;
	private Cipher dcipher;
	private static final Logger LOG = LoggerFactory.getLogger(DesCipher.class);
	// 8-byte Salt
	private static byte[] salt = { (byte) 0xE9, (byte) 0x9B, (byte) 0xC8,
			(byte) 0x32, (byte) 0xA6, (byte) 0x45, (byte) 0xE3, (byte) 0x03 };

	private static final String DEFAULT_PHRASE = "5t03'Z.)Rn64u1+/b02laLs(]0g.Y/N1)V')Poi143+)-)J(OK,[s09f/.SU6selHQ07p*WO811g";

	private static DesCipher INSTANCE = new DesCipher(DEFAULT_PHRASE, salt);
	// Iteration count
	int iterationCount = 19;

	private DesCipher(String passPhrase, byte[] salt) {
		try {
			// Create the key
			KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), salt,
					iterationCount);
			SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES")
					.generateSecret(keySpec);
			ecipher = Cipher.getInstance(key.getAlgorithm());
			dcipher = Cipher.getInstance(key.getAlgorithm());

			// Prepare the parameter to the ciphers
			AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt,
					iterationCount);

			// Create the ciphers
			ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
			dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
		} catch (Exception e) {
			LOG.error("Create DesCipher error: " + e.getMessage() , e);
		}
	}

	public String doEncrypt(String str) {
		Exception e1 = null;
		try {
			// Encode the string into bytes using utf-8
			byte[] utf8 = str.getBytes("UTF8");

			// Encrypt
			byte[] enc = ecipher.doFinal(utf8);

			// Encode bytes to hex string
			return StringUtils.bytesToHex(enc);
		} catch (javax.crypto.BadPaddingException e) {
			e1 = e;
		} catch (IllegalBlockSizeException e) {
			e1 = e;
		} catch (UnsupportedEncodingException e) {
			e1 = e;
		}
		if (e1 != null) {
			LOG.warn("doEncrypt failed", e1);
			throw new RuntimeException("encrypt failed.", e1);
		}
		return null;
	}

	public String doDecrypt(String str) {
		Exception e1 = null;
		try {
			byte[] dec = StringUtils.hexToBytes(str);
			// Decrypt
			byte[] utf8 = dcipher.doFinal(dec);

			// Decode using utf-8
			return new String(utf8, "UTF8");
		} catch (javax.crypto.BadPaddingException e) {
			e1 = e;
		} catch (IllegalBlockSizeException e) {
			e1 = e;
		} catch (UnsupportedEncodingException e) {
			e1 = e;
		}
		if (e1 != null) {
			LOG.warn("doDecrypt failed", e1);
			throw new RuntimeException("decrypt failed.", e1);
		}
		return null;
	}

	public static DesCipher getDesCipher(String saltStr) {
		byte[] salt = new byte[8];
		System.arraycopy(DesCipher.salt, 0, salt, 0, 8);
		if (saltStr != null) {
			try {
				byte[] bytes = saltStr.getBytes("UTF-8");
				for (int i = 0; i < bytes.length; i++) {
					salt[i % 8] ^= bytes[i];
				}
			} catch (UnsupportedEncodingException e) {
			}
		}
		return new DesCipher(DEFAULT_PHRASE, salt);
	}

	public static String encrypt(String str) {
		return INSTANCE.doEncrypt(str);
	}

	public static String decrypt(String str) {
		return INSTANCE.doDecrypt(str);
	}

	public static String encrypt(String salt, String str) {
		DesCipher dc = DesCipher.getDesCipher(salt);
		return dc.doEncrypt(str);
	}
	public static String decrypt(String salt, String str) {
		DesCipher dc = DesCipher.getDesCipher(salt);
		return dc.doDecrypt(str);
	}

	public static void main(String[] args) {
		 String msg =
		 "passwordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswo rdpasswordpasswordpassword";
		 String encrypted = encrypt(msg);
		 System.out.println(msg.length());
		 System.out.println(encrypted.length());
		 System.out.println(encrypted);
		 String decrypted = decrypt(encrypted);
		 System.out.println(decrypted);
		 System.out.println(decrypted.equals(msg));
		// String s =
		// "passwordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpassword";
		testSaltStr();
	}

	public static void testSaltStr() {
		String msg = "passwordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpassword";
		String encrypted = DesCipher.encrypt("atlas", msg);
		System.out.println(msg.length());
		System.out.println(encrypted.length());
		System.out.println(encrypted);
		String decrypted = DesCipher.decrypt("atlas", encrypted);
		System.out.println(decrypted);
		System.out.println(decrypted.equals(msg));
		String s = "passwordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpasswordpassword";

	}

}
