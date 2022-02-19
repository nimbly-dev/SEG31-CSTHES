package com.yorme.fdma.utilities.arduino;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yorme.fdma.app.MainActivity;
import com.yorme.fdma.app.settings.ChangePassword;

//Android to Arduino via bluetooth
public class Ardcon extends AppCompatActivity {
    private static final int REQUEST_ENABLE_BT = 1;

    public void getBluetoothConnection(Context context){
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        try {
            if (!bluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        } catch (NullPointerException nce){
            Toast.makeText(context, "This device does not support Bluetooth.", Toast.LENGTH_SHORT).show();
        }

    }

    
}
