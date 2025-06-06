package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Wallet {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public float solde;
    public int utilisateurId;
    @Ignore
    public List<Transaction> transactions = new ArrayList<>();

    public void recharger(float montant) { this.solde += montant; }
    public void recupererArgent() {}
    public void ajouterRib() {}
    public List<Transaction> consulterHistorique() { return transactions; }
}