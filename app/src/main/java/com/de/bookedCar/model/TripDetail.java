package com.de.bookedCar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Bhadresh on 13/6/17.
 */

class TripDetail {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("tripId")
    @Expose
    private String tripId;
    @SerializedName("tripStartStartDate")
    @Expose
    private String tripStartStartDate;
    @SerializedName("tripEndDate")
    @Expose
    private String tripEndDate;
    @SerializedName("pickupGeoLocation")
    @Expose
    private String pickupGeoLocation;
    @SerializedName("dropGeoLocation")
    @Expose
    private String dropGeoLocation;
    @SerializedName("tripStatus")
    @Expose
    private Integer tripStatus;
    @SerializedName("guestDetails")
    @Expose
    private GuestDetails guestDetails;
    @SerializedName("driverDetails")
    @Expose
    private DriverDetails driverDetails;
    @SerializedName("carDetails")
    @Expose
    private CarDetails carDetails;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getTripStartStartDate() {
        return tripStartStartDate;
    }

    public void setTripStartStartDate(String tripStartStartDate) {
        this.tripStartStartDate = tripStartStartDate;
    }

    public String getTripEndDate() {
        return tripEndDate;
    }

    public void setTripEndDate(String tripEndDate) {
        this.tripEndDate = tripEndDate;
    }

    public String getPickupGeoLocation() {
        return pickupGeoLocation;
    }

    public void setPickupGeoLocation(String pickupGeoLocation) {
        this.pickupGeoLocation = pickupGeoLocation;
    }

    public String getDropGeoLocation() {
        return dropGeoLocation;
    }

    public void setDropGeoLocation(String dropGeoLocation) {
        this.dropGeoLocation = dropGeoLocation;
    }

    public Integer getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(Integer tripStatus) {
        this.tripStatus = tripStatus;
    }

    public GuestDetails getGuestDetails() {
        return guestDetails;
    }

    public void setGuestDetails(GuestDetails guestDetails) {
        this.guestDetails = guestDetails;
    }

    public DriverDetails getDriverDetails() {
        return driverDetails;
    }

    public void setDriverDetails(DriverDetails driverDetails) {
        this.driverDetails = driverDetails;
    }

    public CarDetails getCarDetails() {
        return carDetails;
    }

    public void setCarDetails(CarDetails carDetails) {
        this.carDetails = carDetails;
    }
}
