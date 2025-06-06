package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity
public class Evaluation {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int auteurId;
    private int cibleId;
    private float note;
    private String texte;

    @TypeConverters(DateConverter.class)
    private Date date;



    // Constructeur avec champs principaux (hors ID auto-généré)
    public Evaluation(int auteurId, int cibleId, float note, String texte, Date date) {
        this.auteurId = auteurId;
        this.cibleId = cibleId;
        this.note = note;
        this.texte = texte;
        this.date = date;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuteurId() {
        return auteurId;
    }

    public void setAuteurId(int auteurId) {
        this.auteurId = auteurId;
    }

    public int getCibleId() {
        return cibleId;
    }

    public void setCibleId(int cibleId) {
        this.cibleId = cibleId;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // Méthode existante
    public void publier() {
        // Implémentation à ajouter
    }
}
