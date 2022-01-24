package com.mlmstorenow.api.services;

import org.springframework.stereotype.Service; 
import java.io.ObjectStreamException;
import java.security.KeyRep;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.codec.binary.Base64;

@Service
public class WalmartService {

	public String signitureGenerator(String content) {

		String consumerId = "e395dc02-953b-40df-ab0f-97c4bcc3f664";
		String priviateKeyVersion = "2";
		String key = content; 

		long intimestamp = System.currentTimeMillis();

		Map<String, String> map = new HashMap<>();
		map.put("WM_CONSUMER.ID", consumerId);
		map.put("WM_CONSUMER.INTIMESTAMP", Long.toString(intimestamp));
		map.put("WM_SEC.KEY_VERSION", priviateKeyVersion);

		String[] array = canonicalize(map);

		String data = null;

		try {
			return generateSignature(key, array[1]);
		} catch (Exception e) {
		}
		return "Signature: " + data;
	}

	public String generateSignature(String key, String stringToSign) throws Exception {
		Signature signatureInstance = Signature.getInstance("SHA256WithRSA");

		ServiceKeyRep keyRep = new ServiceKeyRep(KeyRep.Type.PRIVATE, "RSA", "PKCS#8", Base64.decodeBase64(key));

		PrivateKey resolvedPrivateKey = (PrivateKey) keyRep.readResolve();

		signatureInstance.initSign(resolvedPrivateKey);

		byte[] bytesToSign = stringToSign.getBytes("UTF-8");
		signatureInstance.update(bytesToSign);
		byte[] signatureBytes = signatureInstance.sign();

		String signatureString = Base64.encodeBase64String(signatureBytes);

		return signatureString;
	}

	protected static String[] canonicalize(Map<String, String> headersToSign) {
		StringBuffer canonicalizedStrBuffer = new StringBuffer();
		StringBuffer parameterNamesBuffer = new StringBuffer();
		Set<String> keySet = headersToSign.keySet();

		// Create sorted key set to enforce order on the key names
		SortedSet<String> sortedKeySet = new TreeSet<String>(keySet);
		for (String key : sortedKeySet) {
			Object val = headersToSign.get(key);
			parameterNamesBuffer.append(key.trim()).append(";");
			canonicalizedStrBuffer.append(val.toString().trim()).append("\n");
		}
		return new String[] { parameterNamesBuffer.toString(), canonicalizedStrBuffer.toString() };
	}

	class ServiceKeyRep extends KeyRep {
		private static final long serialVersionUID = -7213340660431987616L;

		public ServiceKeyRep(Type type, String algorithm, String format, byte[] encoded) {
			super(type, algorithm, format, encoded);
		}

		protected Object readResolve() throws ObjectStreamException {
			return super.readResolve();
		}
	}
}
