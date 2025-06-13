package com.example.dinim3ak;

public class ReservationItem {
    public String passagerName;
    public String date;
    public String fromCity;
    public String fromTime;
    public String toCity;
    public String toTime;
    public String status;
    public String nombrePlaces;

    public ReservationItem(String passagerName, String date, String fromCity, String fromTime,
                           String toCity, String toTime, String status, String nombrePlaces) {
        this.passagerName = passagerName;
        this.date = date;
        this.fromCity = fromCity;
        this.fromTime = fromTime;
        this.toCity = toCity;
        this.toTime = toTime;
        this.status = status;
        this.nombrePlaces = nombrePlaces;
    }
}
