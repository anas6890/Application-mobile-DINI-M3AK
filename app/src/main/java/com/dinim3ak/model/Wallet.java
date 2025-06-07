package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Wallet {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private float solde;
    private long utilisateurId;

    @Ignore
    private List<Transaction> transactions = new ArrayList<>();

    // Constructeur sans argument
    public Wallet() {}

    // Constructeur avec champs principaux (sauf ID auto-généré et transactions ignoré par Room)
    public Wallet(float solde, long utilisateurId) {
        this.solde = solde;
        this.utilisateurId = utilisateurId;
    }

    // Getters et Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }

    public long getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    // Méthodes existantes
    public void recharger(float montant) {
        this.solde += montant;
    }

    public void recupererArgent() {
        // Implémentation à ajouter
    }

    public void ajouterRib() {
        // Implémentation à ajouter
    }

    public List<Transaction> consulterHistorique() {
        return transactions;
    }
}
