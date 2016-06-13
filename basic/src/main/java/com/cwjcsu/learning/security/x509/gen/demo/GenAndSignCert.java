package com.cwjcsu.learning.security.x509.gen.demo;

import com.cwjcsu.learning.security.x509.gen.CertGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableEntryException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;

/**
 * 
 * @author atlas
 * @date 2012-9-2
 */
public class GenAndSignCert {

	public static void main(String[] args) throws KeyStoreException,
			NoSuchAlgorithmException, CertificateException,
			FileNotFoundException, IOException, UnrecoverableEntryException {
		KeyStore store = KeyStore.getInstance("JKS");
		File file = new File("resource/atlas-ca.jks");
		store.load(new FileInputStream(file), "atlas".toCharArray());
		PrivateKeyEntry ke = (PrivateKeyEntry) store.getEntry("atlas",
				new PasswordProtection("atlas".toCharArray()));
		String subject = "C=CN,ST=GuangDong,L=Shenzhen,O=Skybility,OU=Cloudbility,CN=Alice,E=alice@163.com";
		gen(ke, subject, "alice");
		
		subject = "C=CN,ST=GuangDong,L=Shenzhen,O=Skybility,OU=Cloudbility,CN=Bob,E=Bob@gmail.com";
		gen(ke, subject, "bob");
	}

	public static void store(PrivateKey key, Certificate cert,
			Certificate caCert, String name) throws KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException {
		KeyStore store = KeyStore.getInstance("JKS");
		store.load(null, null);
		store.setKeyEntry(name, key, name.toCharArray(), new Certificate[] {
				cert, caCert });
		File file = new File("resource/atlas-" + name + ".jks");
		if (file.exists() || file.createNewFile()) {
			store.store(new FileOutputStream(file), name.toCharArray());
		}
	}

	public static void gen(PrivateKeyEntry ke, String subject, String name) {
		try {
			X509Certificate caCert = (X509Certificate) ke.getCertificate();
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(2048);
			KeyPair keyPair = kpg.generateKeyPair();

			KeyStore store = KeyStore.getInstance("JKS");
			store.load(null, null);
			String issuer = caCert.getIssuerDN().toString();
			Certificate cert = CertGenerator.generateV3(issuer, subject,
					BigInteger.ZERO, new Date(System.currentTimeMillis() - 1000
							* 60 * 60 * 24),
					new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24
							* 365 * 32), keyPair.getPublic(),
					ke.getPrivateKey(), null);
			store(keyPair.getPrivate(), cert, ke.getCertificate(), name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
