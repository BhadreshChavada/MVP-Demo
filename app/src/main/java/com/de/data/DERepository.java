package com.de.data;

import android.support.annotation.NonNull;

import com.de.BuildConfig;
import com.de.data.local.DELocalDataSource;

import java.util.HashMap;
import java.util.Map;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by emxcel on 1/6/17.
 */

public class DERepository implements DEDataSource {

    private static DERepository INSTANCE = null;

    private final DEDataSource mRemoteDataSource;

    private final DELocalDataSource mLocalDataSource;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean mCacheIsDirty = false;

    // Prevent direct instantiation.
    private DERepository(@NonNull DEDataSource deRemoteDataSource,
                         @NonNull DELocalDataSource deLocalDataSource) {
        mRemoteDataSource = checkNotNull(deRemoteDataSource);
        mLocalDataSource = checkNotNull(deLocalDataSource);
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param deRemoteDataSource the backend data source
     * @param deLocalDataSource  the device storage data source
     * @return the {@link DERepository} instance
     */
    public static DERepository getInstance(DEDataSource deRemoteDataSource,
                                           DELocalDataSource deLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new DERepository(deRemoteDataSource, deLocalDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void doLogin(Map<String, String> params, APIResponse response) {
        if (BuildConfig.IS_PROD) mRemoteDataSource.doLogin(params, response);
        else mLocalDataSource.doLogin(params, response);
    }

    @Override
    public void doOtpVerification(Map<String, String> params, APIResponse apiResponse) {
        if (BuildConfig.IS_PROD) mRemoteDataSource.doOtpVerification(params, apiResponse);
        else mLocalDataSource.doOtpVerification(params, apiResponse);
    }

    @Override
    public void doGetCar(HashMap<String, String> params, APIResponse responses) {
        if (BuildConfig.IS_PROD) mRemoteDataSource.doGetCar(params, responses);
        else mLocalDataSource.doGetCar(params, responses);
    }

    @Override
    public void doGetDriver(HashMap<String, String> map, APIResponse responses) {
        if (BuildConfig.IS_PROD) mRemoteDataSource.doGetDriver(map, responses);
        else mLocalDataSource.doGetDriver(map, responses);
    }

    @Override
    public void doAssignCartoDriver(HashMap<String, String> map, APIResponse response) {
        if (BuildConfig.IS_PROD) mRemoteDataSource.doAssignCartoDriver(map, response);
        else mLocalDataSource.doAssignCartoDriver(map, response);
    }

    @Override
    public void doGetBookedCar(HashMap<String, String> map, APIResponse response) {
        if (BuildConfig.IS_PROD) mRemoteDataSource.doGetBookedCar(map, response);
        else mLocalDataSource.doGetBookedCar(map, response);
    }

    @Override
    public void doUpdateTrip(HashMap<String, String> map, APIResponse response) {
        if (BuildConfig.IS_PROD) mRemoteDataSource.doUpdateTrip(map, response);
        else mLocalDataSource.doUpdateTrip(map, response);

    }

    @Override
    public void doGetCarType(HashMap<String, String> map, APIResponse apiResponses) {
        if (BuildConfig.IS_PROD) mRemoteDataSource.doGetCarType(map, apiResponses);
        else mLocalDataSource.doGetCarType(map, apiResponses);
    }

    @Override
    public void doGetSearchCar(HashMap<String, String> map, APIResponse apiResponse) {
        if (BuildConfig.IS_PROD) mRemoteDataSource.doGetSearchCar(map, apiResponse);
        else mLocalDataSource.doGetSearchCar(map, apiResponse);
    }

    @Override
    public void doGetCity(HashMap<String, String> map, APIResponse apiResponse) {
        if (BuildConfig.IS_PROD) mRemoteDataSource.doGetCity(map, apiResponse);
        else mLocalDataSource.doGetCity(map, apiResponse);
    }

    @Override
    public void doGetPaymentData(HashMap<String, String> map, APIResponse apiResponse) {

        if (BuildConfig.IS_PROD) mRemoteDataSource.doGetPaymentData(map, apiResponse);
        else mLocalDataSource.doGetPaymentData(map, apiResponse);
    }

    @Override
    public void doBookCar(HashMap<String, String> map, APIResponse apiResponse) {
        if (BuildConfig.IS_PROD) mRemoteDataSource.doBookCar(map, apiResponse);
        else mLocalDataSource.doBookCar(map, apiResponse);
    }


}
