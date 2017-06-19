package com.de.baseClass;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.de.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Bhadresh on 10/6/17.
 */

public class baseFragment extends Fragment {


    private ProgressDialog progressDialog;
    String date;

    // TODO: 09/6/17 Call the Activity
    protected void callIntent(Class secondActivity) {

        Intent intent = new Intent(getActivity(), secondActivity);
        startActivity(intent);
    }

    // TODO: 09/6/17 Call Activity with Finish Previous Activity
    protected void callIntent(Class secondActivity, String finish) {

        Intent intent = new Intent(getActivity(), secondActivity);
        getActivity().finish();
        startActivity(intent);
    }

    // TODO: 09/6/17 Call Activity with passing data
    void callIntent(Class secondActivity, Bundle bundle) {

        Intent intent = new Intent(getActivity(), secondActivity);
        intent.putExtra("Bundle", bundle);
        startActivity(intent);
    }

    // TODO: 09/6/17 Display Log
    void displyLog(String Tag, String Message) {
        Log.d(Tag, Message);
    }

    // TODO: 09/6/17 Display Toast
    protected void displayToast(String Message) {
        Toast.makeText(getActivity(), Message, Toast.LENGTH_SHORT).show();
    }

    // TODO: 09/6/17 Call the Fragment
    protected void callFragment(Fragment secondFragment, int frame) {

        getActivity().getSupportFragmentManager().beginTransaction().replace(frame, secondFragment).addToBackStack(null).commit();
    }

    // TODO: 09/6/17 Call the Fragment without backmanage
    protected void callFragment(Fragment secondFragment, int frame, String notnull) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(frame, secondFragment).commit();

    }

    // TODO: 14/6/17 Call the Fragment with pass Bundle
    protected void callFragment(Fragment secondFragment, int frame, Bundle bundle) {


        FragmentManager fm = getActivity().getSupportFragmentManager();
        secondFragment.setArguments(bundle);
        fm.beginTransaction().replace(R.id.content_frame, secondFragment).addToBackStack(null).commit();
    }

    // TODO: 09/6/17 Check the InternetConnection
    protected boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    // TODO: 14/6/17 Display progress Dialog
    protected void displayProgressDialog(boolean active) {
        if (active) showProgressDialog(getActivity(), null, "Please Wait...");
        else hideProgressDialog();
    }

    private void hideProgressDialog() {
        progressDialog.dismiss();
    }

    private void showProgressDialog(FragmentActivity baseActivity, String title, String message) {
        progressDialog = new ProgressDialog(baseActivity);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    protected void chooseDate(final TextView textView, final baseDate dateResponse) {


        Calendar newCalendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String date = simpleDateFormat.format(newDate.getTime());
                dateResponse.onDateSelect(date, textView);


            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(new Date().getTime());
        datePickerDialog.show();
    }


}
