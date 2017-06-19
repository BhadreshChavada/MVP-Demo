package com.de.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.de.R;
import com.de.baseClass.baseActivity;
import com.de.data.APIResponse;
import com.de.login.model.LoginModel;
import com.de.utils.constantString;
import com.de.verifyOtp.otpVerificationActivity;

import java.util.HashMap;
import java.util.Map;

import data.Injection;
import retrofit2.Call;
import retrofit2.Response;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Bhadresh on 9/6/17.
 */

public class loginActivity extends baseActivity implements loginContract.View, View.OnClickListener {

    private loginPresenter mLoginPresenter;
    private loginContract.Presenter mPresenter;
    private String strUserName;
    private String strPassword;
    Button btnSingIn;
    EditText edtContactNo, edtPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onResume() {
        super.onResume();

        init();
    }

    // TODO: 09/6/17 Initialize the widget
    private void init() {

        mLoginPresenter = new loginPresenter(Injection.provideDERepository(getApplicationContext()), this);

        btnSingIn = (Button) findViewById(R.id.btn_login);
        btnSingIn.setOnClickListener(this);

        edtContactNo = (EditText) findViewById(R.id.et_user_contactno);
        edtPassword = (EditText) findViewById(R.id.et_password);

    }


    // TODO: 09/6/17 Set the Presenter
    @Override
    public void setPresenter(loginContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    // TODO: 09/6/17 set Progress Dialog
    @Override
    public void setLoadingIndicator(boolean active) {
        displayProgressDialog(active);
    }

    // TODO: 09/6/17 check the Internet Available or not
    @Override
    public boolean isNetworkAvailable() {
        return isNetworkAvailable(this);
    }

    // TODO: 09/6/17 Display the Toast Message
    @Override
    public void showToast(String message) {
        displayToast(message);
    }


    // TODO: 09/6/17 Check the input field Validation
    @Override
    public boolean validateInput() {
        strUserName = edtContactNo.getText().toString();
        strPassword = edtPassword.getText().toString();
        if (strUserName.equals("") || strUserName == null || strUserName.length() != 10) {
            edtContactNo.setError(constantString.contactNoValidation);
            edtContactNo.requestFocus();
            return false;
        } else if (strPassword == null || strPassword.equals("")) {
            edtPassword.setError(constantString.passwordValidation);
            edtPassword.requestFocus();
            return false;
        }
        return true;
    }


    // TODO: 09/6/17 set the click of Button
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login) {
            if (isNetworkAvailable()) {
                if (validateInput()) {
                    setLoadingIndicator(true);
                    String str_userToken = Base64.encodeToString((strUserName + "-" + strPassword).getBytes(), Base64.DEFAULT);
                    Map<String, String> loginMap = new HashMap<String, String>();
                    loginMap.put(constantString.userToken, str_userToken);
                    mPresenter.doLogin(loginMap, new APIResponse<LoginModel>() {
                        @Override
                        public void onSuccess(Call<LoginModel> call, Response<LoginModel> response) {
                            setLoadingIndicator(false);
                            if (response.code() == 200) {
                                callIntent(otpVerificationActivity.class, "Finish");
                            }

                        }

                        @Override
                        public void onFailure(Call<LoginModel> call, Throwable t) {

                            setLoadingIndicator(false);
                            showToast(constantString.someThingWentWrong);
                        }
                    });
                }
            } else {
                showToast(constantString.noInternet);
            }
        }
    }
}
