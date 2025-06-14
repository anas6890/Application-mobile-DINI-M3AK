package com.example.dinim3ak;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ReservationDemandeItem{
    private long id;
    private String passengerName;
    private String date;
    private String fromCity;
    private String fromTime;
    private String toCity;
    private String toTime;
    private String nbPassager;
    private boolean selected;

    public ReservationDemandeItem(long id, String passengerName, String date, String fromCity, String fromTime, String toCity, String toTime, String nbPassager) {
        this.id = id;
        this.passengerName = passengerName;
        this.date = date;
        this.fromCity = fromCity;
        this.fromTime = fromTime;
        this.toCity = toCity;
        this.toTime = toTime;
        this.nbPassager = nbPassager + " passagers";
        this.selected = false;
    }

    public boolean isSelected(){return selected;}
    public void setSelected(boolean selected){this.selected = selected;}
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDriverName() {
        return passengerName;
    }

    public void setDriverName(String driverName) {
        this.passengerName = driverName;
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
    public String getNbPassager() {
        return nbPassager;
    }

    public void setNbPassager(String nbPassager) {
        this.nbPassager = nbPassager;
    }

}