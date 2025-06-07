package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.dinim3ak.data.dao.VehiculeDao;

@Entity
public class Vehicule {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int conducteurId;
    private String marque;
    private String modele;
    private String couleur;
    private int nombrePlaces;
    private String immatriculation;

    // Constructeur sans argument
    public Vehicule() {}

    // Constructeur avec les champs principaux (hors ID auto-généré)
    public Vehicule(int conducteurId, String marque, String modele, String couleur, int nombrePlaces, String immatriculation) {
        this.conducteurId = conducteurId;
        this.marque = marque;
        this.modele = modele;
        this.couleur = couleur;
        this.nombrePlaces = nombrePlaces;
        this.immatriculation = immatriculation;
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

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public int getNombrePlaces() {
        return nombrePlaces;
    }

    public void setNombrePlaces(int nombrePlaces) {
        this.nombrePlaces = nombrePlaces;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    // Méthodes existantes
    public void ajouterVehicule(VehiculeDao vehiculeDao) {
        // Attention : appel en thread séparé nécessaire en vrai usage Android
        new Thread(() -> {
            vehiculeDao.insert(this);
        }).start();
    }

    public void modifierVehicule(VehiculeDao vehiculeDao) {
        // Même remarque thread
        new Thread(() -> {
            vehiculeDao.update(this);
        }).start();
    }

}
