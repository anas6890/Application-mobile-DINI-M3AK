package com.example.dinim3ak;

public class OffreItem {
    public String driverName;
    public String date;
    public String fromCity;
    public String fromTime;
    public String toCity;
    public String toTime;
    public String price;
    public String nbPassager;

    public OffreItem(String driverName, String date, String fromCity, String fromTime, String toCity, String toTime, String price, String nbPassager) {
        this.driverName = driverName;
        this.date = date;
        this.fromCity = fromCity;
        this.fromTime = fromTime;
        this.toCity = toCity;
        this.toTime = toTime;
        this.price = price;
        this.nbPassager = nbPassager;
    }
}
