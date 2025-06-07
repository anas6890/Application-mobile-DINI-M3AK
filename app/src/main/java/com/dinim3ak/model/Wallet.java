package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.dinim3ak.data.dao.WalletDao;

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

    public void recupererArgent(float montant, WalletDao walletDao) {
        if (montant <= 0 || montant > solde) {
            throw new IllegalArgumentException("Montant invalide");
        }
        // Soustraire le montant du solde
        this.solde -= montant;

        // Mettre à jour le wallet en BD dans un thread
        new Thread(() -> walletDao.update(this)).start();
    }

    public void ajouterRib(String rib, WalletDao walletDao) {
        // Exemple d’ajout RIB (stockage externe ou autre)
        // Ici, on pourrait imaginer un champ rib (à ajouter dans Wallet)
        // ou une table RIB séparée liée au wallet.
        // En attendant, on peut juste log ou simuler.
        System.out.println("RIB ajouté : " + rib);
        // Pas de changement en BD pour l’instant
    }


    public List<Transaction> consulterHistorique() {
        return transactions;
    }
}
