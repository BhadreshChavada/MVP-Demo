package com.de.carDriverMapping.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Bhadresh on 10/6/17.
 */

public class carDriverMappingModel {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("carList")
    @Expose
    private List<CarList> carList = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CarList> getCarList() {
        return carList;
    }

    public void setCarList(List<CarList> carList) {
        this.carList = carList;
    }
}
