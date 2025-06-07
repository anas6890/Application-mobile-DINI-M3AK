package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.dinim3ak.data.converter.DateConverter;

import java.util.Date;

@Entity
public class Evaluation {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private long auteurId;
    private long cibleId;
    private float note;
    private String texte;

    @TypeConverters(DateConverter.class)
    private Date date;

    // Constructeur sans argument
    public Evaluation() {}

    // Constructeur avec champs principaux (hors ID auto-généré)
    public Evaluation(long auteurId, long cibleId, float note, String texte, Date date) {
        this.auteurId = auteurId;
        this.cibleId = cibleId;
        this.note = note;
        this.texte = texte;
        this.date = date;
    }

    // Getters et Setters
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

    public long getCibleId() {
        return cibleId;
    }

    public void setCibleId(long cibleId) {
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
        // Si aucune date n'est fournie, on utilise la date du jour
        if (this.date == null) {
            this.date = new Date();
        }

        // Exemple de message de publication
        System.out.println("Évaluation publiée par l'utilisateur " + auteurId +
                " sur l'utilisateur " + cibleId +
                " avec la note de " + note +
                " le " + date.toString() +
                (texte != null && !texte.isEmpty() ? " : \"" + texte + "\"" : ""));
    }

}
