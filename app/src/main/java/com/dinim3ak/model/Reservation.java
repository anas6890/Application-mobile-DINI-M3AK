package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.dinim3ak.data.converter.DateConverter;
import com.dinim3ak.data.converter.ReservationStatusConverter;

import java.util.Date;

@Entity
public class Reservation {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private long passagerId;
    private long trajetId;

    @TypeConverters(ReservationStatusConverter.class)
    private ReservationStatus statut;

    @TypeConverters(DateConverter.class)
    private Date dateReservation;

    // Constructeur sans argument
    public Reservation() {}

    // Constructeur avec champs principaux (hors ID auto-généré)
    public Reservation(int passagerId, int trajetId, ReservationStatus statut, Date dateReservation) {
        this.passagerId = passagerId;
        this.trajetId = trajetId;
        this.statut = statut;
        this.dateReservation = dateReservation;
    }

    // Getters et Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPassagerId() {
        return passagerId;
    }

    public void setPassagerId(long passagerId) {
        this.passagerId = passagerId;
    }

    public long getTrajetId() {
        return trajetId;
    }

    public void setTrajetId(long trajetId) {
        this.trajetId = trajetId;
    }

    public ReservationStatus getStatut() {
        return statut;
    }

    public void setStatut(ReservationStatus statut) {
        this.statut = statut;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }


}
