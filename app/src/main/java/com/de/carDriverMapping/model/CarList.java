package com.de.carDriverMapping.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Bhadresh on 10/6/17.
 */

public class CarList  implements Parcelable {

    @SerializedName("carId")
    @Expose
    private Integer carId;
    @SerializedName("carNumber")
    @Expose
    private String carNumber;
    @SerializedName("carName")
    @Expose
    private String carName;
    @SerializedName("carType")
    @Expose
    private CarType carType;
    @SerializedName("driverAssignedStatus")
    @Expose
    private Integer driverAssignedStatus;
    @SerializedName("driverDetails")
    @Expose
    private DriverDetails driverDetails;




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

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public Integer getDriverAssignedStatus() {
        return driverAssignedStatus;
    }

    public void setDriverAssignedStatus(Integer driverAssignedStatus) {
        this.driverAssignedStatus = driverAssignedStatus;
    }

    public DriverDetails getDriverDetails() {
        return driverDetails;
    }

    public void setDriverDetails(DriverDetails driverDetails) {
        this.driverDetails = driverDetails;
    }

    protected CarList(Parcel in) {
        carId = in.readByte() == 0x00 ? null : in.readInt();
        carNumber = in.readString();
        carName = in.readString();
        carType = (CarType) in.readValue(CarType.class.getClassLoader());
        driverAssignedStatus = in.readByte() == 0x00 ? null : in.readInt();
        driverDetails = (DriverDetails) in.readValue(DriverDetails.class.getClassLoader());
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
        dest.writeString(carName);
        dest.writeValue(carType);
        if (driverAssignedStatus == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(driverAssignedStatus);
        }
        dest.writeValue(driverDetails);
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
