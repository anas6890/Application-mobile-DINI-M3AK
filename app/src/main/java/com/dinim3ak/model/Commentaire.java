package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.dinim3ak.data.converter.DateConverter;

import java.util.Date;

@Entity
public class Commentaire {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private long auteurId;
    private long covoiturageId;
    private String texte;

    @TypeConverters(DateConverter.class)
    private Date date;

    // Constructeur sans argument
    public Commentaire() {}

    // Constructeur avec champs (hors id auto-généré)
    public Commentaire(long auteurId, int covoiturageId, String texte, Date date) {
        this.auteurId = auteurId;
        this.covoiturageId = covoiturageId;
        this.texte = texte;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
