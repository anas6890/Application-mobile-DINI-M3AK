package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.dinim3ak.data.converter.DateConverter;

import java.util.Date;

@Entity
public class Transaction {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private float montant;
    private String description;

    @TypeConverters(DateConverter.class)
    private Date date;

    // Constructeur sans argument
    public Transaction() {}

    // Constructeur avec tous les champs sauf ID (auto-généré)
    public Transaction(float montant, String description, Date date) {
        this.montant = montant;
        this.description = description;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
