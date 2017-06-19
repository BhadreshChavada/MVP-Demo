package com.de.bookedCar;

import android.support.annotation.NonNull;

import com.de.data.APIResponse;
import com.de.data.DERepository;

import java.util.HashMap;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Bhadresh on 13/6/17.
 */

public class bookedCarDetailPresenter implements bookedCarDetailContract.Presenter {

    private final DERepository mDERepository;

    private final bookedCarDetailContract.View mBookCarView;

    public bookedCarDetailPresenter(@NonNull DERepository deRepository, @NonNull bookedCarDetailContract.View bookCarView) {
        mDERepository = checkNotNull(deRepository);
        mBookCarView = checkNotNull(bookCarView);

        mBookCarView.setPresenter(this);
    }

    @Override
    public void doUpdateTrip(HashMap<String, String> map, APIResponse response) {
        mDERepository.doUpdateTrip(map,response);
    }
}
