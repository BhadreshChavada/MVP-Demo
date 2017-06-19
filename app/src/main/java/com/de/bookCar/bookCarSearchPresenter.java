package com.de.bookCar;

import com.de.data.APIResponse;
import com.de.data.DERepository;

import java.util.HashMap;

/**
 * Created by Bhadresh on 13/6/17.
 */

public class bookCarSearchPresenter implements bookCarSearchContract.Presenter {

    private final DERepository mDERepository;

    private final bookCarSearchContract.View mView;


    public bookCarSearchPresenter(DERepository mDERepository, bookCarSearchContract.View mView) {
        this.mDERepository = mDERepository;
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void doGetCarType(HashMap<String, String> map, APIResponse apiResponses) {
        mDERepository.doGetCarType(map,apiResponses);
    }

    @Override
    public void doGetSearch(HashMap<String, String> map, APIResponse apiResponse) {
        mDERepository.doGetSearchCar(map,apiResponse);
    }
}
