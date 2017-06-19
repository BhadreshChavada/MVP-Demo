package com.de.baseClass;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Bhadresh on 9/6/17.
 */

public class baseActivity extends AppCompatActivity {

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    // TODO: 09/6/17 Call the Activity
    protected void callIntent(Class secondActivity) {

        Intent intent = new Intent(baseActivity.this, secondActivity);
        startActivity(intent);
    }


    // TODO: 09/6/17 Call Activity with Finish Previous Activity
    protected void callIntent(Class secondActivity, String finish) {

        Intent intent = new Intent(baseActivity.this, secondActivity);
        baseActivity.this.finish();
        startActivity(intent);
    }


    // TODO: 09/6/17 Call Activity with passing data
    void callIntent(Class secondActivity, Bundle bundle) {

        Intent intent = new Intent(baseActivity.this, secondActivity);
        intent.putExtra("Bundle", bundle);
        startActivity(intent);
    }


    // TODO: 09/6/17 Display Log
    void displyLog(String Tag, String Message) {
        Log.d(Tag, Message);
    }

    // TODO: 09/6/17 Display Toast
    protected void displayToast(String Message) {
        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();
    }

    // TODO: 09/6/17 Call the Fragment
    protected void callFragment(Fragment secondFragment, int frame) {
        getSupportFragmentManager().beginTransaction().replace(frame, secondFragment).addToBackStack(null).commit();
    }

    // TODO: 09/6/17 Call the Fragment without backmanage
    protected void callFragment(Fragment secondFragment, int frame, String notnull) {
        getSupportFragmentManager().beginTransaction().replace(frame, secondFragment).commit();
    }

    // TODO: 09/6/17 Check the InternetConnection
    protected boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    protected void displayProgressDialog(boolean active){
        if(active) showProgressDialog(this,null, "Please Wait...");
        else hideProgressDialog();
    }

    private void hideProgressDialog() {
        progressDialog.dismiss();
    }

    private void showProgressDialog(baseActivity baseActivity, String title, String message) {
        progressDialog = new ProgressDialog(baseActivity);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }


    protected void chooseDate(final TextView textView, final baseDate dateResponse) {


        Calendar newCalendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(baseActivity.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String date = simpleDateFormat.format(newDate.getTime());
                dateResponse.onDateSelect(date,textView);


            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(new Date().getTime());
        datePickerDialog.show();
    }

}
