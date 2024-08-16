package com.coagronet;

import javax.crypto.SecretKey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

@SpringBootApplication
public class CoagronetApplication {

	public static void main(String[] args) {
		SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // or HS384 or HS512
		String base64Key = Encoders.BASE64.encode(key.getEncoded());
		System.out.println("Base64 Encoded Key: " + base64Key);
		SpringApplication.run(CoagronetApplication.class, args);
	}

}
