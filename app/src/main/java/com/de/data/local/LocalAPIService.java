package com.de.data.local;


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

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by emxcel on 6/6/17.
 */

public interface LocalAPIService {

    @GET("/login.json")
    Call<LoginModel> getLoginCall();

    @GET("/verifyotp.json")
    Call<otpVerificationModel> getOtpVerificationCall();

    @GET("/getcarlist.json")
    Call<carDriverMappingModel> getCarList();

    @GET("/getunassigneddriverlist.json")
    Call<driverListModel> getDriverList();

    @GET("/updatecardrivingmapping.json")
    Call<updateCarDriverMappingModel> AssignCaarDriverMapping();

    @GET("/bookedcarlist.json")
    Call<bookedCarModel> getBookedCarList();

    @GET("/updatetripdetails.json")
    Call<updateTripModel> updateTrip();

    @GET("/getcartype.json")
    Call<searchCarTypeModel> getCarType();

    @GET("/searchcar.json")
    Call<searchCarModel> getSearchCar();

    @GET("/paymentdata.json")
    Call<paymentDataModel> getPaymentData();

    @GET("/citylist.json")
    Call<cityListModel> getCityList();

    @GET("/bookcar.json")
    Call<bookCarModel> getBookCarData();
}
