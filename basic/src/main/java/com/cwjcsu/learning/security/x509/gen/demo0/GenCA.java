package com.cwjcsu.learning.security.x509.gen.demo0;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.List;

import com.cwjcsu.learning.security.x509.util.CertUtil;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;


/**
 * 
 * @author atlas
 * @date 2012-9-2
 */
public class GenCA {

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
		//gen();
		write();
	}

	public static void write() {
		try {
			KeyStore store = KeyStore.getInstance("JKS");
			File file = new File("resource0/atlas-ca.jks");
			store.load(new FileInputStream(file), "atlas".toCharArray());
			PrivateKeyEntry ke = (PrivateKeyEntry) store.getEntry("atlas",
					new PasswordProtection("atlas".toCharArray()));
			CertUtil.writeX509CertToFile(ke.getCertificate(),
					"resource0/atlas-ca.crt");
			CertUtil.writeRSAPrivateKeyToFile(ke.getPrivateKey(),
					"resource0/atlas-ca.key");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static {
		 Security.addProvider(new BouncyCastleProvider());
	}
	public static void main0(String[] args) {
		try {
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(2048);
			KeyPair keyPair = kpg.generateKeyPair();
			KeyStore store = KeyStore.getInstance("JKS");
			store.load(null, null);
			String issuer = "C=CN,ST=GuangDong,L=Shenzhen,O=Skybility,OU=Cloudbility,CN=Atlas Personal License CA,E=cwjcsu@126.com";
			String subject = issuer;
			//issuer 与 subject相同的证书就是CA证书
			Certificate cert = generateV3(issuer, subject,
					BigInteger.ZERO, new Date(System.currentTimeMillis() - 1000
							* 60 * 60 * 24),
					new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24
							* 365 * 32), keyPair.getPublic(),
					keyPair.getPrivate(), null);
			store.setKeyEntry("atlas", keyPair.getPrivate(),
					"atlas".toCharArray(), new Certificate[] { cert });
			cert.verify(keyPair.getPublic());
			File file = new File("resource0/atlas-ca.jks");
			if (file.exists() || file.createNewFile())
				store.store(new FileOutputStream(file), "atlas".toCharArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Certificate generateV3(String issuer, String subject,
			BigInteger serial, Date notBefore, Date notAfter,
			PublicKey publicKey, PrivateKey privKey, List<Extension> extensions)
			throws OperatorCreationException, CertificateException, IOException {

		X509v3CertificateBuilder builder = new JcaX509v3CertificateBuilder(
				new X500Name(issuer), serial, notBefore, notAfter,
				new X500Name(subject), publicKey);
		ContentSigner sigGen = new JcaContentSignerBuilder("SHA1withRSA")
				.setProvider("BC").build(privKey);
		//privKey:使用自己的私钥进行签名，CA证书
		if (extensions != null)
			for (Extension ext : extensions) {
				builder.addExtension(new ASN1ObjectIdentifier(ext.getOid()),
						ext.isCritical(),
						ASN1Primitive.fromByteArray(ext.getValue()));
			}
		X509CertificateHolder holder = builder.build(sigGen);
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		InputStream is1 = new ByteArrayInputStream(holder.toASN1Structure()
				.getEncoded());
		X509Certificate theCert = (X509Certificate) cf.generateCertificate(is1);
		is1.close();
		return theCert;
	}
	public class Extension {
		private String oid;
		private boolean critical;
		private byte[] value;

		public String getOid() {
			return oid;
		}

		public byte[] getValue() {
			return value;
		}

		public boolean isCritical() {
			return critical;
		}
	}

}
