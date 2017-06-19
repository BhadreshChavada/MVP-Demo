package com.de.carDriverMapping;

import android.support.annotation.NonNull;

import com.de.carDriverMapping.carDriverMappingContract.Presenter;
import com.de.data.APIResponse;
import com.de.data.DERepository;

import java.util.HashMap;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Bhadresh on 10/6/17.
 */

public class carDriverMappingPresenter implements Presenter {


    private final DERepository mDERepository;

    private final carDriverMappingContract.View mLoginView;

    public carDriverMappingPresenter(@NonNull DERepository deRepository, @NonNull carDriverMappingContract.View carDriverView) {
        mDERepository = checkNotNull(deRepository);
        mLoginView = checkNotNull(carDriverView);

        mLoginView.setPresenter(this);
    }

    @Override
    public void doGetCarList(HashMap<String, String> params, APIResponse Responses) {
        mDERepository.doGetCar(params, Responses);
    }


}
