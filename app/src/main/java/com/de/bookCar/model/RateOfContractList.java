package com.de.bookCar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Bhadresh on 15/6/17.
 */

public class RateOfContractList {

    @SerializedName("ratOfContractId")
    @Expose
    private Integer ratOfContractId;
    @SerializedName("rateOfContractName")
    @Expose
    private String rateOfContractName;

    public Integer getRatOfContractId() {
        return ratOfContractId;
    }

    public void setRatOfContractId(Integer ratOfContractId) {
        this.ratOfContractId = ratOfContractId;
    }

    public String getRateOfContractName() {
        return rateOfContractName;
    }

    public void setRateOfContractName(String rateOfContractName) {
        this.rateOfContractName = rateOfContractName;
    }
}
