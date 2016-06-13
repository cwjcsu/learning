package com.cwjcsu.learning.security.x509.gen.demo0;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableEntryException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.List;

import com.cwjcsu.learning.security.x509.gen.MyExtension;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;


/**
 * gen certs in jks with different password for key entry and store file.
 * @author atlas
 * @date 2012-9-2
 */
public class GenAndSignCert {

	public static void main(String[] args) throws KeyStoreException,
			NoSuchAlgorithmException, CertificateException,
			FileNotFoundException, IOException, UnrecoverableEntryException {
		//读取CA证书的JKS文件
		KeyStore store = KeyStore.getInstance("JKS");
		File file = new File("resource/atlas-ca.jks");
		store.load(new FileInputStream(file), "atlas".toCharArray());
		
		PrivateKeyEntry ke = (PrivateKeyEntry) store.getEntry("atlas",
				new PasswordProtection("atlas".toCharArray()));
		String subject = "C=CN,ST=GuangDong,L=Shenzhen,O=Skybility,OU=Cloudbility,CN=Alice,E=alice@163.com";
		//给alice签发证书并存为xxx-alice.jks的文件
		gen(ke, subject, "alice");
		subject = "C=CN,ST=GuangDong,L=Shenzhen,O=Skybility,OU=Cloudbility,CN=Bob,E=Bob@gmail.com";
		//给Bob签发证书并存为xxx-bob.jks的文件
		gen(ke, subject, "bob");
	}

	//用KeyEntry形式存储一个私钥以及对应的证书，并把CA证书加入到它的信任证书列表里面。
	public static void store(PrivateKey key, Certificate cert,
			Certificate caCert, String name) throws KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException {
		KeyStore store = KeyStore.getInstance("JKS");
		store.load(null, null);
		store.setKeyEntry(name, key, name.toCharArray(), new Certificate[] {
				cert, caCert });
		File file = new File("resource/atlas-" + name + ".jks");
		if (file.exists() || file.createNewFile()) {
			store.store(new FileOutputStream(file), ("_"+name).toCharArray());
		}
	}

	//用ke所代表的CA给subject签发证书，并存储到名称为name的jks文件里面。
	public static void gen(PrivateKeyEntry ke, String subject, String name) {
		try {
			X509Certificate caCert = (X509Certificate) ke.getCertificate();
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(2048);
			KeyPair keyPair = kpg.generateKeyPair();

			KeyStore store = KeyStore.getInstance("JKS");
			store.load(null, null);
			String issuer = caCert.getIssuerDN().toString();
			Certificate cert = generateV3(issuer, subject,
					BigInteger.ZERO, new Date(System.currentTimeMillis() - 1000
							* 60 * 60 * 24),
					new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24
							* 365 * 32), keyPair.getPublic(),//待签名的公钥
					ke.getPrivateKey()//CA的私钥
					, null);
			store(keyPair.getPrivate(), cert, ke.getCertificate(), name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Certificate generateV3(String issuer, String subject,
			BigInteger serial, Date notBefore, Date notAfter,
			PublicKey publicKey, PrivateKey privKey, List<MyExtension> extensions)
			throws OperatorCreationException, CertificateException, IOException {

		X509v3CertificateBuilder builder = new JcaX509v3CertificateBuilder(
				new X500Name(issuer), serial, notBefore, notAfter,
				new X500Name(subject), publicKey);
		ContentSigner sigGen = new JcaContentSignerBuilder("SHA1withRSA")
				.setProvider("BC").build(privKey);
		//privKey是CA的私钥，publicKey是待签名的公钥，那么生成的证书就是被CA签名的证书。
		if (extensions != null)
			for (MyExtension ext : extensions) {
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
}
