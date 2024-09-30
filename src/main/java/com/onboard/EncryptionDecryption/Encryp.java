package com.onboard.EncryptionDecryption;

import java.util.Base64;

import javax.crypto.Cipher;
//import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Encryp {
	private static final String ALGORITHM = "AES/ECB/PKCS5Padding"; // Use a mode with padding

	public static String encrypt(String data, SecretKey key) throws Exception {
	    Cipher cipher = Cipher.getInstance(ALGORITHM);
	    cipher.init(Cipher.ENCRYPT_MODE, key);
	    byte[] encryptedBytes = cipher.doFinal(data.getBytes());
	    return Base64.getEncoder().encodeToString(encryptedBytes);
	}

	public static String decrypt(String encryptedData, SecretKey key) throws Exception {
	    Cipher cipher = Cipher.getInstance(ALGORITHM);
	    cipher.init(Cipher.DECRYPT_MODE, key);
	    byte[] decryptedBytes = Base64.getDecoder().decode(encryptedData);
	    return new String(cipher.doFinal(decryptedBytes));
	}

}
	
