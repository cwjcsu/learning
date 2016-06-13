package com.cwjcsu.learning.security.x509.gen;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.List;

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
public class CertGenerator {
	static {
		 Security.addProvider(new BouncyCastleProvider());
	}
	/**
	 * 使用SHA1withRSA签名算法生成x509 v3 证书，provider is BC
	 * @param issuer
	 * @param subject
	 * @param serial
	 * @param notBefore
	 * @param notAfter
	 * @param publicKey
	 * @param privKey
	 * @param extensions
	 * @return
	 * @throws OperatorCreationException
	 * @throws CertificateException
	 * @throws IOException
	 */
	public static Certificate generateV3(String issuer, String subject,
			BigInteger serial, Date notBefore, Date notAfter,
			PublicKey publicKey, PrivateKey privKey, List<MyExtension> extensions)
			throws OperatorCreationException, CertificateException, IOException {

		X509v3CertificateBuilder builder = new JcaX509v3CertificateBuilder(
				new X500Name(issuer), serial, notBefore, notAfter,
				new X500Name(subject), publicKey);
		ContentSigner sigGen = new JcaContentSignerBuilder("SHA1withRSA")
				.setProvider("BC").build(privKey);
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
//
//	public static void main(String[] args) throws CertificateParsingException,
//			InvalidKeyException, CertificateEncodingException,
//			IllegalStateException, NoSuchProviderException,
//			NoSuchAlgorithmException, SignatureException {
//		Date startDate = new Date(System.currentTimeMillis() - 100000); // time
//																		// from
//																		// which
//																		// certificate
//																		// is
//																		// valid
//		Date expiryDate = new Date(System.currentTimeMillis() + 100000); // time
//																			// after
//																			// which
//																			// certificate
//																			// is
//																			// not
//																			// valid
//		BigInteger serialNumber = BigInteger.ONE; // serial number for
//													// certificate
//		PrivateKey caKey = null; // private key of the certifying authority (ca)
//									// certificate
//		X509Certificate caCert = null; // public key certificate of the
//										// certifying authority
//		KeyPair keyPair = null; // public/private key pair that we are creating
//								// certificate for
//
//		X509V3CertificateGenerator certGen = new X509V3CertificateGenerator();
//		X500Principal subjectName = new X500Principal("CN=Test V3 Certificate");
//
//		certGen.setSerialNumber(serialNumber);
//		certGen.setIssuerDN(caCert.getSubjectX500Principal());
//		certGen.setNotBefore(startDate);
//		certGen.setNotAfter(expiryDate);
//		certGen.setSubjectDN(subjectName);
//		certGen.setPublicKey(keyPair.getPublic());
//		certGen.setSignatureAlgorithm("signatureAlgorithm");
//
//		certGen.addExtension(X509Extensions.AuthorityKeyIdentifier, false,
//				new AuthorityKeyIdentifierStructure(caCert));
//		certGen.addExtension(X509Extensions.SubjectKeyIdentifier, false,
//				new SubjectKeyIdentifierStructure(keyPair.getPublic()));
//
//		X509Certificate cert = certGen.generate(caKey, "BC"); // note: private
//																// key of CA
//	}

//	public static void gen2() {
//		// 设置开始日期和结束日期
//		long year = 360 * 24 * 60 * 60 * 1000;
//		Date notBefore = new Date();
//		Date notAfter = new Date(notBefore.getTime() + year);
//
//		// 设置颁发者和主题
//		String issuerString = "CN=root,OU=单位,O=组织";
//		X500Name issueDn = new X500Name(issuerString);
//		X500Name subjectDn = new X500Name(issuerString);
//
//		// 证书序列号
//		BigInteger serail = BigInteger.probablePrime(32, new Random());
//
//		// 证书中的公钥
//		KeyPair keyPair = null;
//		try {
//			keyPair = KeyPairGenerator.getInstance("RSA", "BC")
//					.generateKeyPair();
//		} catch (NoSuchAlgorithmException e1) {
//			e1.printStackTrace();
//		}
//		PublicKey publicKey = keyPair.getPublic();
//		PrivateKey privateKey = keyPair.getPrivate();
//
//		// 组装公钥信息
//		SubjectPublicKeyInfo subjectPublicKeyInfo = null;
//		try {
//			subjectPublicKeyInfo = SubjectPublicKeyInfo
//					.getInstance(new ASN1InputStream(publicKey.getEncoded())
//							.readObject());
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//
//		// 证书的签名数据
//		final byte[] signatureData;
//		try {
//			Signature signature = Signature.getInstance("SHA1withRSA");
//			signature.initSign(privateKey);
//			signature.update(publicKey.getEncoded());
//			signatureData = signature.sign();
//		} catch (Exception e) {
//			throw new RuntimeException(e.getMessage(), e);
//		}
//
//		// 组装证书
//		X509v3CertificateBuilder builder = new X509v3CertificateBuilder(
//				issueDn, serail, notBefore, notAfter, subjectDn,
//				subjectPublicKeyInfo);
//
//		// 给证书签名
//		X509CertificateHolder holder = builder.build(new ContentSigner() {
//			ByteArrayOutputStream buf = new ByteArrayOutputStream();
//
//			@Override
//			public byte[] getSignature() {
//				try {
//					buf.write(signatureData);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				return signatureData;
//			}
//
//			@Override
//			public OutputStream getOutputStream() {
//				return buf;
//			}
//
//			@Override
//			public AlgorithmIdentifier getAlgorithmIdentifier() {
//				return AlgorithmIdentifier.getInstance(X509Util
//						.getAlgorithmOID("SHA1withRSA"));
//			}
//		});
//		try {
//			byte[] certBuf = holder.getEncoded();
//			X509Certificate certificate = (X509Certificate) CertificateFactory
//					.getInstance("X509").generateCertificate(
//							new ByteArrayInputStream(certBuf));
//			System.out.println(certificate);
//			// 证书base64编码字符串
//			System.out.println(Base64.encode(certificate.getEncoded()));
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (CertificateException e) {
//			e.printStackTrace();
//		}
//	}
}
