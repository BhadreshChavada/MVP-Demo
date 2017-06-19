package com.de.bookedCar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Bhadresh on 12/6/17.
 */

public class GuestDetails {

    @SerializedName("guestId")
    @Expose
    private Integer guestId;
    @SerializedName("guestName")
    @Expose
    private String guestName;
    @SerializedName("guestContactNo")
    @Expose
    private String guestContactNo;

    public Integer getGuestId() {
        return guestId;
    }

    public void setGuestId(Integer guestId) {
        this.guestId = guestId;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestContactNo() {
        return guestContactNo;
    }

    public void setGuestContactNo(String guestContactNo) {
        this.guestContactNo = guestContactNo;
    }
}
