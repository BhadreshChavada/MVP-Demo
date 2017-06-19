package com.de.bookCar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Bhadresh on 15/6/17.
 */

public class InvoiceCategoryList {

    @SerializedName("invoiceCategoryId")
    @Expose
    private Integer invoiceCategoryId;
    @SerializedName("invoiceCategoryName")
    @Expose
    private String invoiceCategoryName;

    public Integer getInvoiceCategoryId() {
        return invoiceCategoryId;
    }

    public void setInvoiceCategoryId(Integer invoiceCategoryId) {
        this.invoiceCategoryId = invoiceCategoryId;
    }

    public String getInvoiceCategoryName() {
        return invoiceCategoryName;
    }

    public void setInvoiceCategoryName(String invoiceCategoryName) {
        this.invoiceCategoryName = invoiceCategoryName;
    }
}
