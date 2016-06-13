package com.cwjcsu.learning.security.x509.gen.demo0;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Date;

import com.cwjcsu.learning.security.x509.gen.X509Util;
import com.cwjcsu.learning.security.x509.util.CertUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.OperatorCreationException;


/**
 * 
 * @author atlas
 * @date 2012-9-2
 */
public class GenCA2 {

	static {
		Security.addProvider(new BouncyCastleProvider());
	}
	/**
	 * @param args
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * @throws CertificateException
	 * @throws OperatorCreationException
	 * @throws SignatureException
	 * @throws NoSuchProviderException
	 * @throws InvalidKeyException
	 */
	public static void main(String[] args) {
		gen();
		// write();
	}

	public static void write() {
		try {
			KeyStore store = KeyStore.getInstance("JKS");
			File file = new File("resource1/skybility-ca.jks");
			store.load(new FileInputStream(file), "skybility".toCharArray());
			PrivateKeyEntry ke = (PrivateKeyEntry) store.getEntry("skybility",
					new PasswordProtection("skybility".toCharArray()));
			CertUtil.writeX509CertToFile(ke.getCertificate(), "resource1/atlas-ca.crt");
			CertUtil.writeRSAPrivateKeyToFile(ke.getPrivateKey(), "resource1/atlas-ca.key");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void gen() {
		try {
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(2048);
			KeyPair keyPair = kpg.generateKeyPair();
			KeyStore store = KeyStore.getInstance("JKS");
			store.load(null, null);
			String issuer = "C=CN,ST=GuangDong,L=Shenzhen,O=Skybility,OU=Skybility Certification Authority,CN=Skybility CA,E=support@skybility.com";
			// issuer 与 subject相同的证书就是CA证书
			Certificate cert = X509Util.generateCAX509CertV3(issuer, BigInteger.ZERO,
					new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24),
					new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 365 * 32), keyPair.getPublic(),
					keyPair.getPrivate());
			store.setKeyEntry("skybilityca", keyPair.getPrivate(), "skybility@aoguan".toCharArray(),
					new Certificate[] { cert });
			cert.verify(keyPair.getPublic());
			File file = new File("resource1/skybility-ca.jks");
			if (file.exists() || file.createNewFile())
				store.store(new FileOutputStream(file), "skybility@aoguan".toCharArray());
			
			CertUtil.writeX509CertToFile(cert, "resource1/skybility-ca.crt");
			CertUtil.writeRSAPrivateKeyToFile(keyPair.getPrivate(), "resource1/skybility-ca.key");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
