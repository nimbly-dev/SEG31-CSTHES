package com.yorme.fdma.core.service;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

//TODO
//Notify user of patient falling in wheelchair
public class SMS extends Activity{

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
//    Button sendBtn;
//    EditText txtphoneNo;
//    EditText txtMessage;
    String phoneNo, message, txtphoneNo, txtMessage;

//    Eto Pag tawag ng functions at pag set ng text at buttons.
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        sendBtn = (Button) findViewById(R.id.btnSendSMS);
//        txtphoneNo = (EditText) findViewById(R.id.editText);
//        txtMessage = (EditText) findViewById(R.id.editText2);
//
//        sendBtn.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                sendSMSMessage();
//            }
//        });
//    }

    public void sendSMSMessage() {
//        phoneNo = txtphoneNo.getText().toString();
//        message = txtMessage.getText().toString();

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }
}
