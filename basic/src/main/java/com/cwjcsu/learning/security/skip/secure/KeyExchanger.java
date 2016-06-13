package com.cwjcsu.learning.security.skip.secure;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.KeyAgreement;

import com.cwjcsu.learning.security.skip.Skip;
import org.bouncycastle.jce.provider.BouncyCastleProvider;


/**
 * 
 * @author atlas
 * @date 2012-9-6
 */
public class KeyExchanger {
	protected DataOutputStream out = null;
	protected DataInputStream in = null;
	protected KeyPair dhKeyPair;

	protected PublicKey peerDHPublicKey;

//	static {
//		Security.addProvider(new BouncyCastleProvider());
//	}

	public KeyExchanger(DataOutputStream out, DataInputStream in) {
		super();
		this.out = out;
		this.in = in;
	}

	protected void init() {
		try {
			// Create a Diffie-Hellman key pair.
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("DH");
			kpg.initialize(Skip.DHParameterSpec);
			dhKeyPair = kpg.genKeyPair();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	// Send the public key.
	protected void sendDHPublicKey() throws IOException {
		byte[] keyBytes = dhKeyPair.getPublic().getEncoded();
		write(keyBytes);
	}

	protected byte[] read() throws IOException {
		byte[] bytes = new byte[in.readInt()];
		in.readFully(bytes);
		return bytes;
	}

	protected void write(byte[] bytes) throws IOException {
		out.writeInt(bytes.length);
		out.write(bytes);
	}

	public void exchange() throws IOException {
		this.init();
		this.sendDHPublicKey();
		this.receiveDHPublicKey();
	}

	protected void receiveDHPublicKey() throws IOException {
		byte[] publicKeyBytes = read();
		KeyFactory kf;
		try {
			kf = KeyFactory.getInstance("DH");
			X509EncodedKeySpec x509Spec = new X509EncodedKeySpec(publicKeyBytes);
			peerDHPublicKey = kf.generatePublic(x509Spec);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
	}

	public byte[] generateKey() {
		KeyAgreement ka;
		try {
			ka = KeyAgreement.getInstance("DH");
			ka.init(dhKeyPair.getPrivate());
			ka.doPhase(peerDHPublicKey, true);
			return ka.generateSecret();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
