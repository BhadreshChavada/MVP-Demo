package com.de.data.remote;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.de.R;
import com.de.bookCar.model.bookCarModel;
import com.de.bookCar.model.cityListModel;
import com.de.bookCar.model.paymentDataModel;
import com.de.bookCar.model.searchCarModel;
import com.de.bookCar.model.searchCarTypeModel;
import com.de.bookedCar.model.bookedCarModel;
import com.de.bookedCar.model.updateTripModel;
import com.de.carDriverMapping.model.carDriverMappingModel;
import com.de.carDriverMapping.model.driverListModel;
import com.de.carDriverMapping.model.updateCarDriverMappingModel;
import com.de.data.APIResponse;
import com.de.data.DEDataSource;
import com.de.login.model.LoginModel;
import com.de.verifyOtp.model.otpVerificationModel;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by emxcel on 1/6/17.
 */

public class DERemoteDataSource implements DEDataSource {

    private static DERemoteDataSource INSTANCE;


    private Context mContext;

    // Prevent direct instantiation.
    private DERemoteDataSource(@NonNull Context context) {
        checkNotNull(mContext = context);
    }

    public static DERemoteDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new DERemoteDataSource(context);
        }
        return INSTANCE;
    }

    //TODO SetupRetrofit Method here
    public RemoteAPIService setupRetrofit(String URL) {
        return new Retrofit
                .Builder()
                .baseUrl(mContext.getString(R.string.api_base))
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build().create(RemoteAPIService.class);
    }

    @Override
    public void doLogin(Map<String, String> params, final APIResponse apiResponse) {
        Call<LoginModel> loginCall = setupRetrofit("").getLoginCall(params);
        Log.d("url", loginCall.request().url().toString());
        loginCall.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                apiResponse.onSuccess(call, response);
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                apiResponse.onFailure(call, t);
            }
        });

    }

    @Override
    public void doOtpVerification(Map<String, String> params, final APIResponse apiResponse) {
        Call<otpVerificationModel> otpVerificationCall = setupRetrofit("").getOtpVerificationCall(params);
        Log.d("url", otpVerificationCall.request().url().toString());
        otpVerificationCall.enqueue(new Callback<otpVerificationModel>() {
            @Override
            public void onResponse(Call<otpVerificationModel> call, Response<otpVerificationModel> response) {
                apiResponse.onSuccess(call, response);
            }

            @Override
            public void onFailure(Call<otpVerificationModel> call, Throwable t) {
                apiResponse.onFailure(call, t);
            }
        });

    }

    @Override
    public void doGetCar(HashMap<String, String> params, final APIResponse apiResponse) {

        Call<carDriverMappingModel> getCarCall = setupRetrofit("").getcarDriverMappingCarList(params);
        Log.d("url", "URL: " + getCarCall.request().url().toString());
        getCarCall.enqueue(new Callback<carDriverMappingModel>() {
            @Override
            public void onResponse(Call<carDriverMappingModel> call, Response<carDriverMappingModel> response) {

                apiResponse.onSuccess(call, response);

            }

            @Override
            public void onFailure(Call<carDriverMappingModel> call, Throwable t) {
                apiResponse.onFailure(call, t);
            }
        });
    }

    @Override
    public void doGetDriver(HashMap<String, String> map, final APIResponse responses) {


        Call<driverListModel> getDriverCall = setupRetrofit("").getcarDriverMappingDriverList(map);
        Log.d("url", "URL: " + getDriverCall.request().url().toString());
        getDriverCall.enqueue(new Callback<driverListModel>() {
            @Override
            public void onResponse(Call<driverListModel> call, Response<driverListModel> response) {

                responses.onSuccess(call, response);

            }

            @Override
            public void onFailure(Call<driverListModel> call, Throwable t) {
                responses.onFailure(call, t);
            }
        });
    }

    @Override
    public void doAssignCartoDriver(HashMap<String, String> map, final APIResponse response) {

        Call<updateCarDriverMappingModel> updateAssignCartoDriver = setupRetrofit("").updateAssignCartoDriver(map);
        Log.d("url", "URL: " + updateAssignCartoDriver.request().url().toString());
        updateAssignCartoDriver.enqueue(new Callback<updateCarDriverMappingModel>() {
            @Override
            public void onResponse(Call<updateCarDriverMappingModel> call, Response<updateCarDriverMappingModel> responses) {

                response.onSuccess(call, responses);

            }

            @Override
            public void onFailure(Call<updateCarDriverMappingModel> call, Throwable t) {
                response.onFailure(call, t);
            }
        });

    }

    @Override
    public void doGetBookedCar(HashMap<String, String> map, final APIResponse response) {

        Call<bookedCarModel> getBookCar = setupRetrofit("").getBookCar(map);
        Log.d("url", "URL: " + getBookCar.request().url().toString());
        getBookCar.enqueue(new Callback<bookedCarModel>() {
            @Override
            public void onResponse(Call<bookedCarModel> call, Response<bookedCarModel> responses) {

                response.onSuccess(call, responses);

            }

            @Override
            public void onFailure(Call<bookedCarModel> call, Throwable t) {
                response.onFailure(call, t);
            }
        });

    }

    @Override
    public void doUpdateTrip(HashMap<String, String> map, final APIResponse response) {

        Call<updateTripModel> updateTrip = setupRetrofit("").updateTrip(map);
        Log.d("url", "URL: " + updateTrip.request().url().toString());
        updateTrip.enqueue(new Callback<updateTripModel>() {
            @Override
            public void onResponse(Call<updateTripModel> call, Response<updateTripModel> responses) {

                response.onSuccess(call, responses);

            }

            @Override
            public void onFailure(Call<updateTripModel> call, Throwable t) {
                response.onFailure(call, t);
            }
        });
    }

    @Override
    public void doGetCarType(HashMap<String, String> map, final APIResponse apiResponses) {
        Call<searchCarTypeModel> getBookCarType = setupRetrofit("").getCarType(map);
        Log.d("url", "URL: " + getBookCarType.request().url().toString());
        getBookCarType.enqueue(new Callback<searchCarTypeModel>() {
            @Override
            public void onResponse(Call<searchCarTypeModel> call, Response<searchCarTypeModel> responses) {
                apiResponses.onSuccess(call, responses);
            }

            @Override
            public void onFailure(Call<searchCarTypeModel> call, Throwable t) {
                apiResponses.onFailure(call, t);
            }
        });
    }

    @Override
    public void doGetSearchCar(HashMap<String, String> map, final APIResponse apiResponse) {

        Call<searchCarModel> getSearchCar = setupRetrofit("").getSearchCar(map);
        Log.d("url", "URL: " + getSearchCar.request().url().toString());
        getSearchCar.enqueue(new Callback<searchCarModel>() {
            @Override
            public void onResponse(Call<searchCarModel> call, Response<searchCarModel> responses) {
                apiResponse.onSuccess(call, responses);
            }

            @Override
            public void onFailure(Call<searchCarModel> call, Throwable t) {
                apiResponse.onFailure(call, t);
            }
        });
    }

    @Override
    public void doGetCity(HashMap<String, String> map, final APIResponse apiResponse) {

        Call<cityListModel> getPaymentData = setupRetrofit("").getCityList(map);
        Log.d("url", "URL: " + getPaymentData.request().url().toString());
        getPaymentData.enqueue(new Callback<cityListModel>() {
            @Override
            public void onResponse(Call<cityListModel> call, Response<cityListModel> responses) {
                apiResponse.onSuccess(call, responses);
            }

            @Override
            public void onFailure(Call<cityListModel> call, Throwable t) {
                apiResponse.onFailure(call, t);
            }
        });
    }

    @Override
    public void doGetPaymentData(HashMap<String, String> map, final APIResponse apiResponse) {

        Call<paymentDataModel> getPaymentData = setupRetrofit("").getPaymentData(map);
        Log.d("url", "URL: " + getPaymentData.request().url().toString());
        getPaymentData.enqueue(new Callback<paymentDataModel>() {
            @Override
            public void onResponse(Call<paymentDataModel> call, Response<paymentDataModel> responses) {
                apiResponse.onSuccess(call, responses);
            }

            @Override
            public void onFailure(Call<paymentDataModel> call, Throwable t) {
                apiResponse.onFailure(call, t);
            }
        });
    }

    @Override
    public void doBookCar(HashMap<String, String> map, final APIResponse apiResponse) {
        Call<bookCarModel> getBookCarData = setupRetrofit("").getBookCarData(map);
        Log.d("url", "URL: " + getBookCarData.request().url().toString());
        getBookCarData.enqueue(new Callback<bookCarModel>() {
            @Override
            public void onResponse(Call<bookCarModel> call, Response<bookCarModel> responses) {
                apiResponse.onSuccess(call, responses);
            }

            @Override
            public void onFailure(Call<bookCarModel> call, Throwable t) {
                apiResponse.onFailure(call, t);
            }
        });
    }
}
