package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.dinim3ak.data.converter.DateConverter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Message {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private long expediteurId;
    private long destinataireId;
    private long trajetId;
    private String contenu;

    @TypeConverters(DateConverter.class)
    private LocalDate dateEnvoi;

    @TypeConverters(DateConverter.class)
    private LocalTime heureEnvoi;

    // Constructeur sans argument
    public Message() {}

    // Constructeur avec les champs principaux (hors ID auto-généré)
    public Message(long expediteurId, long destinataireId, long trajetId, String contenu,
                   LocalDate dateEnvoi, LocalTime heureEnvoi) {
        this.expediteurId = expediteurId;
        this.destinataireId = destinataireId;
        this.trajetId = trajetId;
        this.contenu = contenu;
        this.dateEnvoi = dateEnvoi;
        this.heureEnvoi = heureEnvoi;
    }

    // Getters et Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public long getExpediteurId() { return expediteurId; }
    public void setExpediteurId(long expediteurId) { this.expediteurId = expediteurId; }

    public long getDestinataireId() { return destinataireId; }
    public void setDestinataireId(long destinataireId) { this.destinataireId = destinataireId; }

    public long getTrajetId() { return trajetId; }
    public void setTrajetId(long trajetId) { this.trajetId = trajetId; }

    public String getContenu() { return contenu; }
    public void setContenu(String contenu) { this.contenu = contenu; }

    public LocalDate getDateEnvoi() { return dateEnvoi; }
    public void setDateEnvoi(LocalDate dateEnvoi) { this.dateEnvoi = dateEnvoi; }

    public LocalTime getHeureEnvoi() { return heureEnvoi; }
    public void setHeureEnvoi(LocalTime heureEnvoi) { this.heureEnvoi = heureEnvoi; }

    // Méthode envoyer adaptée
    public void envoyer() {
        if (this.dateEnvoi == null) {
            this.dateEnvoi = LocalDate.now();
        }
        if (this.heureEnvoi == null) {
            this.heureEnvoi = LocalTime.now();
        }

        System.out.println("Message envoyé de l'utilisateur " + expediteurId +
                " à l'utilisateur " + destinataireId +
                " pour le trajet " + trajetId +
                " le " + dateEnvoi.toString() + " à " + heureEnvoi.toString() +
                " : \"" + contenu + "\"");
    }

}
