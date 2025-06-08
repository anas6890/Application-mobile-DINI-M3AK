package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.dinim3ak.data.converter.DateConverter;

import java.time.LocalDate;

@Entity
public class Evaluation {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private long auteurId;
    private long cibleId;
    private float note;
    private String texte;

    @TypeConverters(DateConverter.class)
    private LocalDate date;

    // Constructeur sans argument
    public Evaluation() {}

    // Constructeur avec champs principaux (hors ID auto-généré)
    public Evaluation(long auteurId, long cibleId, float note, String texte, LocalDate date) {
        this.auteurId = auteurId;
        this.cibleId = cibleId;
        this.note = note;
        this.texte = texte;
        this.date = date;
    }

    // Getters et Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public long getAuteurId() { return auteurId; }
    public void setAuteurId(long auteurId) { this.auteurId = auteurId; }

    public long getCibleId() { return cibleId; }
    public void setCibleId(long cibleId) { this.cibleId = cibleId; }

    public float getNote() { return note; }
    public void setNote(float note) { this.note = note; }

    public String getTexte() { return texte; }
    public void setTexte(String texte) { this.texte = texte; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    // Méthode existante
    public void publier() {
        if (this.date == null) {
            this.date = LocalDate.now();
        }

        System.out.println("Évaluation publiée par l'utilisateur " + auteurId +
                " sur l'utilisateur " + cibleId +
                " avec la note de " + note +
                " le " + date.toString() +
                (texte != null && !texte.isEmpty() ? " : \"" + texte + "\"" : ""));
    }
}
