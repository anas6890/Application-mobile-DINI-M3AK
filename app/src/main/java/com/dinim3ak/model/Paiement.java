package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity
public class Paiement {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int utilisateurId;
    public int trajetId;
    public float montant;
    @TypeConverters(DateConverter.class)
    public Date datePaiement;
    public PaiementStatus statut;
    public MethodePaiement methode;

    public boolean effectuerPaiement() { return true; }
    public void rembourser() {}
}