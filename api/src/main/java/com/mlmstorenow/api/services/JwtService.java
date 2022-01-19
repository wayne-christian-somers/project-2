package com.mlmstorenow.api.services;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

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
	static SecureRandom random = new SecureRandom();
	static byte[] sharedSecret = new byte[32];
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
			e.printStackTrace();

		} catch (JOSEException e) {
			e.printStackTrace();

		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
