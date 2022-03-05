package com.yorme.fdma.core.model.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.yorme.fdma.R;
import com.yorme.fdma.core.model.ActivationLog;
import com.yorme.fdma.core.model.ChangePasswordLog;
import com.yorme.fdma.core.model.ChangePhoneNumberLog;

import java.util.ArrayList;

public class ChangePhoneNumberLogAdapter extends ArrayAdapter<ChangePhoneNumberLog> {

    public ChangePhoneNumberLogAdapter(Context context, ArrayList<ChangePhoneNumberLog> changePhoneNumberLogs) {
        super(context, 0, changePhoneNumberLogs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ChangePhoneNumberLog changePhoneNumberLog = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_listview_change_phone_number_logs, parent, false);
        }
        // Lookup view for data population
        TextView date = convertView.findViewById(R.id.changePhoneNumberLogsTextView1);
        TextView time = convertView.findViewById(R.id.changePhoneNumberLogsTextView1);
        // Populate the data into the template view using the data object
        date.setText(changePhoneNumberLog.getLogDate().toString());
        time.setText(changePhoneNumberLog.getLogTime().toString());
        // Return the completed view to render on screen
        return convertView;
    }
}
