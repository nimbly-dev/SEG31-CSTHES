package com.yorme.fdma.utilities.arduino;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.yorme.fdma.app.MainActivity;
import com.yorme.fdma.app.settings.ChangePassword;

//Android to Arduino via bluetooth
public class Ardcon extends AppCompatActivity {
    private static final int REQUEST_ENABLE_BT = 1;
    public static final String ACTION_REQUEST_ENABLE = "android.bluetooth.adapter.action.REQUEST_ENABLE";

    public void getBluetoothConnection(Context context) {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Toast.makeText(context, "This device does not support Bluetooth.", Toast.LENGTH_SHORT).show();
        } else {
            if(!bluetoothAdapter.isEnabled()){
                Toast.makeText(context, "Bluetooth is not Enabled", Toast.LENGTH_SHORT).show();

                Intent enableBtIntent = new Intent(ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);

//                ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
//                        new ActivityResultContracts.StartActivityForResult(),
//                        new ActivityResultCallback<ActivityResult>() {
//                            @Override
//                            public void onActivityResult(ActivityResult result) {
//                                if (result.getResultCode() == Activity.RESULT_OK) {
//                                    Intent data = result.getData();
//                                }
//                            }
//                        });
//
//                someActivityResultLauncher.launch(enableBtIntent);
            }
        }

    }
}
