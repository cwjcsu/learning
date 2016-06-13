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
import java.security.Security;
import java.security.UnrecoverableEntryException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.List;

import com.cwjcsu.learning.security.x509.gen.MyExtension;
import com.cwjcsu.learning.security.x509.gen.X509Util;
import com.cwjcsu.learning.security.x509.util.CertUtil;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.asn1.x509.SubjectKeyIdentifier;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509ExtensionUtils;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;


/**
 * gen certs in jks with different password for key entry and store file.
 * 
 * @author atlas
 * @date 2012-9-2
 */
public class GenAndSignCert2 {
	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	public static void main(String[] args) throws KeyStoreException, NoSuchAlgorithmException, CertificateException,
			FileNotFoundException, IOException, UnrecoverableEntryException {
		genAndSignAndStore();
	}

	public static void main2(String[] args) throws KeyStoreException, NoSuchAlgorithmException, CertificateException,
			FileNotFoundException, IOException, UnrecoverableEntryException {
		KeyStore store = KeyStore.getInstance("JKS");
		File file = new File("resource1/skybility-ca.jks");
		store.load(new FileInputStream(file), "skybility@aoguan".toCharArray());
		PrivateKeyEntry ke = (PrivateKeyEntry) store.getEntry("skybilityca",
				new PasswordProtection("skybility@aoguan".toCharArray()));
		CertUtil.writeX509CertToFile(ke.getCertificate(), "resource1/skybility-ca.cer");
	}

	public static void main0(String[] args) throws KeyStoreException, NoSuchAlgorithmException, CertificateException,
			FileNotFoundException, IOException, UnrecoverableEntryException {
		KeyStore store = KeyStore.getInstance("JKS");
		File file = new File("resource1/skybility-ha.jks");
		store.load(new FileInputStream(file), "HqJkN._V".toCharArray());
		PrivateKeyEntry ke = (PrivateKeyEntry) store.getEntry("ha", new PasswordProtection("HqJkN._V".toCharArray()));
		CertUtil.writeX509CertToFile(ke.getCertificate(), "resource1/skybility-ha.cer");
	}

	/**
	 * 利用ca签名一个新的证书并保存
	 * 
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws UnrecoverableEntryException
	 */
	public static void genAndSignAndStore() throws KeyStoreException, NoSuchAlgorithmException, CertificateException,
			FileNotFoundException, IOException, UnrecoverableEntryException {
		// 读取CA证书的JKS文件
		KeyStore store = KeyStore.getInstance("JKS");
		File file = new File("resource1/skybility-ca.jks");
		store.load(new FileInputStream(file), "skybility@aoguan".toCharArray());

		PrivateKeyEntry ke = (PrivateKeyEntry) store.getEntry("skybilityca",
				new PasswordProtection("skybility@aoguan".toCharArray()));
		String subject = "C=CN,ST=GuangDong,L=Shenzhen,O=Skybility,OU=R&D department,CN=Skybility HA,E=support@skybilityha.com";
		gen(ke, subject, "ha", "HqJkN._V");
	}

	// 用KeyEntry形式存储一个私钥以及对应的证书，并把CA证书加入到它的信任证书列表里面。
	public static void store(PrivateKey key, Certificate cert, Certificate caCert, String name)
			throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		KeyStore store = KeyStore.getInstance("JKS");
		store.load(null, null);
		store.setKeyEntry(name, key, name.toCharArray(), new Certificate[] { cert, caCert });
		File file = new File("resource1/skybility-" + name + ".jks");
		if (file.exists() || file.createNewFile()) {
			store.store(new FileOutputStream(file), (name).toCharArray());
		}
	}

	// 用ke所代表的CA给subject签发证书，并存储到名称为name的jks文件里面。
	public static void gen(PrivateKeyEntry ke, String subject, String name, String password) {
		try {
			X509Certificate caCert = (X509Certificate) ke.getCertificate();
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(2048);
			KeyPair keyPair = kpg.generateKeyPair();
			KeyStore store = KeyStore.getInstance("JKS");
			store.load(null, null);
			Certificate cert = X509Util.generateX509CertV3(subject, BigInteger.ZERO,
					new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24),
					new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 365 * 32), keyPair.getPublic(), // 待签名的公钥
					ke.getPrivateKey(),// CA的私钥
					(X509Certificate)ke.getCertificate()//CAcert
					);
			store.setKeyEntry(name, keyPair.getPrivate(), password.toCharArray(), new Certificate[] { cert, caCert });
			CertUtil.writeX509CertToFile(cert, "resource1/skybility-" + name + ".crt");
			File file = new File("resource1/skybility-" + name + ".jks");
			if (file.exists() || file.createNewFile()) {
				store.store(new FileOutputStream(file), password.toCharArray());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Certificate generateV3(String issuer, String subject, BigInteger serial, Date notBefore,
			Date notAfter, PublicKey publicKey, PrivateKey privKey,Certificate caCert, List<MyExtension> extensions)
					throws OperatorCreationException, CertificateException, IOException, NoSuchAlgorithmException {

		X509v3CertificateBuilder builder = new JcaX509v3CertificateBuilder(new X500Name(issuer), serial, notBefore,
				notAfter, new X500Name(subject), publicKey);
		ContentSigner sigGen = new JcaContentSignerBuilder("SHA1withRSA").setProvider("BC").build(privKey);
		// privKey是CA的私钥，publicKey是待签名的公钥，那么生成的证书就是被CA签名的证书。
		if (extensions != null) {
			for (MyExtension ext : extensions) {
				builder.addExtension(new ASN1ObjectIdentifier(ext.getOid()), ext.isCritical(),
						ASN1Primitive.fromByteArray(ext.getValue()));
			}
		}
		builder.addExtension(org.bouncycastle.asn1.x509.Extension.basicConstraints, true, new BasicConstraints(false));

		builder.addExtension(org.bouncycastle.asn1.x509.Extension.keyUsage, true, new KeyUsage(KeyUsage.digitalSignature
				| KeyUsage.nonRepudiation | KeyUsage.keyEncipherment | KeyUsage.keyAgreement | KeyUsage.keyCertSign));

		SubjectKeyIdentifier subjectKeyIdentifier = new JcaX509ExtensionUtils().createSubjectKeyIdentifier(publicKey);
		builder.addExtension(org.bouncycastle.asn1.x509.Extension.subjectKeyIdentifier, false, subjectKeyIdentifier);
		builder.addExtension(org.bouncycastle.asn1.x509.Extension.authorityKeyIdentifier, false,
				new JcaX509ExtensionUtils().createAuthorityKeyIdentifier((X509Certificate)caCert));
		
		X509CertificateHolder holder = builder.build(sigGen);
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		InputStream is1 = new ByteArrayInputStream(holder.toASN1Structure().getEncoded());
		X509Certificate theCert = (X509Certificate) cf.generateCertificate(is1);
		is1.close();
		return theCert;
	}
}
