package com.mlmstorenow.api.services;

import java.util.ArrayList;

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

import lombok.Getter;
import lombok.Setter;

@Service
public class JwtService {
	static String key = ConfigProperties.getConfigProp("secret_key");
	static String base64Key = DatatypeConverter.printBase64Binary(key.getBytes());
	byte[] sharedSecret = DatatypeConverter.parseBase64Binary(base64Key);
	static JWSSigner signer; // Create HMAC signer
	@Setter
	@Getter
	private static ArrayList<String> uname = new ArrayList<>();

	public SignedJWT tokenGenerator(String data) {

		SignedJWT signedJWT = null;

		JWSSigner signer = null;
		try {
			signer = new MACSigner(sharedSecret);
		} catch (KeyLengthException e) {

			e.getMessage();
		}

		// Prepare JWT with claims set
		JWTClaimsSet claimsSet = new JWTClaimsSet.Builder().subject(data).issuer("Localhost://4200").build();

		signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

		// Apply the HMAC protection
		try {
			signedJWT.sign(signer);
		} catch (JOSEException e) {

			e.getMessage();
		}

		uname.add(data);

		return signedJWT;

	}

	public boolean authenticate(String payload) {
		// To parse the JWS and verify it, e.g. on client-side
		try {
			SignedJWT signedJWT = SignedJWT.parse(payload);

			JWSVerifier verifier = new MACVerifier(sharedSecret);

			return signedJWT.verify(verifier);

		} catch (KeyLengthException e) {
			e.getMessage();
		} catch (JOSEException e) {
			e.getMessage();
		} catch (java.text.ParseException e) {
			e.getMessage();
			
		}
		return false;
	}
}
