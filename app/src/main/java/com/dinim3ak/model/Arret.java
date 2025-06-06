package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Arret {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String ville;
    private int covoiturageId;



    // Constructeur avec champs (hors id auto-généré)
    public Arret(String ville, int covoiturageId) {
        this.ville = ville;
        this.covoiturageId = covoiturageId;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getCovoiturageId() {
        return covoiturageId;
    }

    public void setCovoiturageId(int covoiturageId) {
        this.covoiturageId = covoiturageId;
    }
}
