package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Covoiturage {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private long conducteurId;
    private String villeDepart;
    private String villeArrivee;
    @TypeConverters(DateConverter.class)
    private Date dateHeureDepart;
    private float dureeEstimee;
    private float prixParPassager;
    private int nombrePlaces;
    private long vehiculeId;
    private CovoiturageStatus statut;
    private TypeCovoiturage type;

    @Ignore
    private List<Arret> arrets = new ArrayList<>();

    @Ignore
    private List<Commentaire> commentaires = new ArrayList<>();

    // Constructeur sans argument
    public Covoiturage() {}

    // Constructeur avec les principaux champs (hors ID auto-généré, arrets et commentaires)
    public Covoiturage(long conducteurId, String villeDepart, String villeArrivee, Date dateHeureDepart,
                       float dureeEstimee, float prixParPassager, int nombrePlaces, long vehiculeId,
                       CovoiturageStatus statut, TypeCovoiturage type) {
        this.conducteurId = conducteurId;
        this.villeDepart = villeDepart;
        this.villeArrivee = villeArrivee;
        this.dateHeureDepart = dateHeureDepart;
        this.dureeEstimee = dureeEstimee;
        this.prixParPassager = prixParPassager;
        this.nombrePlaces = nombrePlaces;
        this.vehiculeId = vehiculeId;
        this.statut = statut;
        this.type = type;
    }

    // Getters et Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getConducteurId() {
        return conducteurId;
    }

    public void setConducteurId(long conducteurId) {
        this.conducteurId = conducteurId;
    }

    public String getVilleDepart() {
        return villeDepart;
    }

    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    public String getVilleArrivee() {
        return villeArrivee;
    }

    public void setVilleArrivee(String villeArrivee) {
        this.villeArrivee = villeArrivee;
    }

    public Date getDateHeureDepart() {
        return dateHeureDepart;
    }

    public void setDateHeureDepart(Date dateHeureDepart) {
        this.dateHeureDepart = dateHeureDepart;
    }

    public float getDureeEstimee() {
        return dureeEstimee;
    }

    public void setDureeEstimee(float dureeEstimee) {
        this.dureeEstimee = dureeEstimee;
    }

    public float getPrixParPassager() {
        return prixParPassager;
    }

    public void setPrixParPassager(float prixParPassager) {
        this.prixParPassager = prixParPassager;
    }

    public int getNombrePlaces() {
        return nombrePlaces;
    }

    public void setNombrePlaces(int nombrePlaces) {
        this.nombrePlaces = nombrePlaces;
    }

    public long getVehiculeId() {
        return vehiculeId;
    }

    public void setVehiculeId(long vehiculeId) {
        this.vehiculeId = vehiculeId;
    }

    public CovoiturageStatus getStatut() {
        return statut;
    }

    public void setStatut(CovoiturageStatus statut) {
        this.statut = statut;
    }

    public TypeCovoiturage getType() {
        return type;
    }

    public void setType(TypeCovoiturage type) {
        this.type = type;
    }

    public List<Arret> getArrets() {
        return arrets;
    }

    public void setArrets(List<Arret> arrets) {
        this.arrets = arrets;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    // Méthodes existantes
    public void publierTrajet() {
        // Implémentation à ajouter
    }

    public void modifierTrajet() {
        // Implémentation à ajouter
    }

    public void annulerTrajet() {
        // Implémentation à ajouter
    }

    public String afficherDetails() {
        return villeDepart + " → " + villeArrivee;
    }
}
