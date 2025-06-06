package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.dinim3ak.data.converter.CovoiturageConverter;
import com.dinim3ak.data.converter.DateConverter;

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

    @TypeConverters(CovoiturageConverter.class)
    private CovoiturageStatus statut;

    private TypeCovoiturage type;

    @Ignore
    private List<Arret> arrets = new ArrayList<>();

    @Ignore
    private List<Commentaire> commentaires = new ArrayList<>();

    // Constructors
    public Covoiturage() {
    }

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

    // Getters and Setters
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

    // Business logic methods
    public void publierTrajet() {
        if (statut != CovoiturageStatus.annule) {
            this.statut = CovoiturageStatus.Disponible;
            System.out.println("Trajet publié : " + afficherDetails());
        } else {
            System.out.println("Impossible de publier un trajet annulé.");
        }
    }

    public void modifierTrajet() {
        if (statut != CovoiturageStatus.annule) {
            // Add modification logic here if needed
            System.out.println("Trajet modifié : " + afficherDetails());
        } else {
            System.out.println("Impossible de modifier un trajet annulé.");
        }
    }

    public void annulerTrajet() {
        this.statut = CovoiturageStatus.annule;
        System.out.println("Trajet annulé : " + afficherDetails());
    }

    public String afficherDetails() {
        return "Trajet de " + villeDepart + " à " + villeArrivee +
               " le " + (dateHeureDepart != null ? dateHeureDepart.toString() : "non défini") +
               " | Prix: " + prixParPassager + "€ | Places: " + nombrePlaces +
               " | Statut: " + (statut != null ? statut.name() : "non défini");
    }

    public void reserverPlace() {
        if (statut == CovoiturageStatus.Disponible && nombrePlaces > 0) {
            nombrePlaces--;
            if (nombrePlaces == 0) {
                statut = CovoiturageStatus.Complet;
            }
            System.out.println("Place réservée. " + nombrePlaces + " places restantes.");
        } else {
            System.out.println("Impossible de réserver : plus de places ou trajet non disponible.");
        }
    }
}
