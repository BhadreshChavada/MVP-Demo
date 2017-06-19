package com.de.bookCar;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.de.R;
import com.de.bookCar.model.PaymentModeList;

import java.util.List;

/**
 * Created by Bhadresh on 12/6/17.
 */

public class paymentTypeSpinnerAdapter extends ArrayAdapter<PaymentModeList> {

    // Your sent context
    private Context context;
    // Your custom values for the spinner (User)
    private List<PaymentModeList> values;


    public paymentTypeSpinnerAdapter(Context context, int textViewResourceId,
                                     List<PaymentModeList> values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
    }

   

    public int getCount() {
        return values.size();
    }

    public PaymentModeList getItem(int position) {
        return values.get(position);
    }

    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        convertView = View.inflate(context, R.layout.layout_spinner_backgroung, null);
        TextView spinner_txt = (TextView) convertView.findViewById(R.id.layout_spinner_txt);
        spinner_txt.setText(values.get(position).getPaymentType());

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {

        convertView = View.inflate(context, R.layout.layout_spinner_backgroung, null);
        TextView spinner_txt = (TextView) convertView.findViewById(R.id.layout_spinner_txt);
        spinner_txt.setText(values.get(position).getPaymentType());

        return convertView;
    }
}