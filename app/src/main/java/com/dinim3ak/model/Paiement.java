package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.dinim3ak.data.converter.DateConverter;
import com.dinim3ak.data.converter.MethodePaiementConverter;
import com.dinim3ak.data.converter.PaiementStatusConverter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@TypeConverters({DateConverter.class, PaiementStatusConverter.class, MethodePaiementConverter.class})
public class Paiement {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private long utilisateurId;
    private long trajetId;
    private float montant;

    @TypeConverters(DateConverter.class)
    private LocalDate datePaiement;

    @TypeConverters(DateConverter.class)
    private LocalTime heurePaiement;

    @TypeConverters(PaiementStatusConverter.class)
    private PaiementStatus statut;

    @TypeConverters(MethodePaiementConverter.class)
    private MethodePaiement methode;

    // Constructeur sans argument
    public Paiement() {}

    // Constructeur principal (hors ID)
    public Paiement(long utilisateurId, long trajetId, float montant,
                    LocalDate datePaiement, LocalTime heurePaiement,
                    PaiementStatus statut, MethodePaiement methode) {
        this.utilisateurId = utilisateurId;
        this.trajetId = trajetId;
        this.montant = montant;
        this.datePaiement = datePaiement;
        this.heurePaiement = heurePaiement;
        this.statut = statut;
        this.methode = methode;
    }

    // Getters et Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public long getUtilisateurId() { return utilisateurId; }
    public void setUtilisateurId(long utilisateurId) { this.utilisateurId = utilisateurId; }

    public long getTrajetId() { return trajetId; }
    public void setTrajetId(long trajetId) { this.trajetId = trajetId; }

    public float getMontant() { return montant; }
    public void setMontant(float montant) { this.montant = montant; }

    public LocalDate getDatePaiement() { return datePaiement; }
    public void setDatePaiement(LocalDate datePaiement) { this.datePaiement = datePaiement; }

    public LocalTime getHeurePaiement() { return heurePaiement; }
    public void setHeurePaiement(LocalTime heurePaiement) { this.heurePaiement = heurePaiement; }

    public PaiementStatus getStatut() { return statut; }
    public void setStatut(PaiementStatus statut) { this.statut = statut; }

    public MethodePaiement getMethode() { return methode; }
    public void setMethode(MethodePaiement methode) { this.methode = methode; }

    // Méthodes
    public boolean effectuerPaiement() {
        if (this.statut == PaiementStatus.EFFECTUE) {
            System.out.println("Le paiement a déjà été effectué.");
            return false;
        }

        this.datePaiement = LocalDate.now();
        this.heurePaiement = LocalTime.now();
        this.statut = PaiementStatus.EFFECTUE;

        System.out.println("Paiement de " + montant + "€ effectué par l'utilisateur " + utilisateurId +
                " pour le trajet " + trajetId + " via " + methode +
                " le " + datePaiement + " à " + heurePaiement);

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
