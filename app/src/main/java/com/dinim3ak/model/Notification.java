package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity
public class Notification {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int utilisateurId;
    private String contenu;
    private TypeNotification type;

    @TypeConverters(DateConverter.class)
    private Date dateEnvoi;

    private boolean lu;

    // Constructeur sans argument
    public Notification() {}

    // Constructeur avec champs (hors id auto-généré)
    public Notification(int utilisateurId, String contenu, TypeNotification type, Date dateEnvoi, boolean lu) {
        this.utilisateurId = utilisateurId;
        this.contenu = contenu;
        this.type = type;
        this.dateEnvoi = dateEnvoi;
        this.lu = lu;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(int utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public TypeNotification getType() {
        return type;
    }

    public void setType(TypeNotification type) {
        this.type = type;
    }

    public Date getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public boolean isLu() {
        return lu;
    }

    public void setLu(boolean lu) {
        this.lu = lu;
    }

    // Méthode existante
    public void marquerCommeLue() {
        this.lu = true;
    }
}
