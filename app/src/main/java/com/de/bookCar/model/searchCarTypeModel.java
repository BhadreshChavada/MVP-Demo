package com.de.bookCar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Bhadresh on 13/6/17.
 */

public class searchCarTypeModel {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("carTypeList")
    @Expose
    private List<CarTypeList> carTypeList = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CarTypeList> getCarTypeList() {
        return carTypeList;
    }

    public void setCarTypeList(List<CarTypeList> carTypeList) {
        this.carTypeList = carTypeList;
    }
}
