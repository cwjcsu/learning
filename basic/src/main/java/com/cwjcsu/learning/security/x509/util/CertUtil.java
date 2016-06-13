package com.cwjcsu.learning.security.x509.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.cwjcsu.common.Base64;
import com.cwjcsu.common.Resources;
import com.cwjcsu.common.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;


/**
 * 
 * @author atlas
 * @date 2012-9-1
 */
public class CertUtil {

	public static final String CERT_FILE_FIRST_LINE = "-----BEGIN CERTIFICATE-----";
	public static final String CERT_FILE_LAST_LINE = "-----END CERTIFICATE-----";

	public static final String RSA_PRIVATE_KEY_FILE_FIRST_LINE = "-----BEGIN RSA PRIVATE KEY-----";
	public static final String RSA_PRIVATE_KEY_FILE_LAST_LINE = "-----END RSA PRIVATE KEY-----";

	private static String readBetween(String file, String firstLine,
			String lastLine) throws IOException {
		BufferedReader reader = null;
		try {
			Reader r = new InputStreamReader(new FileInputStream(file));
			reader = new BufferedReader(r);
			String line = reader.readLine();
			if (!firstLine.equals(line)) {
				throw new RuntimeException("Invalid file,first must be '"
						+ firstLine + "'");
			}
			StringBuffer sb = new StringBuffer();
			boolean gotLast = false;
			while ((line = reader.readLine()) != null) {
				if (lastLine.equals(line)) {
					gotLast = true;
					break;
				}
				sb.append(line);
			}
			if (gotLast == false) {
				throw new RuntimeException("Invalid file,last line must be '"
						+ firstLine + "'");
			}
			return sb.toString();
		} finally {
			Resources.close(reader);
		}
	}

	private static void writeTo(String file, String firstLine, String content,
			String lastLine) throws IOException {
		BufferedWriter writer = null;
		try {
			Writer r = new OutputStreamWriter(new FileOutputStream(file));
			writer = new BufferedWriter(r);
			int index = 0;
			char[] data = content.toCharArray();
			writer.write(firstLine);
			writer.write(System.getProperty("line.separator"));
			while (index < data.length) {
				writer.write(data, index, Math.min(64, data.length - index));
				index += 64;
				writer.write(System.getProperty("line.separator"));
			}
			writer.write(lastLine);
		} finally {
			Resources.close(writer);
		}
	}

	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	public static List<Certificate> readX509CertFromFile(String file) {
		try {
			String certString = readBetween(file, CERT_FILE_FIRST_LINE,
					CERT_FILE_LAST_LINE);
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			Collection<? extends Certificate> certs = cf
					.generateCertificates(new ByteArrayInputStream(Base64
							.decode(certString)));
			return new ArrayList<Certificate>(certs);
		} catch (Exception e) {
			if (e instanceof RuntimeException) {
				throw (RuntimeException) e;
			}
			throw new RuntimeException("error reading " + file, e);
		}
	}

	public static PrivateKey readRSAPrivateKeyFromFile(String file) {
		try {
			String certString = readBetween(file,
					RSA_PRIVATE_KEY_FILE_FIRST_LINE,
					RSA_PRIVATE_KEY_FILE_LAST_LINE);
			KeyFactory kf = KeyFactory.getInstance("RSA");
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(
					Base64.decode(certString));
			PrivateKey priKey = kf.generatePrivate(pkcs8KeySpec);
			return priKey;
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof RuntimeException) {
				throw (RuntimeException) e;
			}
			throw new RuntimeException("error reading " + file, e);
		}
	}

	public static void writeX509CertToFile(Certificate cert, String file) {
		try {
			String certString = Base64.encode(cert.getEncoded());
			writeTo(file, CERT_FILE_FIRST_LINE, certString, CERT_FILE_LAST_LINE);
		} catch (Exception e) {
			if (e instanceof RuntimeException) {
				throw (RuntimeException) e;
			}
			throw new RuntimeException("error writing " + file, e);
		}
	}

	public static void writeRSAPrivateKeyToFile(PrivateKey key, String file) {
		try {
			String certString = Base64.encode(key.getEncoded());
			writeTo(file, RSA_PRIVATE_KEY_FILE_FIRST_LINE, certString,
					RSA_PRIVATE_KEY_FILE_LAST_LINE);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof RuntimeException) {
				throw (RuntimeException) e;
			}
			throw new RuntimeException("error reading " + file, e);
		}
	}

	public static void main(String[] args) throws CertificateParsingException {
		String file = "resource/CA.crt";
		List<Certificate> certs = CertUtil.readX509CertFromFile(file);
		X509Certificate c = (X509Certificate) certs.get(0);
		List<String> exts = c.getExtendedKeyUsage();
		if (exts != null) {
			System.out.println("Extensions:");
			for (String oid : exts) {
				System.out.println(oid + ":"
						+ StringUtils.bytesToHex(c.getExtensionValue(oid)));
			}
		}
		System.out.println(c.getBasicConstraints());
		System.out.println(c.getSubjectDN());
		System.out.println(c.getIssuerDN());

		Set critSet = c.getCriticalExtensionOIDs();
		if (critSet != null && !critSet.isEmpty()) {
			System.out.println("Set of critical extensions:");
			for (Iterator i = critSet.iterator(); i.hasNext();) {
				String oid = (String) i.next();
				System.out.println(oid);
			}
		}

	}
}
