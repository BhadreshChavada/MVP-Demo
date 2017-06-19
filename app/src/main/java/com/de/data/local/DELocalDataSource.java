package com.de.data.local;

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
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by emxcel on 1/6/17.
 */

public class DELocalDataSource implements DEDataSource {

    private static final String TAG = DELocalDataSource.class.getSimpleName();
    private static DELocalDataSource INSTANCE;

    private Context mContext;

    // Prevent direct instantiation.
    private DELocalDataSource(@NonNull Context context) {
        checkNotNull(mContext = context);
    }

    public static DELocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new DELocalDataSource(context);
        }
        return INSTANCE;
    }

    //TODO SetupRetrofit Method here
    public LocalAPIService setupRetrofit() {
        LocalAPIService mRestService = null;

        if (mRestService == null) {
            final OkHttpClient client = new OkHttpClient
                    .Builder()
                    .addInterceptor(new LocalIntercepter(mContext))
                    .build();

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            final Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(mContext.getString(R.string.api_base))
                    .client(client)
                    .build();

            mRestService = retrofit.create(LocalAPIService.class);
        }
        return mRestService;
    }

    @Override
    public void doLogin(Map<String, String> params, final APIResponse apiResponse) {

        Call<LoginModel> loginCall = setupRetrofit().getLoginCall();
        Log.d(TAG, "URL: " + loginCall.request().url().toString());
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

        Call<otpVerificationModel> otpVerificationCall = setupRetrofit().getOtpVerificationCall();
        Log.d(TAG, "URL: " + otpVerificationCall.request().url().toString());
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

        Call<carDriverMappingModel> CarListCall = setupRetrofit().getCarList();
        Log.d(TAG, "URL: " + CarListCall.request().url().toString());
        CarListCall.enqueue(new Callback<carDriverMappingModel>() {
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

        Call<driverListModel> DriverListCall = setupRetrofit().getDriverList();
        Log.d(TAG, "URL: " + DriverListCall.request().url().toString());
        DriverListCall.enqueue(new Callback<driverListModel>() {
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

        Call<updateCarDriverMappingModel> assignCarDriverMapping = setupRetrofit().AssignCaarDriverMapping();
        Log.d(TAG, "URL: " + assignCarDriverMapping.request().url().toString());
        assignCarDriverMapping.enqueue(new Callback<updateCarDriverMappingModel>() {
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
        Call<bookedCarModel> bookedCarList = setupRetrofit().getBookedCarList();
        Log.d(TAG, "URL: " + bookedCarList.request().url().toString());
        bookedCarList.enqueue(new Callback<bookedCarModel>() {
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
        Call<updateTripModel> updateTrip = setupRetrofit().updateTrip();
        Log.d(TAG, "URL: " + updateTrip.request().url().toString());
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

        Call<searchCarTypeModel> carTypeList = setupRetrofit().getCarType();
        Log.d(TAG, "URL: " + carTypeList.request().url().toString());
        carTypeList.enqueue(new Callback<searchCarTypeModel>() {
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
        Call<searchCarModel> searachCarList = setupRetrofit().getSearchCar();
        Log.d(TAG, "URL: " + searachCarList.request().url().toString());
        searachCarList.enqueue(new Callback<searchCarModel>() {
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

        Call<cityListModel> getCityList = setupRetrofit().getCityList();
        Log.d("url", "URL: " + getCityList.request().url().toString());
        getCityList.enqueue(new Callback<cityListModel>() {
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

        Call<paymentDataModel> getPaymentData = setupRetrofit().getPaymentData();
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
        Call<bookCarModel> getBookCarData = setupRetrofit().getBookCarData();
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
