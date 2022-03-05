package com.yorme.fdma.app.passwordmodal;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.yorme.fdma.R;
import com.yorme.fdma.app.MainActivity;
import com.yorme.fdma.app.changephonenumber.ChangePhoneNumber;
import com.yorme.fdma.core.dao.ChangePhoneNumberLogsDao;
import com.yorme.fdma.core.dao.PasswordDao;
import com.yorme.fdma.core.service.Decryptor;
import com.yorme.fdma.core.service.Encryptor;
import com.yorme.fdma.utilities.PropertiesReader;
import com.yorme.fdma.utilities.database.DBHelper;
import com.yorme.fdma.utilities.database.DBSQL;

import org.apache.commons.lang3.StringUtils;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;


@RequiresApi(api = Build.VERSION_CODES.O)
public class PasswordModal extends AppCompatActivity {

    private final static String RESET_KEY= "FDMAPPf4gvl6";
    private ChangePhoneNumberLogsDao changePhoneNumberLogsDao;

    private PasswordDao passwordDao;
    private Decryptor decryptor;
    private PropertiesReader propertiesReader;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_password_modal);

        Button btn_password = findViewById(R.id.btn_password);
        btn_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    validPassword();
                } catch (SQLException | NoSuchAlgorithmException |
                        InvalidAlgorithmParameterException | NoSuchPaddingException |
                        IllegalBlockSizeException | BadPaddingException |
                        InvalidKeyException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

    }

    public void validPassword() throws SQLException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException,
            BadPaddingException, InvalidKeyException {
        Intent switchActivityIntent = new Intent(PasswordModal.this, MainActivity.class);
        //Initialize decrypt values
//        propertiesReader = new PropertiesReader();
//        passwordDao = new PasswordDao();
//        decryptor = new Decryptor();
//        SecretKey secretKey = Encryptor.generateKey(128);
//        IvParameterSpec ivParameterSpec = Encryptor.generateIv();
//        String algorithm = propertiesReader.getApplicationProperty().getProperty("encrypt.algorithm");
//
//        String userPassword = Decryptor.decrypt(algorithm,passwordDao.getPassword(),secretKey,ivParameterSpec);
        startActivity(switchActivityIntent);
    }

    private void hardResetIfTextIsEntered(String enteredText){
        if(StringUtils.equals(enteredText,RESET_KEY)){
            dbHelper = new DBHelper(this);

            dbHelper.flushTable(DBSQL.FLUSH_PASSWORD_TABLE);
            dbHelper.flushTable(DBSQL.FLUSH_CHANGE_PASSWORD_LOG_TABLE);
            dbHelper.flushTable(DBSQL.FLUSH_CHANGE_PHONE_NUMBER_LOG_TABLE);
        }
    }

}