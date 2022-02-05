package com.yorme.fdma.core.service;

import com.yorme.fdma.utilities.PropertiesReader;

import junit.framework.TestCase;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class DecryptorTest extends TestCase {
    private PropertiesReader propertiesReader;

    @Test
    public void testDecrpytStringUsingAES(){
        String input = "AString12"; //9

        propertiesReader = new PropertiesReader();
        try {
            SecretKey secretKey = Encryptor.generateKey(128);
            IvParameterSpec ivParameterSpec = Encryptor.generateIv();
            String algorithm = propertiesReader.getApplicationProperty().getProperty("encrypt.algorithm");

            String cipherText = Encryptor.encrypt(algorithm, input, secretKey, ivParameterSpec);
            String plainText = Decryptor.decrypt(algorithm,cipherText,secretKey,ivParameterSpec);

            assertEquals(input,plainText);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException |
                InvalidAlgorithmParameterException | InvalidKeyException |
                BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

}
