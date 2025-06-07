package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Arret {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String ville;
    private long covoiturageId;

    // Constructeur sans argument
    public Arret() {}

    // Constructeur avec champs (hors id auto-généré)
    public Arret(String ville, long covoiturageId) {
        this.ville = ville;
        this.covoiturageId = covoiturageId;
    }

    // Getters et setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public long getCovoiturageId() {
        return covoiturageId;
    }

    public void setCovoiturageId(long covoiturageId) {
        this.covoiturageId = covoiturageId;
    }
}
