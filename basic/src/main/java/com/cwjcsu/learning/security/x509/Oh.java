package com.cwjcsu.learning.security.x509;

import java.util.Date;

/**
 * 
 * @author atlas
 * @date 2012-9-2
 */
public class Oh {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String ss="MIIDUjCCAjoCAQAwDQYJKoZIhvcNAQEFBQAwbzEVMBMGA1UECxMMR29BZ2VudCBS";
		System.out.println(ss.length());
		System.out.println(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24
							* 365 * 32).toLocaleString());

	}
}
