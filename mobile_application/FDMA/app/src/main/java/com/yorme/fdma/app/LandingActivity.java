package com.yorme.fdma.app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.yorme.fdma.R;
import com.yorme.fdma.core.service.Decryptor;
import com.yorme.fdma.utilities.PropertiesReader;
import com.yorme.fdma.utilities.database.DBHelper;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import io.github.giuseppebrb.ardutooth.Ardutooth;

@RequiresApi(api = Build.VERSION_CODES.O)
public class LandingActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 1;
    public static final String ACTION_REQUEST_ENABLE = "android.bluetooth.adapter.action.REQUEST_ENABLE";

    private PropertiesReader propertiesReader;
    private Decryptor decryptor;
    private  DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_landing);

        //INSERT DEFAULT PASSWORD IF DEFAULT PASSWORD IS NOT SET
        dbHelper = new DBHelper(this);
        boolean getPassword = dbHelper.isDefaultPasswordExists();
        if(!getPassword){
            try {
                dbHelper.insertDefaultPassword();
            } catch (SQLException | NoSuchAlgorithmException |
                    InvalidAlgorithmParameterException | NoSuchPaddingException |
                    IllegalBlockSizeException | BadPaddingException | InvalidKeyException |
                    UnsupportedEncodingException throwables) {
                throwables.printStackTrace();
            }
        }


        Ardutooth mArdutooth = Ardutooth.getInstance(this);
        mArdutooth.setConnection();

        Button btn_password_landingpage = (Button) findViewById(R.id.btn_password_landingpage);
        btn_password_landingpage.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                goToMainActivity();
            }
        });

    }

    private void goToMainActivity() {
        Intent switchActivityIntent = new Intent(LandingActivity.this, MainActivity.class);
        startActivity(switchActivityIntent);
    }

    private void checkIfBluetoothIsOn(Context context) {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Toast.makeText(context, "This device does not support Bluetooth.", Toast.LENGTH_SHORT).show();
        } else {
            if (!bluetoothAdapter.isEnabled()) {
                Toast.makeText(context, "Bluetooth is not Enabled", Toast.LENGTH_SHORT).show();

                Intent enableBtIntent = new Intent(ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }
    }

}