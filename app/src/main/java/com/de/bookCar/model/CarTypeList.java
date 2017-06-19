package com.de.bookCar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Bhadresh on 13/6/17.
 */

public class CarTypeList {

    @SerializedName("carId")
    @Expose
    private Integer carId;
    @SerializedName("carType")
    @Expose
    private String carType;

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }
}
