package com.mlmstorenow.api.services;

import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Service;

import com.mlmstorenow.api.config.ConfigProperties;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

@Service
public class JwtService {
	static String key = ConfigProperties.getConfigProp("secret_key");
	static String base64Key = DatatypeConverter.printBase64Binary(key.getBytes());
	byte[] sharedSecret = DatatypeConverter.parseBase64Binary(base64Key);
	static JWSSigner signer; // Create HMAC signer

	public SignedJWT tokenGenerator(String data) {

		try {
			JWSSigner signer = new MACSigner(sharedSecret);

			// Prepare JWT with claims set
			JWTClaimsSet claimsSet = new JWTClaimsSet.Builder().subject(data).issuer("Localhost://4200").build();

			SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

			// Apply the HMAC protection
			signedJWT.sign(signer);

			return signedJWT;
		} catch (JOSEException e) {
			return null;
		}

	}

	public boolean authenticate(String payload) {
		// To parse the JWS and verify it, e.g. on client-side
		try {
			SignedJWT signedJWT = SignedJWT.parse(payload);

			JWSVerifier verifier = new MACVerifier(sharedSecret);

			return signedJWT.verify(verifier);

		} catch (KeyLengthException e) {
			

		} catch (JOSEException e) {
			

		} catch (java.text.ParseException e) {
			
		}
		return false;
	}
}
