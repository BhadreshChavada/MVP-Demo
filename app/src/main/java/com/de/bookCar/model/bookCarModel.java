package com.de.bookCar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Bhadresh on 16/6/17.
 */

public class bookCarModel {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("bookingId")
    @Expose
    private String bookingId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
}
