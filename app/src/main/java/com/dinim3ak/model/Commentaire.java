package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity
public class Commentaire {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int auteurId;
    private int covoiturageId;
    private String texte;

    @TypeConverters(DateConverter.class)
    private Date date;



    // Constructeur avec champs (hors id auto-généré)
    public Commentaire(int auteurId, int covoiturageId, String texte, Date date) {
        this.auteurId = auteurId;
        this.covoiturageId = covoiturageId;
        this.texte = texte;
        this.date = date;
    }

    // Getters et setters
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

    public int getCovoiturageId() {
        return covoiturageId;
    }

    public void setCovoiturageId(int covoiturageId) {
        this.covoiturageId = covoiturageId;
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
}
