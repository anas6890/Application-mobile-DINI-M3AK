package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity
public class Reservation {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int passagerId;
    private int trajetId;
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
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPassagerId() {
        return passagerId;
    }

    public void setPassagerId(int passagerId) {
        this.passagerId = passagerId;
    }

    public int getTrajetId() {
        return trajetId;
    }

    public void setTrajetId(int trajetId) {
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

    // Méthodes existantes
    public boolean reserver() {
        return true;
    }

    public void annuler() {
        // Implémentation à ajouter
    }

    public void accepter() {
        // Implémentation à ajouter
    }

    public void refuser() {
        // Implémentation à ajouter
    }
}
