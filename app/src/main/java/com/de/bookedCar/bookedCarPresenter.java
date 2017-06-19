package com.de.bookedCar;

import android.support.annotation.NonNull;

import com.de.data.APIResponse;
import com.de.data.DERepository;

import java.util.HashMap;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Bhadresh on 12/6/17.
 */

public class bookedCarPresenter implements bookedCarListContract.Presenter {

    private final DERepository mDERepository;

    private final bookedCarListContract.View mBookCarView;

    public bookedCarPresenter(@NonNull DERepository deRepository, @NonNull bookedCarListContract.View bookCarView) {
        mDERepository = checkNotNull(deRepository);
        mBookCarView = checkNotNull(bookCarView);

        mBookCarView.setPresenter(this);
    }

    @Override
    public void doGetBookedCarList(HashMap<String, String> map, APIResponse response) {
        mDERepository.doGetBookedCar(map,response);
    }
}
