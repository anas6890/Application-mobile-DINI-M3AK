package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.dinim3ak.data.converter.DateConverter;
import com.dinim3ak.data.converter.ReservationStatusConverter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@TypeConverters({ReservationStatusConverter.class, DateConverter.class})
public class Reservation {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private long passagerId;
    private long trajetId;
    private ReservationStatus statut;

    private LocalDate dateReservation;
    private LocalTime heureReservation;

    // Constructeur sans argument
    public Reservation() {}

    // Constructeur avec champs principaux
    public Reservation(long passagerId, long trajetId, ReservationStatus statut,
                       LocalDate dateReservation, LocalTime heureReservation) {
        this.passagerId = passagerId;
        this.trajetId = trajetId;
        this.statut = statut;
        this.dateReservation = dateReservation;
        this.heureReservation = heureReservation;
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

    public LocalDate getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDate dateReservation) {
        this.dateReservation = dateReservation;
    }

    public LocalTime getHeureReservation() {
        return heureReservation;
    }

    public void setHeureReservation(LocalTime heureReservation) {
        this.heureReservation = heureReservation;
    }
}
