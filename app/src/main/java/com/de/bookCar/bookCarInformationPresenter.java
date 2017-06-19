package com.de.bookCar;

import com.de.data.APIResponse;
import com.de.data.DERepository;

import java.util.HashMap;

/**
 * Created by Bhadresh on 15/6/17.
 */

public class bookCarInformationPresenter implements bookCarInformationContract.Presenter {

    DERepository mDEDeRepository;
    bookCarInformationContract.View mView;

    public bookCarInformationPresenter(DERepository mDEDeRepository, bookCarInformationContract.View mView) {
        this.mDEDeRepository = mDEDeRepository;
        this.mView = mView;

        mView.setPresenter(this);
    }

    @Override
    public void doGetCity(HashMap<String, String> map, APIResponse apiResponse) {

        mDEDeRepository.doGetCity(map, apiResponse);
    }

    @Override
    public void doGetPaymentData(HashMap<String, String> map, APIResponse apiResponse) {

        mDEDeRepository.doGetPaymentData(map, apiResponse);
    }

    @Override
    public void doBookCar(HashMap<String, String> map, APIResponse apiResponse) {
        mDEDeRepository.doBookCar(map, apiResponse);
    }


}
