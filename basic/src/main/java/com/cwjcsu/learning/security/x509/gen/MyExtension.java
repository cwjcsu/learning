package com.cwjcsu.learning.security.x509.gen;

/**
 * 
 * @author atlas
 * @date 2012-9-2
 */
public class MyExtension {
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
