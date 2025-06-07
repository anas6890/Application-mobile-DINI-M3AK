package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.dinim3ak.data.converter.DateConverter;
import com.dinim3ak.data.converter.MethodePaiementConverter;
import com.dinim3ak.data.converter.PaiementStatusConverter;

import java.util.Date;
@TypeConverters({DateConverter.class,PaiementStatus.class,MethodePaiement.class})

@Entity
public class Paiement {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private long utilisateurId;
    private long trajetId;
    private float montant;

    @TypeConverters(DateConverter.class)
    private Date datePaiement;

    @TypeConverters(PaiementStatusConverter.class)
    private PaiementStatus statut;

    @TypeConverters(MethodePaiementConverter.class)
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
        if (this.statut == PaiementStatus.EFFECTUE) {
            System.out.println("Le paiement a déjà été effectué.");
            return false;
        }

        // Simuler le traitement du paiement
        this.datePaiement = new Date();
        this.statut = PaiementStatus.EFFECTUE;

        System.out.println("Paiement de " + montant + "€ effectué par l'utilisateur " + utilisateurId +
                " pour le trajet " + trajetId + " via " + methode +
                " le " + datePaiement);

        return true;
    }

    public void rembourser() {
        if (this.statut != PaiementStatus.EFFECTUE) {
            System.out.println("Le paiement ne peut pas être remboursé car il n'a pas été effectué.");
            return;
        }

        this.statut = PaiementStatus.REMBOURSE;

        System.out.println("Paiement de " + montant + "€ remboursé à l'utilisateur " + utilisateurId +
                " pour le trajet " + trajetId);
    }

}
