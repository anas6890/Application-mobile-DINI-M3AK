package com.example.dinim3ak;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class OffreItem implements Parcelable{
    private long id;
    private String driverName;
    private String date;
    private String fromCity;
    private String fromTime;
    private String toCity;
    private String toTime;
    private String price;
    private String nbPassager;
    private String nbPassagerRestant;

    public OffreItem(long id, String driverName, String date, String fromCity, String fromTime, String toCity, String toTime, String price, String nbPassager, String nbPassagerRestant) {
        this.id = id;
        this.driverName = driverName;
        this.date = date;
        this.fromCity = fromCity;
        this.fromTime = fromTime;
        this.toCity = toCity;
        this.toTime = toTime;
        this.price = price+" DHS";
        this.nbPassager = nbPassager+" passagers";
        this.nbPassagerRestant = nbPassagerRestant+" restants";
    }

    public long getId(){return id;}
    public void setId(long id){this.id = id;}
    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNbPassager() {
        return nbPassager;
    }

    public void setNbPassager(String nbPassager) {
        this.nbPassager = nbPassager;
    }

    public String getNbPassagerRestant() {
        return nbPassagerRestant;
    }

    public void setNbPassagerRestant(String nbPassagerRestant) {
        this.nbPassagerRestant = nbPassagerRestant;
    }

    public static final Creator<OffreItem> CREATOR = new Creator<OffreItem>() {
        @Override
        public OffreItem createFromParcel(Parcel in) {
            return new OffreItem(in);
        }

        @Override
        public OffreItem[] newArray(int size) {
            return new OffreItem[size];
        }
    };

    public OffreItem(Parcel in) {
        id = in.readLong();
        driverName = in.readString();
        date = in.readString();
        fromCity= in.readString();
        fromTime= in.readString();
        toCity = in.readString();
        toTime = in.readString();
        price = in.readString();
        nbPassager = in.readString();
        nbPassagerRestant = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(driverName);
        dest.writeString(date);
        dest.writeString(fromCity);
        dest.writeString(fromTime);
        dest.writeString(toCity);
        dest.writeString(toTime);
        dest.writeString(price);
        dest.writeString(nbPassager);
        dest.writeString(nbPassagerRestant);
    }
}
