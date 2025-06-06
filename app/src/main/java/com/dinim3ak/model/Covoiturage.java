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
    private int id;
    private int conducteurId;
    private String villeDepart;
    private String villeArrivee;
    @TypeConverters(DateConverter.class)
    private Date dateHeureDepart;
    private float dureeEstimee;
    private float prixParPassager;
    private int nombrePlaces;
    private int vehiculeId;
    private StatutCovoiturage statut;
    private TypeCovoiturage type;

    @Ignore
    private List<Arret> arrets = new ArrayList<>();

    @Ignore
    private List<Commentaire> commentaires = new ArrayList<>();



    // Constructeur avec les principaux champs (hors ID auto-généré, arrets et commentaires)
    public Covoiturage(int conducteurId, String villeDepart, String villeArrivee, Date dateHeureDepart,
                       float dureeEstimee, float prixParPassager, int nombrePlaces, int vehiculeId,
                       StatutCovoiturage statut, TypeCovoiturage type) {
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
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConducteurId() {
        return conducteurId;
    }

    public void setConducteurId(int conducteurId) {
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

    public int getVehiculeId() {
        return vehiculeId;
    }

    public void setVehiculeId(int vehiculeId) {
        this.vehiculeId = vehiculeId;
    }

    public StatutCovoiturage getStatut() {
        return statut;
    }

    public void setStatut(StatutCovoiturage statut) {
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
