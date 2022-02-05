package com.yorme.fdma.core.dao;

import com.yorme.fdma.core.service.Decryptor;
import com.yorme.fdma.core.service.Encryptor;
import com.yorme.fdma.utilities.PropertiesReader;
import com.yorme.fdma.utilities.database.DBConnection;
import com.yorme.fdma.utilities.database.DBSQL;

import junit.framework.TestCase;

import org.junit.Test;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class PasswordDaoTest extends TestCase {
    private PasswordDao passwordDao;
    private DBConnection conn;
    private PropertiesReader propertiesReader;

    @Test
    public void testCreateInsertAndReadPassword(){
        try {
            //Flush table
            conn = new DBConnection();
            conn.flushTable(DBSQL.FLUSH_PASSWORD_TABLE);

            passwordDao = new PasswordDao();
            propertiesReader = new PropertiesReader();
            String testPassword = "Fdma-app2022";
            String cipherText = "";
            SecretKey secretKey = Encryptor.generateKey(128);
            IvParameterSpec ivParameterSpec = Encryptor.generateIv();
            String algorithm = propertiesReader.getApplicationProperty().getProperty("encrypt.algorithm");

            cipherText = Encryptor.encrypt(algorithm, testPassword, secretKey, ivParameterSpec);

            passwordDao.createPasswordTable();
            passwordDao.insertPassword(cipherText);

            String getEncryptedPassword = passwordDao.getPassword();
            System.out.println("Password: " +getEncryptedPassword);
            assertTrue(cipherText != null);
            assertEquals(cipherText, getEncryptedPassword);

        } catch (SQLException | NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException
                | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdatePassword(){
        String getEncryptedPassword = "";
        String cipherText = "";
        String testOldPassword = "Fdma-app2022";
        String testNewPassword = "UpdatedPassword";
        try {
            //Flush table
            conn = new DBConnection();
            conn.flushTable(DBSQL.FLUSH_PASSWORD_TABLE);

            passwordDao = new PasswordDao();
            propertiesReader = new PropertiesReader();

            SecretKey secretKey = Encryptor.generateKey(128);
            IvParameterSpec ivParameterSpec = Encryptor.generateIv();
            String algorithm = propertiesReader.getApplicationProperty().getProperty("encrypt.algorithm");

            cipherText = Encryptor.encrypt(algorithm, testOldPassword, secretKey, ivParameterSpec);

            passwordDao.createPasswordTable();
            passwordDao.insertPassword(cipherText);
            passwordDao.updatePassword(Encryptor.encrypt(algorithm, testNewPassword, secretKey, ivParameterSpec));

            getEncryptedPassword = passwordDao.getPassword();

            assertEquals(testNewPassword, Decryptor.decrypt(algorithm, getEncryptedPassword, secretKey, ivParameterSpec));
        } catch (SQLException | NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException
                | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }
}
