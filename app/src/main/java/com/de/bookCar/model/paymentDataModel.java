package com.de.bookCar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Bhadresh on 15/6/17.
 */

public class paymentDataModel {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("invoiceCategoryList")
    @Expose
    private List<InvoiceCategoryList> invoiceCategoryList = null;
    @SerializedName("PaymentModeList")
    @Expose
    private List<PaymentModeList> paymentModeList = null;
    @SerializedName("rateOfContractList")
    @Expose
    private List<RateOfContractList> rateOfContractList = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<InvoiceCategoryList> getInvoiceCategoryList() {
        return invoiceCategoryList;
    }

    public void setInvoiceCategoryList(List<InvoiceCategoryList> invoiceCategoryList) {
        this.invoiceCategoryList = invoiceCategoryList;
    }

    public List<PaymentModeList> getPaymentModeList() {
        return paymentModeList;
    }

    public void setPaymentModeList(List<PaymentModeList> paymentModeList) {
        this.paymentModeList = paymentModeList;
    }

    public List<RateOfContractList> getRateOfContractList() {
        return rateOfContractList;
    }

    public void setRateOfContractList(List<RateOfContractList> rateOfContractList) {
        this.rateOfContractList = rateOfContractList;
    }
}
