/*$Id: $
 * author   date   comment
 * cwjcsu@gmail.com  2016年5月26日  Created
*/
package com.cwjcsu.learning.security.x509.gen;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.misc.MiscObjectIdentifiers;
import org.bouncycastle.asn1.misc.NetscapeCertType;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509ExtensionUtils;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

public class X509Util {
	public static class X509Extension {
		private ASN1ObjectIdentifier oid;
		private boolean critical;
		private ASN1Encodable value;

		public X509Extension(ASN1ObjectIdentifier oid, boolean critical, ASN1Encodable value) {
			super();
			this.oid = oid;
			this.critical = critical;
			this.value = value;
		}

		public ASN1ObjectIdentifier getOid() {
			return oid;
		}

		public boolean isCritical() {
			return critical;
		}

		public ASN1Encodable getValue() {
			return value;
		}
	}

	/**
	 * 
	 * @param subject
	 * @param serial
	 * @param notBefore
	 * @param notAfter
	 * @param publicKey
	 *            是待签名的公钥
	 * @param privKey
	 *            CA的私钥
	 * @param caCert
	 *            CA的证书
	 * @return
	 * @throws OperatorCreationException
	 * @throws CertificateException
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * @throws SignatureException
	 * @throws NoSuchProviderException
	 * @throws InvalidKeyException
	 */
	public static Certificate generateX509CertV3(String subject, BigInteger serial, Date notBefore, Date notAfter,
			PublicKey publicKey, PrivateKey privKey, X509Certificate caCert)
					throws OperatorCreationException, CertificateException, NoSuchAlgorithmException, IOException,
					InvalidKeyException, NoSuchProviderException, SignatureException {
		return generateX509CertV3(subject, serial, notBefore, notAfter, publicKey, privKey, caCert, null);
	}

	/**
	 * 
	 * @param subject
	 * @param serial
	 * @param notBefore
	 * @param notAfter
	 * @param publicKey
	 *            是待签名的公钥
	 * @param privKey
	 *            CA的私钥
	 * @param caCert
	 *            CA的证书
	 * @param extensions
	 * @return
	 * @throws OperatorCreationException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws SignatureException
	 * @throws NoSuchProviderException
	 * @throws InvalidKeyException
	 */
	public static Certificate generateX509CertV3(String subject, BigInteger serial, Date notBefore, Date notAfter,
			PublicKey publicKey, PrivateKey privKey, X509Certificate caCert,
			Map<ASN1ObjectIdentifier, X509Extension> extensions)
					throws OperatorCreationException, CertificateException, IOException, NoSuchAlgorithmException,
					InvalidKeyException, NoSuchProviderException, SignatureException {

		X509v3CertificateBuilder builder = new JcaX509v3CertificateBuilder(
				new X500Name(caCert.getSubjectX500Principal().getName()), serial, notBefore, notAfter,
				new X500Name(subject), publicKey);

		ContentSigner sigGen = new JcaContentSignerBuilder("SHA256withRSA").setProvider("BC").build(privKey);
		// privKey是CA的私钥，publicKey是待签名的公钥，那么生成的证书就是被CA签名的证书。
		if (extensions == null) {
			extensions = new HashMap<ASN1ObjectIdentifier, X509Extension>();
		}
		if (!extensions.containsKey(Extension.basicConstraints)) {
			extensions.put(Extension.basicConstraints,
					new X509Extension(Extension.basicConstraints, true, new BasicConstraints(false)));
		}
		if (!extensions.containsKey(Extension.keyUsage)) {
			extensions
					.put(Extension.keyUsage,
							new X509Extension(Extension.keyUsage, true,
									new KeyUsage(KeyUsage.digitalSignature | KeyUsage.nonRepudiation
											| KeyUsage.keyEncipherment | KeyUsage.keyAgreement
											| KeyUsage.keyAgreement)));
		}
		extensions.put(Extension.subjectKeyIdentifier, new X509Extension(Extension.subjectKeyIdentifier, false,
				new JcaX509ExtensionUtils().createSubjectKeyIdentifier(publicKey)));
		extensions.put(Extension.authorityKeyIdentifier, new X509Extension(Extension.authorityKeyIdentifier, false,
				new JcaX509ExtensionUtils().createAuthorityKeyIdentifier(caCert)));

		extensions.put(MiscObjectIdentifiers.netscapeCertType,new X509Extension(MiscObjectIdentifiers.netscapeCertType,
	            false, new NetscapeCertType(NetscapeCertType.sslClient
	            | NetscapeCertType.smime|NetscapeCertType.sslServer)));
		
		for (X509Extension ext : extensions.values()) {
			builder.addExtension(ext.getOid(), ext.isCritical(), ext.getValue());
		}

		X509CertificateHolder holder = builder.build(sigGen);
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		InputStream is1 = new ByteArrayInputStream(holder.toASN1Structure().getEncoded());
		X509Certificate theCert = (X509Certificate) cf.generateCertificate(is1);
		theCert.verify(caCert.getPublicKey());
		is1.close();
		return theCert;
	}

	/**
	 * 
	 * @param issuer
	 *            issuer和subject
	 * @param serial
	 * @param notBefore
	 * @param notAfter
	 * @param publicKey
	 *            CA的公钥
	 * @param privKey
	 *            CA的私钥
	 * @return
	 * @throws OperatorCreationException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws SignatureException
	 * @throws NoSuchProviderException
	 * @throws InvalidKeyException
	 */
	public static X509Certificate generateCAX509CertV3(String issuer, BigInteger serial, Date notBefore, Date notAfter,
			PublicKey publicKey, PrivateKey privKey)
					throws OperatorCreationException, CertificateException, IOException, NoSuchAlgorithmException,
					InvalidKeyException, NoSuchProviderException, SignatureException {
		return generateCAX509CertV3(issuer, serial, notBefore, notAfter, publicKey, privKey, null);
	}

	/**
	 * 
	 * @param issuer
	 *            issuer和subject
	 * @param serial
	 * @param notBefore
	 * @param notAfter
	 * @param publicKey
	 *            CA的公钥
	 * @param privKey
	 *            CA的私钥
	 * @param extensions
	 * @return
	 * @throws OperatorCreationException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws SignatureException
	 * @throws NoSuchProviderException
	 * @throws InvalidKeyException
	 */
	public static X509Certificate generateCAX509CertV3(String issuer, BigInteger serial, Date notBefore, Date notAfter,
			PublicKey publicKey, PrivateKey privKey, Map<ASN1ObjectIdentifier, X509Extension> extensions)
					throws OperatorCreationException, CertificateException, IOException, NoSuchAlgorithmException,
					InvalidKeyException, NoSuchProviderException, SignatureException {

		X509v3CertificateBuilder builder = new JcaX509v3CertificateBuilder(new X500Name(issuer), serial, notBefore,
				notAfter, new X500Name(issuer), publicKey);
		ContentSigner sigGen = new JcaContentSignerBuilder("SHA256withRSA").setProvider("BC").build(privKey);
		// privKey:使用自己的私钥进行签名，CA证书
		if (extensions == null) {
			extensions = new HashMap<ASN1ObjectIdentifier, X509Extension>();
		}
		if (!extensions.containsKey(Extension.basicConstraints)) {
			extensions.put(Extension.basicConstraints,
					new X509Extension(Extension.basicConstraints, true, new BasicConstraints(true)));// new
																										// BasicConstraints(true)
																										// means
																										// ca
																										// =
																										// true
		}
		if (!extensions.containsKey(Extension.keyUsage)) {
			extensions.put(Extension.keyUsage,
					new X509Extension(Extension.keyUsage, true,
							new KeyUsage(KeyUsage.digitalSignature | KeyUsage.nonRepudiation | KeyUsage.keyEncipherment
									| KeyUsage.keyAgreement | KeyUsage.keyAgreement | KeyUsage.cRLSign
									| KeyUsage.keyCertSign)));
		}
		extensions.put(Extension.subjectKeyIdentifier, new X509Extension(Extension.subjectKeyIdentifier, false,
				new JcaX509ExtensionUtils().createSubjectKeyIdentifier(publicKey)));
		extensions.put(Extension.authorityKeyIdentifier, new X509Extension(Extension.authorityKeyIdentifier, false,
				new JcaX509ExtensionUtils().createAuthorityKeyIdentifier(publicKey)));

		extensions.put(MiscObjectIdentifiers.netscapeCertType,new X509Extension(MiscObjectIdentifiers.netscapeCertType,
	            false, new NetscapeCertType(NetscapeCertType.sslClient
	            | NetscapeCertType.smime|NetscapeCertType.sslServer|NetscapeCertType.sslCA|NetscapeCertType.objectSigningCA|NetscapeCertType.smimeCA)));
		
//		extensions.put(Extension.subjectAlternativeName, new X509Extension(Extension.subjectAlternativeName, false,
//				new GeneralNames(new GeneralName(GeneralName.rfc822Name, "test@test.test"))));
		
		for (X509Extension ext : extensions.values()) {
			builder.addExtension(ext.getOid(), ext.isCritical(), ext.getValue());
		}

		X509CertificateHolder holder = builder.build(sigGen);
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		InputStream is1 = new ByteArrayInputStream(holder.toASN1Structure().getEncoded());
		X509Certificate theCert = (X509Certificate) cf.generateCertificate(is1);
		theCert.verify(publicKey);
		is1.close();
		return theCert;
	}
}
