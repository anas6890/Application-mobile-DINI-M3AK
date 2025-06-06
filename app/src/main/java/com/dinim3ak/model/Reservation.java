package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;


@Entity
public class Reservation {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int passagerId;
    public int trajetId;
    public ReservationStatus statut;
    @TypeConverters(DateConverter.class)
    public Date dateReservation;

    public boolean reserver() { return true; }
    public void annuler() {}
    public void accepter() {}
    public void refuser() {}
}