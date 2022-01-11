package com.yorme.fdma.core.service;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

//TODO
//Notify user of patient falling in wheelchair
public class SMS {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;

    public void sendSMSMessage(
            String phoneNo, String message,
            Activity activity){
        if(ContextCompat.checkSelfPermission(activity, Manifest.permission.SEND_SMS)
            != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale
                    (activity,Manifest.permission.SEND_SMS)){

            }else{
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }


}
