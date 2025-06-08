package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.dinim3ak.data.converter.DateConverter;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@TypeConverters({DateConverter.class})
public class Transaction {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private float montant;
    private String description;

    private LocalDate date;
    private LocalTime heure;

    // Constructeur sans argument
    public Transaction() {}

    // Constructeur avec tous les champs sauf ID (auto-généré)
    public Transaction(float montant, String description, LocalDate date, LocalTime heure) {
        this.montant = montant;
        this.description = description;
        this.date = date;
        this.heure = heure;
    }

    // Getters et Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHeure() {
        return heure;
    }

    public void setHeure(LocalTime heure) {
        this.heure = heure;
    }
}
