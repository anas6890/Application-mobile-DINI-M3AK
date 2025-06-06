package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Covoiturage {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int conducteurId;
    public String villeDepart;
    public String villeArrivee;
    @TypeConverters(DateConverter.class)
    public Date dateHeureDepart;
    public float dureeEstimee;
    public float prixParPassager;
    public int nombrePlaces;
    public int vehiculeId;
    public StatutCovoiturage statut;
    public TypeCovoiturage type;
    @Ignore
    public List<Arret> arrets = new ArrayList<>();
    @Ignore
    public List<Commentaire> commentaires = new ArrayList<>();

    public void publierTrajet() {}
    public void modifierTrajet() {}
    public void annulerTrajet() {}
    public String afficherDetails() { return villeDepart + " → " + villeArrivee; }

    // Getters/Setters...
}
