package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity
public class Paiement {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private long utilisateurId;
    private long trajetId;
    private float montant;

    @TypeConverters(DateConverter.class)
    private Date datePaiement;

    private PaiementStatus statut;
    private MethodePaiement methode;

    // Constructeur sans argument
    public Paiement() {}

    // Constructeur avec les champs principaux (hors ID auto-généré)
    public Paiement(long utilisateurId, int trajetId, float montant, Date datePaiement, PaiementStatus statut, MethodePaiement methode) {
        this.utilisateurId = utilisateurId;
        this.trajetId = trajetId;
        this.montant = montant;
        this.datePaiement = datePaiement;
        this.statut = statut;
        this.methode = methode;
    }

    // Getters et Setters
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

    public long getTrajetId() {
        return trajetId;
    }

    public void setTrajetId(long trajetId) {
        this.trajetId = trajetId;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public Date getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(Date datePaiement) {
        this.datePaiement = datePaiement;
    }

    public PaiementStatus getStatut() {
        return statut;
    }

    public void setStatut(PaiementStatus statut) {
        this.statut = statut;
    }

    public MethodePaiement getMethode() {
        return methode;
    }

    public void setMethode(MethodePaiement methode) {
        this.methode = methode;
    }

    // Méthodes existantes
    public boolean effectuerPaiement() {
        return true;
    }

    public void rembourser() {
        // Implémentation à ajouter
    }
}
