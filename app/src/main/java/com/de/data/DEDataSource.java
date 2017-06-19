package com.de.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by emxcel on 1/6/17.
 */

public interface DEDataSource {

    void doLogin(Map<String, String> params, APIResponse apiResponse);

    void doOtpVerification(Map<String, String> params, APIResponse apiResponse);

    void doGetCar(HashMap<String, String> params, APIResponse responses);

    void doGetDriver(HashMap<String, String> map, APIResponse responses);

    void doAssignCartoDriver(HashMap<String, String> map, APIResponse response);

    void doGetBookedCar(HashMap<String, String> map, APIResponse response);

    void doUpdateTrip(HashMap<String, String> map, APIResponse response);

    void doGetCarType(HashMap<String, String> map, APIResponse apiResponses);

    void doGetSearchCar(HashMap<String, String> map, APIResponse apiResponse);

    void doGetCity(HashMap<String, String> map, APIResponse apiResponse);

    void doGetPaymentData(HashMap<String, String> map, APIResponse apiResponse);

    void doBookCar(HashMap<String, String> map, APIResponse apiResponse);
}
