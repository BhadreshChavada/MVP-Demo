package com.de.bookedCar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Bhadresh on 12/6/17.
 */

public class CarDetails {
    @SerializedName("carId")
    @Expose
    private Integer carId;
    @SerializedName("carNumber")
    @Expose
    private String carNumber;

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }
}
