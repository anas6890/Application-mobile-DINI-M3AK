package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.dinim3ak.data.converter.DateConverter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Commentaire {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private long auteurId;
    private long covoiturageId;
    private String texte;

    @TypeConverters(DateConverter.class)
    private LocalDate date;

    @TypeConverters(DateConverter.class)
    private LocalTime heure;

    // Constructeur sans argument
    public Commentaire() {}

    // Constructeur avec champs (hors id auto-généré)
    public Commentaire(long auteurId, long covoiturageId, String texte, LocalDate date, LocalTime heure) {
        this.auteurId = auteurId;
        this.covoiturageId = covoiturageId;
        this.texte = texte;
        this.date = date;
        this.heure = heure;
    }

    // Getters et setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAuteurId() {
        return auteurId;
    }

    public void setAuteurId(long auteurId) {
        this.auteurId = auteurId;
    }

    public long getCovoiturageId() {
        return covoiturageId;
    }

    public void setCovoiturageId(long covoiturageId) {
        this.covoiturageId = covoiturageId;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
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
