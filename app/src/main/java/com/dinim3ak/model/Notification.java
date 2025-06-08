package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.dinim3ak.data.converter.DateConverter;
import com.dinim3ak.data.converter.TypeNotificationConverter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Notification {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private long utilisateurId;
    private String contenu;

    @TypeConverters(TypeNotificationConverter.class)
    private TypeNotification type;

    @TypeConverters(DateConverter.class)
    private LocalDate dateEnvoi;

    @TypeConverters(DateConverter.class)
    private LocalTime heureEnvoi;

    private boolean lu;

    // Constructeur sans argument
    public Notification() {}

    // Constructeur avec champs (hors id auto-généré)
    public Notification(long utilisateurId, String contenu, TypeNotification type,
                        LocalDate dateEnvoi, LocalTime heureEnvoi, boolean lu) {
        this.utilisateurId = utilisateurId;
        this.contenu = contenu;
        this.type = type;
        this.dateEnvoi = dateEnvoi;
        this.heureEnvoi = heureEnvoi;
        this.lu = lu;
    }

    // Getters et setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public long getUtilisateurId() { return utilisateurId; }
    public void setUtilisateurId(long utilisateurId) { this.utilisateurId = utilisateurId; }

    public String getContenu() { return contenu; }
    public void setContenu(String contenu) { this.contenu = contenu; }

    public TypeNotification getType() { return type; }
    public void setType(TypeNotification type) { this.type = type; }

    public LocalDate getDateEnvoi() { return dateEnvoi; }
    public void setDateEnvoi(LocalDate dateEnvoi) { this.dateEnvoi = dateEnvoi; }

    public LocalTime getHeureEnvoi() { return heureEnvoi; }
    public void setHeureEnvoi(LocalTime heureEnvoi) { this.heureEnvoi = heureEnvoi; }

    public boolean isLu() { return lu; }
    public void setLu(boolean lu) { this.lu = lu; }

    // Méthodes
    public void marquerCommeLue() {
        this.lu = true;
        System.out.println("Notification " + id + " marquée comme lue pour l'utilisateur " + utilisateurId);
    }

    public void envoyer() {
        if (this.dateEnvoi == null) this.dateEnvoi = LocalDate.now();
        if (this.heureEnvoi == null) this.heureEnvoi = LocalTime.now();
        this.lu = false;

        System.out.println("Notification envoyée à l'utilisateur " + utilisateurId +
                " [" + type + "] : \"" + contenu + "\" le " +
                dateEnvoi + " à " + heureEnvoi);
    }
}
