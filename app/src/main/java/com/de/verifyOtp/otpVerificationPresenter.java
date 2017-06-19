package com.de.verifyOtp;

import android.support.annotation.NonNull;

import com.de.data.APIResponse;
import com.de.data.DERepository;

import java.util.Map;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Bhadresh on 10/6/17.
 */

public class otpVerificationPresenter implements otpVerificationContract.Presenter {

    private final DERepository mDERepository;

    private final otpVerificationContract.View mOtpVerficationView;

    public otpVerificationPresenter(@NonNull DERepository deRepository, @NonNull otpVerificationContract.View otpVerificationView) {
        mDERepository = checkNotNull(deRepository);
        mOtpVerficationView = checkNotNull(otpVerificationView);

        mOtpVerficationView.setPresenter(this);
    }

    @Override
    public void doOtpVerification(Map<String, String> params, APIResponse response) {
        mDERepository.doOtpVerification(params, response);
    }
}
