package com.example.dinim3ak;

public class ReservationItem {
    public String driverName;
    public String date;
    public String fromCity;
    public String fromTime;
    public String toCity;
    public String toTime;
    public String status;

    public ReservationItem(String driverName, String date, String fromCity, String fromTime, String toCity, String toTime, String status) {
        this.driverName = driverName;
        this.date = date;
        this.fromCity = fromCity;
        this.fromTime = fromTime;
        this.toCity = toCity;
        this.toTime = toTime;
        this.status = status;
    }
}
