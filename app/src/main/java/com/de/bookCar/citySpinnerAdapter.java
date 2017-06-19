package com.de.bookCar;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.de.R;
import com.de.bookCar.model.CityList;

import java.util.List;

/**
 * Created by Bhadresh on 12/6/17.
 */

public class citySpinnerAdapter extends ArrayAdapter<CityList> {

    // Your sent context
    private Context context;
    // Your custom values for the spinner (User)
    private List<CityList> values;


    public citySpinnerAdapter(Context context, int textViewResourceId,
                              List<CityList> values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
    }

   

    public int getCount() {
        return values.size();
    }

    public CityList getItem(int position) {
        return values.get(position);
    }

    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        convertView = View.inflate(context, R.layout.layout_spinner_backgroung, null);
        TextView spinner_txt = (TextView) convertView.findViewById(R.id.layout_spinner_txt);
        spinner_txt.setText(values.get(position).getCityName());

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {

        convertView = View.inflate(context, R.layout.layout_spinner_backgroung, null);
        TextView spinner_txt = (TextView) convertView.findViewById(R.id.layout_spinner_txt);
        spinner_txt.setText(values.get(position).getCityName());

        return convertView;
    }
}