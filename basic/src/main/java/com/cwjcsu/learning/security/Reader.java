package com.cwjcsu.learning.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.KeyStore.TrustedCertificateEntry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Enumeration;

/**
 * 
 * @author atlas
 * @date 2012-9-7
 */
public class Reader {

	public static KeyInfo read(InputStream file, String alias, String password) {
		try {
			KeyStore store = KeyStore.getInstance("JKS");
			store.load(file, password.toCharArray());
			Enumeration<String> ss = store.aliases();
			while (ss.hasMoreElements()) {
				System.out.println(ss.nextElement());
			}
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

	public static void main(String[] args) throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException {
		KeyInfo info = read(new FileInputStream("resource/atlas-alice.jks"),
				"alice", "alice");
		
		KeyInfo infoBob = read(new FileInputStream("resource/atlas-bob.jks"),
				"bob", "bob");
		File file = new File("resource/atlas-trusted.jks");
		if (file.exists() || file.createNewFile()) {
			OutputStream os = new FileOutputStream(file);
			KeyStore store = KeyStore.getInstance("JKS");
			store.load(null, null);
			store.setCertificateEntry("atlas", info.getCaCertificate());
			store.setCertificateEntry("alice", info.getCertificate());
			store.setCertificateEntry("bob", infoBob.getCertificate());
			store.store(os, "trusted".toCharArray());
		}
	}
}
