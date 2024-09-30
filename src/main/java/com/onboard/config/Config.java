package com.onboard.config;

import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config{
	private static final String HARD_CODED_KEY = "1hx4AR8MyXCxmFauWhNn7Q=="; // Replace with your actual key
    
    @Bean
    public  SecretKey getSecretKey() {
        byte[] decodedKey = Base64.getDecoder().decode(HARD_CODED_KEY);
        // Recreate the secret key from the decoded key
        return new SecretKeySpec(decodedKey, "AES");
    }
}