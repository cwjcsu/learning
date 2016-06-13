package com.cwjcsu.learning.security.skip.secure;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

/**
 * 
 * @author atlas
 * @date 2012-9-6
 */
public class SecureKeyExchanger extends KeyExchanger {
	private static String signAlgorithm = "SHA1withRSA";

	private PrivateKey privateKey;
	private Certificate certificate;
	private Certificate caCertificate;

	private Certificate peerCert;

	public SecureKeyExchanger(DataOutputStream out, DataInputStream in,
			PrivateKey privateKey, Certificate certificate,
			Certificate caCertificate) {
		super(out, in);
		this.privateKey = privateKey;
		this.certificate = certificate;
		this.caCertificate = caCertificate;
	}

	// Send the public key.
	public void sendPublicKey() throws IOException,
			CertificateEncodingException {
		byte[] keyBytes = certificate.getEncoded();
		out.writeInt(keyBytes.length);
		out.write(keyBytes);
	}

	public void receiverPublicKey() throws IOException {
		byte[] keyBytes = new byte[in.readInt()];
		in.readFully(keyBytes);
		try {
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			peerCert = cf
					.generateCertificate(new ByteArrayInputStream(keyBytes));
			peerCert.verify(caCertificate.getPublicKey());
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void receiveDHPublicKey() throws IOException {
		// receiver public key
		receiverPublicKey();

		// receive dh public key
		byte[] publicKeyBytes = read();

		// receive signature of dh public key
		byte[] sign = read();
		KeyFactory kf;
		try {
			// verify signature using peer certificate
			Signature sig = Signature.getInstance(signAlgorithm);
			sig.initVerify(peerCert);
			sig.verify(sign);
			kf = KeyFactory.getInstance("DH");
			X509EncodedKeySpec x509Spec = new X509EncodedKeySpec(publicKeyBytes);
			peerDHPublicKey = kf.generatePublic(x509Spec);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void sendDHPublicKey() throws IOException {
		try {
			// send public key
			sendPublicKey();
			// send dh public key
			byte[] keyBytes = dhKeyPair.getPublic().getEncoded();
			write(keyBytes);
			// sign dh public key using my private key and send the signature
			Signature sig;

			sig = Signature.getInstance(signAlgorithm);
			sig.initSign(privateKey);
			sig.update(keyBytes);
			byte[] sign = sig.sign();
			write(sign);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
