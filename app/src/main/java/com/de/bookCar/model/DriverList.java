package com.de.bookCar.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Bhadresh on 14/6/17.
 */

public class DriverList implements Parcelable {

    @SerializedName("driverId")
    @Expose
    private Integer driverId;
    @SerializedName("driverName")
    @Expose
    private String driverName;

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }


    protected DriverList(Parcel in) {
        driverId = in.readByte() == 0x00 ? null : in.readInt();
        driverName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (driverId == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(driverId);
        }
        dest.writeString(driverName);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<DriverList> CREATOR = new Parcelable.Creator<DriverList>() {
        @Override
        public DriverList createFromParcel(Parcel in) {
            return new DriverList(in);
        }

        @Override
        public DriverList[] newArray(int size) {
            return new DriverList[size];
        }
    };

}
