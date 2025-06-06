package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.dinim3ak.data.converter.DateConverter;
import com.dinim3ak.data.converter.TypeNotificationConverter;

import java.util.Date;

@Entity
public class Notification {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private long utilisateurId;
    private String contenu;

    @TypeConverters(TypeNotificationConverter.class)
    private TypeNotification type;

    @TypeConverters(DateConverter.class)
    private Date dateEnvoi;

    private boolean lu;

    // Constructeur sans argument
    public Notification() {}

    // Constructeur avec champs (hors id auto-généré)
    public Notification(long utilisateurId, String contenu, TypeNotification type, Date dateEnvoi, boolean lu) {
        this.utilisateurId = utilisateurId;
        this.contenu = contenu;
        this.type = type;
        this.dateEnvoi = dateEnvoi;
        this.lu = lu;
    }

    // Getters et setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(long utilisateurId) {
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
        System.out.println("Notification " + id + " marquée comme lue pour l'utilisateur " + utilisateurId);
    }
    public void envoyer() {
        if (this.dateEnvoi == null) {
            this.dateEnvoi = new Date();
        }
        this.lu = false;

        System.out.println("Notification envoyée à l'utilisateur " + utilisateurId +
                " [" + type + "] : \"" + contenu + "\" le " + dateEnvoi.toString());
    }


}
