package com.de.verifyOtp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Bhadresh on 10/6/17.
 */

class UserDetails {

    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("contactNo")
    @Expose
    private String contactNo;
    @SerializedName("emailId")
    @Expose
    private String emailId;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("userTenantId")
    @Expose
    private Integer userTenantId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getUserTenantId() {
        return userTenantId;
    }

    public void setUserTenantId(Integer userTenantId) {
        this.userTenantId = userTenantId;
    }


}
