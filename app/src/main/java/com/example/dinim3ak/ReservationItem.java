package com.example.dinim3ak;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ReservationItem implements Parcelable {
    public long id;
    public String passagerName;
    public String date;
    public String fromCity;
    public String fromTime;
    public String toCity;
    public String toTime;
    public String status;
    public String nombrePlaces;

    public ReservationItem(long id, String passagerName, String date, String fromCity, String fromTime,
                           String toCity, String toTime, String status, String nombrePlaces) {
        this.id = id;
        this.passagerName = passagerName;
        this.date = date;
        this.fromCity = fromCity;
        this.fromTime = fromTime;
        this.toCity = toCity;
        this.toTime = toTime;
        this.status = status;
        this.nombrePlaces = nombrePlaces;
    }

    public static final Creator<ReservationItem> CREATOR = new Creator<ReservationItem>() {
        @Override
        public ReservationItem createFromParcel(Parcel in) {
            return new ReservationItem(in);
        }

        @Override
        public ReservationItem[] newArray(int size) {
            return new ReservationItem[size];
        }
    };

    public ReservationItem(Parcel in) {
        id = in.readLong();
        passagerName = in.readString();
        date = in.readString();
        fromCity= in.readString();
        fromTime= in.readString();
        toCity = in.readString();
        toTime = in.readString();
        nombrePlaces = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(passagerName);
        dest.writeString(date);
        dest.writeString(fromCity);
        dest.writeString(fromTime);
        dest.writeString(toCity);
        dest.writeString(toTime);
        dest.writeString(nombrePlaces);
    }
}
