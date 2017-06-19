package com.de.carDriverMapping.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Bhadresh on 12/6/17.
 */

public class driverListModel {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("driverList")
    @Expose
    private List<DriverList> driverList = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DriverList> getDriverList() {
        return driverList;
    }

    public void setDriverList(List<DriverList> driverList) {
        this.driverList = driverList;
    }
}
