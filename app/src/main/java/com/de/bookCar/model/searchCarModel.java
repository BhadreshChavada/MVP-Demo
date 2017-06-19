package com.de.bookCar.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bhadresh on 14/6/17.
 */

public class searchCarModel  implements Parcelable {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("carList")
    @Expose
    private List<CarList> carList = null;
    @SerializedName("driverList")
    @Expose
    private List<DriverList> driverList = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CarList> getCarList() {
        return carList;
    }

    public void setCarList(List<CarList> carList) {
        this.carList = carList;
    }

    public List<DriverList> getDriverList() {
        return driverList;
    }

    public void setDriverList(List<DriverList> driverList) {
        this.driverList = driverList;
    }

    protected searchCarModel(Parcel in) {
        message = in.readString();
        if (in.readByte() == 0x01) {
            carList = new ArrayList<CarList>();
            in.readList(carList, CarList.class.getClassLoader());
        } else {
            carList = null;
        }
        if (in.readByte() == 0x01) {
            driverList = new ArrayList<DriverList>();
            in.readList(driverList, DriverList.class.getClassLoader());
        } else {
            driverList = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(message);
        if (carList == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(carList);
        }
        if (driverList == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(driverList);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<searchCarModel> CREATOR = new Parcelable.Creator<searchCarModel>() {
        @Override
        public searchCarModel createFromParcel(Parcel in) {
            return new searchCarModel(in);
        }

        @Override
        public searchCarModel[] newArray(int size) {
            return new searchCarModel[size];
        }
    };
}
