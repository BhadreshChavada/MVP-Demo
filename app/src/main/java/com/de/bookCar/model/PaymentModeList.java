package com.de.bookCar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Bhadresh on 15/6/17.
 */

public class PaymentModeList {

    @SerializedName("paymentModeId")
    @Expose
    private Integer paymentModeId;
    @SerializedName("paymentType")
    @Expose
    private String paymentType;

    public Integer getPaymentModeId() {
        return paymentModeId;
    }

    public void setPaymentModeId(Integer paymentModeId) {
        this.paymentModeId = paymentModeId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
