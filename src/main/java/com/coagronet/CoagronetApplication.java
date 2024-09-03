package com.coagronet;

import javax.crypto.SecretKey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;

@SpringBootApplication
@EnableScheduling
public class CoagronetApplication {
	public static void main(String[] args) {
		SecretKey key = Jwts.SIG.HS256.key().build(); // or HS384 or HS512
		String base64Key = Encoders.BASE64.encode(key.getEncoded());
		System.out.println("Base64 Encoded Key: " + base64Key);
		SpringApplication.run(CoagronetApplication.class, args);
	}
}
