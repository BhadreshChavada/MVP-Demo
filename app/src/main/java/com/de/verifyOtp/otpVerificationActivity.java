package com.de.verifyOtp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.de.R;
import com.de.baseClass.baseActivity;
import com.de.data.APIResponse;
import com.de.navigationMenu.navigationActivity;
import com.de.utils.constantString;
import com.de.verifyOtp.model.otpVerificationModel;

import java.util.HashMap;

import data.Injection;
import retrofit2.Call;
import retrofit2.Response;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Bhadresh on 10/6/17.
 */

public class otpVerificationActivity extends baseActivity implements otpVerificationContract.View, View.OnClickListener {

    private otpVerificationPresenter otpVerificationPresenter;
    private otpVerificationContract.Presenter mPresenter;

    EditText edtOtp;
    Button btnverify;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
    }

    @Override
    protected void onResume() {
        super.onResume();

        otpVerificationPresenter = new otpVerificationPresenter(Injection.provideDERepository(getApplicationContext()), this);
        init();
    }

    // TODO: 10/6/17 Initialize the widget
    private void init() {

        edtOtp = (EditText) findViewById(R.id.edt_verify_otp);
        btnverify = (Button) findViewById(R.id.btn_verify);
        btnverify.setOnClickListener(this);


    }

    // TODO: 10/6/17 Set the Presenter
    @Override
    public void setPresenter(otpVerificationContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    // TODO: 10/6/17 set Progress Dialog
    @Override
    public void setLoadingIndicator(boolean active) {
        displayProgressDialog(active);
    }

    // TODO: 10/6/17 check the Internet Available or not
    @Override
    public boolean isNetworkAvailable() {
        return isNetworkAvailable(otpVerificationActivity.this);
    }

    // TODO: 10/6/17 Display the Toast Message
    @Override
    public void showToast(String message) {

    }

    // TODO: 10/6/17 Check the input field Validation
    @Override
    public boolean validateInput() {

        if (edtOtp.getText().length() != 6) {
            edtOtp.setError(constantString.otpValidaion);
            edtOtp.requestFocus();
            return false;
        }
        return true;
    }

    // TODO: 10/6/17 set the click of Button
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_verify) {

            if (isNetworkAvailable()) {
                if (validateInput()) {
                    setLoadingIndicator(true);
                    HashMap<String, String> map = new HashMap<>();
                    map.put("OTP", edtOtp.getText().toString());


                    otpVerificationPresenter.doOtpVerification(map, new APIResponse<otpVerificationModel>() {
                        @Override
                        public void onSuccess(Call<otpVerificationModel> call, Response<otpVerificationModel> response) {

                            setLoadingIndicator(false);
                            callIntent(navigationActivity.class, "Finish");
                        }

                        @Override
                        public void onFailure(Call<otpVerificationModel> call, Throwable t) {

                            setLoadingIndicator(false);
                        }
                    });
                }
            }
        }
    }
}
