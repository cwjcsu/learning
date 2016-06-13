package com.cwjcsu.learning.security.x509.gen;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.PrivateKeyEntry;

/**
 * 
 * @author atlas
 * @date 2012-9-7
 */
public class Reader {

	public static KeyInfo read(String file, String alias, String password) {
		try {
			KeyStore store = KeyStore.getInstance("JKS");
			store.load(new FileInputStream(file), password.toCharArray());
			PrivateKeyEntry ke = (PrivateKeyEntry) store.getEntry(alias,
					new PasswordProtection(password.toCharArray()));
			KeyInfo info = new KeyInfo(ke.getPrivateKey(), ke.getCertificate(),
					ke.getCertificateChain()[1]);
			return info;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
