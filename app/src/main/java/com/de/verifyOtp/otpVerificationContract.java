package com.de.verifyOtp;

import com.de.baseClass.basePresenter;
import com.de.baseClass.baseView;
import com.de.data.APIResponse;

import java.util.Map;

/**
 * Created by Bhadresh on 10/6/17.
 */

public interface otpVerificationContract {

    interface View extends baseView<Presenter> {



        boolean validateInput();
    }

    interface Presenter extends basePresenter {

        void doOtpVerification(Map<String, String> params, APIResponse response);
    }
}
