package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity
public class Message {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int expediteurId;
    private int destinataireId;
    private int trajetId;
    private String contenu;

    @TypeConverters(DateConverter.class)
    private Date dateEnvoi;



    // Constructeur avec les champs principaux (hors ID auto-généré)
    public Message(int expediteurId, int destinataireId, int trajetId, String contenu, Date dateEnvoi) {
        this.expediteurId = expediteurId;
        this.destinataireId = destinataireId;
        this.trajetId = trajetId;
        this.contenu = contenu;
        this.dateEnvoi = dateEnvoi;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExpediteurId() {
        return expediteurId;
    }

    public void setExpediteurId(int expediteurId) {
        this.expediteurId = expediteurId;
    }

    public int getDestinataireId() {
        return destinataireId;
    }

    public void setDestinataireId(int destinataireId) {
        this.destinataireId = destinataireId;
    }

    public int getTrajetId() {
        return trajetId;
    }

    public void setTrajetId(int trajetId) {
        this.trajetId = trajetId;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    // Méthode existante
    public void envoyer() {
        // Implémentation à ajouter
    }
}
