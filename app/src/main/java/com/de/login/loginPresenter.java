package com.de.login;

import android.support.annotation.NonNull;

import com.de.data.APIResponse;
import com.de.data.DERepository;

import java.util.Map;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Bhadresh on 9/6/17.
 */

public class loginPresenter implements loginContract.Presenter {

    private final DERepository mDERepository;

    private final loginContract.View mLoginView;

    public loginPresenter(@NonNull DERepository deRepository, @NonNull loginContract.View loginView) {
        mDERepository = checkNotNull(deRepository);
        mLoginView = checkNotNull(loginView);

        mLoginView.setPresenter(this);
    }


    @Override
    public void doLogin(Map<String, String> params, APIResponse response) {
        mDERepository.doLogin(params, response);
    }


}