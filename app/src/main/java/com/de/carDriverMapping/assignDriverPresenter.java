package com.de.carDriverMapping;

import android.support.annotation.NonNull;

import com.de.data.APIResponse;
import com.de.data.DERepository;

import java.util.HashMap;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Bhadresh on 10/6/17.
 */

public class assignDriverPresenter implements assignDriverContract.Presenter {


    private final DERepository mDERepository;

    private final assignDriverContract.View mAssignDriverView;

    public assignDriverPresenter(@NonNull DERepository deRepository, @NonNull assignDriverContract.View carDriverView) {
        mDERepository = checkNotNull(deRepository);
        mAssignDriverView = checkNotNull(carDriverView);

        mAssignDriverView.setPresenter(this);
    }


    @Override
    public void doGetDriverList(HashMap<String, String> map, APIResponse Responses) {
        mDERepository.doGetDriver(map, Responses);
    }

    @Override
    public void doAssignCartoDriver(HashMap<String, String> map, APIResponse Response) {
        mDERepository.doAssignCartoDriver(map, Response);

    }
}
