package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Utilisateur {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nom;
    public String prenom;
    public String email;
    public String motDePasse;
    public String numeroTelephone;
    @TypeConverters(DateConverter.class)
    public Date dateNaissance;
    public Sex sexe;
    public String photoProfil;
    public float noteMoyenne;
    public int walletId;
    @TypeConverters(DateConverter.class)
    public Date dateInscription;
    public int profilId;

    public void inscrire() {}
    public boolean seConnecter(String email, String motDePasse) {
        return this.email.equals(email) && this.motDePasse.equals(motDePasse);
    }
    public void modifierProfil(String choix) {}
    public void rechargerWallet(float montant) {}
    public List<Covoiturage> consulterHistorique() { return new ArrayList<>(); }
}