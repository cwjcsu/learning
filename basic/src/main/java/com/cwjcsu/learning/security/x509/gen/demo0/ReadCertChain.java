/*$Id: $
 * author   date   comment
 * cwjcsu@gmail.com  2016年5月26日  Created
*/
package com.cwjcsu.learning.security.x509.gen.demo0;

import com.cwjcsu.learning.security.x509.util.CertUtil;

import java.security.cert.Certificate;
import java.util.List;


public class ReadCertChain {

	public static void main(String[] args) {
		List<Certificate> certs = CertUtil.readX509CertFromFile("resource1/skybility-ha.cer");
		for (Certificate cert : certs) {
			System.out.println(cert);
			System.out.println("=============================");
			
		}
	}

}
