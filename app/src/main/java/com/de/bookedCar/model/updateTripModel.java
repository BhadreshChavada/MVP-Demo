package com.de.bookedCar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Bhadresh on 13/6/17.
 */

public class updateTripModel {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("tripDetail")
    @Expose
    private TripDetail tripDetail;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TripDetail getTripDetail() {
        return tripDetail;
    }

    public void setTripDetail(TripDetail tripDetail) {
        this.tripDetail = tripDetail;
    }
}
