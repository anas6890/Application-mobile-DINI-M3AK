package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Vehicule {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int conducteurId;
    public String marque;
    public String modele;
    public String couleur;
    public int nombrePlaces;
    public String immatriculation;

    public void ajouterVehicule() {}
    public void modifierVehicule() {}
}