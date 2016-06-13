package com.cwjcsu.common.crypto;

import com.cwjcsu.common.ChecksumUtil;
import com.cwjcsu.common.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 使用AES 128bit 算法进行加密和解密的工具类
 * 
 * @author atlas
 * @date 2012-9-18
 */
public final class AESCipher {
	private static final int bsize = 16;
	private static final int ivsize = 16;
	private Cipher dCipher;
	private Cipher eCipher;

	private AESCipher(Cipher dCipher, Cipher eCipher) {
		this.eCipher = eCipher;
		this.dCipher = dCipher;
	}

	public static AESCipher getInstance(byte[] key) throws CryptoException {
		String pad = "PKCS5Padding";
		try {
			byte[] tmpKey = ChecksumUtil.getChecksumAsBytes("md5", key);
			byte[] tmpIV = new byte[ivsize];
			if (key.length <= ivsize) {
				System.arraycopy(key, 0, tmpKey, 0, key.length);
			} else if (key.length > ivsize) {
				System.arraycopy(key, 0, tmpIV, 0, tmpIV.length);
			}
			SecretKeySpec keyspec = new SecretKeySpec(tmpKey, "AES");
			IvParameterSpec ivSpec = new IvParameterSpec(tmpIV);
			Cipher dcipher = Cipher.getInstance("AES/CBC/" + pad);
			Cipher ecipher = Cipher.getInstance("AES/CBC/" + pad);
			ecipher.init(Cipher.ENCRYPT_MODE, keyspec, ivSpec);
			dcipher.init(Cipher.DECRYPT_MODE, keyspec, ivSpec);
			return new AESCipher(dcipher, ecipher);
		} catch (Exception e) {
			throw new CryptoException("initializing AESCipher fail.", e);
		}
	}

	/**
	 * 
	 * @param str
	 * @return
	 * @throws CryptoException
	 */
	public String encrypt(String str) throws CryptoException {
		try {
			// Encode the string into bytes using utf-8
			byte[] utf8 = str.getBytes("UTF8");
			// Encrypt
			byte[] enc = this.eCipher.doFinal(utf8);
			// Encode bytes to hex string
			return StringUtils.bytesToHex(enc);
		} catch (Exception e) {
			throw new CryptoException("encrypt failed.", e);
		}
	}

	public String decrypt(String str) throws CryptoException {
		try {
			byte[] dec = StringUtils.hexToBytes(str);
			// Decrypt
			byte[] utf8 = this.dCipher.doFinal(dec);
			// Decode using utf-8
			return new String(utf8, "UTF8");
		} catch (Exception e) {
			throw new CryptoException("decrypt failed.", e);
		}
	}

	public byte[] encrypt(byte[] message) throws CryptoException {
		try {
			// Encrypt
			return this.eCipher.doFinal(message);
		} catch (Exception e) {
			throw new CryptoException("encrypt failed.", e);
		}
	}

	/**
	 * 这个方法是为了尽可能少的减少内存拷贝，input buffer和output
	 * buffer可以是一个byte数组，不过调用者需要保证output可以输出数据的长度要足够大，即能够容纳加密后的padding。
	 * 这个padding不会超过 {@link #getBsize() }-1
	 * 
	 * @param input
	 *            待加密的buffer
	 * @param inputOffset
	 *            待加密的数据在input buffer中的起始位置（从0开始）
	 * @param inputLen
	 *            待加密的数据的长度,不超过(input.length-inputOffset).
	 * @param output
	 *            加密后的数据的输出buffer。
	 * @param outputOffset
	 *            输出buffer的起始位置。
	 * @return 在output里面实际解密的数据长度
	 * 
	 * @throws ShortBufferException
	 *             如果output buffer太小，无法容纳输出结果
	 */
	public int encrypt(byte[] input, int inputOffset, int inputLen,
			byte[] output, int outputOffset) throws CryptoException {
		try {
			return eCipher.doFinal(input, inputOffset, inputLen, output,
					outputOffset);
		} catch (Exception e) {
			throw new CryptoException("encryption failed", e);
		}
	}

	/**
	 * 这个方法是为了尽可能少的减少内存拷贝，input buffer和output buffer可以是同一个byte数组。
	 * 
	 * @param input
	 *            待解密的buffer
	 * @param inputOffset
	 *            待解密的数据在input buffer中的起始位置（从0开始）
	 * @param inputLen
	 *            待解密的数据的长度,不超过(input.length-inputOffset).
	 * @param output
	 *            解密后的数据的输出buffer。
	 * @param outputOffset
	 *            输出buffer的起始位置。
	 * @return 在output里面实际解密的数据长度
	 * @throws ShortBufferException
	 *             如果output buffer太小，无法容纳输出结果
	 */
	public int decrypt(byte[] input, int inputOffset, int inputLen,
			byte[] output, int outputOffset) throws CryptoException {
		try {
			return dCipher.doFinal(input, inputOffset, inputLen, output,
					outputOffset);
		} catch (Exception e) {
			throw new CryptoException("decryption failed", e);
		}
	}

	/**
	 * 返回需要的padding的长度
	 * 
	 * @param len
	 * @return
	 */
	public int pad(int len) {
		return eCipher.getOutputSize(len) - len;
	}

	public byte[] decrypt(byte[] message) throws CryptoException {
		try {
			// Decrypted
			byte[] decrypted = this.dCipher.doFinal(message);
			return decrypted;
		} catch (Exception e) {
			throw new CryptoException("decrypt failed.", e);
		}
	}

	public int getBsize() {
		return bsize;
	}

	@Override
	public String toString() {
		return "128 bit AESCipher with mode CBC and padding PKCS5Padding for both encryption and decryption";
	}
}
