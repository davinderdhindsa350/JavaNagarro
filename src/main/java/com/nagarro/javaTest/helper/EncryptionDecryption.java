package com.nagarro.javaTest.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import kotlin.text.Charsets;
 
public class EncryptionDecryption {
 
    private static SecretKeySpec secretKey;
 
    private EncryptionDecryption() {}
    public static void setKey() 
    {
    	var myKey = "1234abcdfh789";
        MessageDigest sha = null;
        try {
        	byte[] key = myKey.getBytes(Charsets.UTF_8);
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16); 
            secretKey = new SecretKeySpec(key, "AES");
        } 
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } 
        
    }
 
    public static String encrypt(String strToEncrypt) 
    {
        try
        {
            setKey();
            var cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(Charsets.UTF_8)));
        } 
        catch (Exception e) 
        {
            e.getStackTrace();
        }
        return null;
    }
 
    public static String decrypt(String strToDecrypt) 
    {
        try
        {
            setKey();
            var cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } 
        catch (Exception e) 
        {
        	 e.getStackTrace();
        }
        return null;
    }
}
