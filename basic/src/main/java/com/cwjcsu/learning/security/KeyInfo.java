package com.cwjcsu.learning.security;

import java.security.PrivateKey;
import java.security.cert.Certificate;

/**
 * 
 * @author atlas
 * @date 2012-9-7
 */
public class KeyInfo {
	PrivateKey privateKey;
	Certificate certificate;
	Certificate caCertificate;

	public KeyInfo(PrivateKey privateKey, Certificate certificate,
			Certificate caCertificate) {
		super();
		this.privateKey = privateKey;
		this.certificate = certificate;
		this.caCertificate = caCertificate;
	}

	public Certificate getCaCertificate() {
		return caCertificate;
	}

	public Certificate getCertificate() {
		return certificate;
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}
}
