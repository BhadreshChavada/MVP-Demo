package com.de.data.remote;

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
import com.de.login.model.LoginModel;
import com.de.verifyOtp.model.otpVerificationModel;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by root on 15/4/17.
 */

public interface RemoteAPIService {
    @GET("/login/app/de-login-check")
    Call<LoginModel> getLoginCall(
            @QueryMap(encoded = true) Map<String, String> param);

    @GET("/login/app/de-verify-otp")
    Call<otpVerificationModel> getOtpVerificationCall(
            @QueryMap(encoded = true) Map<String, String> param);

    @GET("/login/app/getCarList")
    Call<carDriverMappingModel> getcarDriverMappingCarList(@QueryMap(encoded = true) Map<String, String> param);

    @GET("/login/app/getUnassignedDriverList")
    Call<driverListModel> getcarDriverMappingDriverList(@QueryMap(encoded = true) Map<String, String> param);

    @GET("/login/app/updateCarDriverMapping")
    Call<updateCarDriverMappingModel> updateAssignCartoDriver(@QueryMap(encoded = true) HashMap<String, String> map);

    @GET("/login/app/bookedCarList")
    Call<bookedCarModel> getBookCar(@QueryMap(encoded = true) HashMap<String, String> map);

    @GET("/login/app/upateTripDetail")
    Call<updateTripModel> updateTrip(HashMap<String, String> map);

    @GET("/login/app/getCarType")
    Call<searchCarTypeModel> getCarType(HashMap<String, String> map);

    @GET("/login/app/searchCar")
    Call<searchCarModel> getSearchCar(HashMap<String, String> map);

    @GET("/login/app/getPaymentMode")
    Call<paymentDataModel> getPaymentData(HashMap<String, String> map);

    @GET("/login/app/getCityList")
    Call<cityListModel> getCityList(HashMap<String, String> map);

    @GET("/login/app/bookCar")
    Call<bookCarModel> getBookCarData(HashMap<String, String> map);
}
