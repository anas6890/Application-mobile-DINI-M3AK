package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;


import com.dinim3ak.data.converter.DateConverter;
import com.dinim3ak.data.converter.SexConverter;
import com.dinim3ak.data.dao.WalletDao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@TypeConverters({DateConverter.class, SexConverter.class})
public class Utilisateur {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String numeroTelephone;
    private LocalDate dateNaissance;
    private Sex sexe;
    private String photoProfil;
    private float noteMoyenne;
    private long walletId;
    private LocalDate dateInscription;
    private LocalTime heureInscription;
    private long profilId;

    // Constructeur vide requis par Room
    public Utilisateur() {}

    // Constructeur complet
    public Utilisateur(long id, String nom, String prenom, String email, String motDePasse, String numeroTelephone,
                       LocalDate dateNaissance, Sex sexe, String photoProfil, float noteMoyenne, long walletId,
                       LocalDate dateInscription, LocalTime heureInscription, long profilId) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.numeroTelephone = numeroTelephone;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.photoProfil = photoProfil;
        this.noteMoyenne = noteMoyenne;
        this.walletId = walletId;
        this.dateInscription = dateInscription;
        this.heureInscription = heureInscription;
        this.profilId = profilId;
    }

    // Getters et Setters

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }

    public String getNumeroTelephone() { return numeroTelephone; }
    public void setNumeroTelephone(String numeroTelephone) { this.numeroTelephone = numeroTelephone; }

    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }

    public Sex getSexe() { return sexe; }
    public void setSexe(Sex sexe) { this.sexe = sexe; }

    public String getPhotoProfil() { return photoProfil; }
    public void setPhotoProfil(String photoProfil) { this.photoProfil = photoProfil; }

    public float getNoteMoyenne() { return noteMoyenne; }
    public void setNoteMoyenne(float noteMoyenne) { this.noteMoyenne = noteMoyenne; }

    public long getWalletId() { return walletId; }
    public void setWalletId(long walletId) { this.walletId = walletId; }

    public LocalDate getDateInscription() { return dateInscription; }
    public void setDateInscription(LocalDate dateInscription) { this.dateInscription = dateInscription; }

    public LocalTime getHeureInscription() { return heureInscription; }
    public void setHeureInscription(LocalTime heureInscription) { this.heureInscription = heureInscription; }

    public long getProfilId() { return profilId; }
    public void setProfilId(long profilId) { this.profilId = profilId; }

    // Méthodes métier

    public void inscrire() {
        if (this.dateInscription == null) {
            this.dateInscription = LocalDate.now();
            this.heureInscription = LocalTime.now();
        }
    }

    public boolean seConnecter(String email, String motDePasse) {
        return this.email.equals(email) && this.motDePasse.equals(motDePasse);
    }

    public void modifierProfil(String champ, String nouvelleValeur) {
        switch (champ.toLowerCase()) {
            case "nom":
                setNom(nouvelleValeur);
                break;
            case "prenom":
                setPrenom(nouvelleValeur);
                break;
            case "email":
                setEmail(nouvelleValeur);
                break;
            case "photo":
                setPhotoProfil(nouvelleValeur);
                break;
            // autres cas...
        }
    }

    public void rechargerWallet(float montant, WalletDao walletDao) {
        if (montant <= 0) return;

        Wallet wallet = walletDao.getById((int) this.walletId);
        if (wallet != null) {
            wallet.recharger(montant);
            walletDao.update(wallet);
        }
    }

    public List<Covoiturage> consulterHistorique() {
        return new ArrayList<>();
    }
}
