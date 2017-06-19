package com.de.carDriverMapping;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.de.R;
import com.de.carDriverMapping.model.DriverList;

import java.util.List;

/**
 * Created by Bhadresh on 12/6/17.
 */

public class driverSpinnerAdapter extends ArrayAdapter<DriverList> {

    // Your sent context
    private Context context;
    // Your custom values for the spinner (User)
    private List<DriverList> values;


    public driverSpinnerAdapter(Context context, int textViewResourceId,
                                List<DriverList> values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
    }



    public int getCount() {
        return values.size();
    }

    public DriverList getItem(int position) {
        return values.get(position);
    }

    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        convertView = View.inflate(context, R.layout.layout_spinner_backgroung, null);
        TextView spinner_txt = (TextView) convertView.findViewById(R.id.layout_spinner_txt);
        spinner_txt.setText(values.get(position).getDriverName());

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {

        convertView = View.inflate(context, R.layout.layout_spinner_backgroung, null);
        TextView spinner_txt = (TextView) convertView.findViewById(R.id.layout_spinner_txt);
        spinner_txt.setText(values.get(position).getDriverName());

        return convertView;
    }
}