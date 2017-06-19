package com.de.bookCar.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Bhadresh on 14/6/17.
 */

public class CarList implements Parcelable {
    @SerializedName("carId")
    @Expose
    private Integer carId;
    @SerializedName("carNumber")
    @Expose
    private String carNumber;
    @SerializedName("carType")
    @Expose
    private String carType;
    @SerializedName("carName")
    @Expose
    private String carName;
    @SerializedName("bookedTime")
    @Expose
    private String bookedTime;
    @SerializedName("bufferedTime")
    @Expose
    private String bufferedTime;
    @SerializedName("availableTime")
    @Expose
    private String availableTime;

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getBookedTime() {
        return bookedTime;
    }

    public void setBookedTime(String bookedTime) {
        this.bookedTime = bookedTime;
    }

    public String getBufferedTime() {
        return bufferedTime;
    }

    public void setBufferedTime(String bufferedTime) {
        this.bufferedTime = bufferedTime;
    }

    public String getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(String availableTime) {
        this.availableTime = availableTime;
    }

    protected CarList(Parcel in) {
        carId = in.readByte() == 0x00 ? null : in.readInt();
        carNumber = in.readString();
        carType = in.readString();
        carName = in.readString();
        bookedTime = in.readString();
        bufferedTime = in.readString();
        availableTime = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (carId == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(carId);
        }
        dest.writeString(carNumber);
        dest.writeString(carType);
        dest.writeString(carName);
        dest.writeString(bookedTime);
        dest.writeString(bufferedTime);
        dest.writeString(availableTime);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<CarList> CREATOR = new Parcelable.Creator<CarList>() {
        @Override
        public CarList createFromParcel(Parcel in) {
            return new CarList(in);
        }

        @Override
        public CarList[] newArray(int size) {
            return new CarList[size];
        }
    };
}
