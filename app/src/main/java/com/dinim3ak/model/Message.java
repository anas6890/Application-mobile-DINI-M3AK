package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.dinim3ak.data.converter.DateConverter;

import java.util.Date;

@Entity
public class Message {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private long expediteurId;
    private long destinataireId;
    private long trajetId;
    private String contenu;

    @TypeConverters(DateConverter.class)
    private Date dateEnvoi;

    // Constructeur sans argument
    public Message() {}

    // Constructeur avec les champs principaux (hors ID auto-généré)
    public Message(long expediteurId, long destinataireId, long trajetId, String contenu, Date dateEnvoi) {
        this.expediteurId = expediteurId;
        this.destinataireId = destinataireId;
        this.trajetId = trajetId;
        this.contenu = contenu;
        this.dateEnvoi = dateEnvoi;
    }

    // Getters et Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getExpediteurId() {
        return expediteurId;
    }

    public void setExpediteurId(long expediteurId) {
        this.expediteurId = expediteurId;
    }

    public long getDestinataireId() {
        return destinataireId;
    }

    public void setDestinataireId(long destinataireId) {
        this.destinataireId = destinataireId;
    }

    public long getTrajetId() {
        return trajetId;
    }

    public void setTrajetId(long trajetId) {
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
        // Si aucune date n’est définie, on utilise la date actuelle
        if (this.dateEnvoi == null) {
            this.dateEnvoi = new Date();
        }

        // Affichage (à remplacer par une logique réelle si besoin)
        System.out.println("Message envoyé de l'utilisateur " + expediteurId +
                " à l'utilisateur " + destinataireId +
                " pour le trajet " + trajetId +
                " le " + dateEnvoi.toString() +
                " : \"" + contenu + "\"");
    }

}
