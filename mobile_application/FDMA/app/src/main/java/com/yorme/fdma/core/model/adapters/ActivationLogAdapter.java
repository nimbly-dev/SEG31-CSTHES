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

import java.util.ArrayList;

public class ActivationLogAdapter extends ArrayAdapter<ActivationLog> {

    public ActivationLogAdapter(Context context, ArrayList<ActivationLog> activationlogs) {
        super(context, 0, activationlogs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ActivationLog activationLog = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_listview_activation_logs, parent, false);
        }
        // Lookup view for data population
        TextView date = convertView.findViewById(R.id.activationLogsTextView1);
        TextView time = convertView.findViewById(R.id.activationLogsTextView2);
        // Populate the data into the template view using the data object
        date.setText(activationLog.getLogDate().toString());
        time.setText(activationLog.getLogTime().toString());
        // Return the completed view to render on screen
        return convertView;
    }
}
