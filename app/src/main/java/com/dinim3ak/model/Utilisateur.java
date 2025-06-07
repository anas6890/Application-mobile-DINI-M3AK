package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.dinim3ak.data.converter.DateConverter;
import com.dinim3ak.data.converter.SexConverter;
import com.dinim3ak.data.dao.WalletDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Utilisateur {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String numeroTelephone;

    @TypeConverters(DateConverter.class)
    private Date dateNaissance;

    @TypeConverters(SexConverter.class)
    private Sex sexe;
    private String photoProfil;
    private float noteMoyenne;
    private long walletId;
    @TypeConverters(DateConverter.class)
    private Date dateInscription;
    private long profilId;

    // Constructeur vide requis pour Room
    public Utilisateur() {}

    // Constructeur complet
    public Utilisateur(long id, String nom, String prenom, String email, String motDePasse, String numeroTelephone,
                       Date dateNaissance, Sex sexe, String photoProfil, float noteMoyenne, long walletId,
                       Date dateInscription, long profilId) {
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

    public Date getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(Date dateNaissance) { this.dateNaissance = dateNaissance; }

    public Sex getSexe() { return sexe; }
    public void setSexe(Sex sexe) { this.sexe = sexe; }

    public String getPhotoProfil() { return photoProfil; }
    public void setPhotoProfil(String photoProfil) { this.photoProfil = photoProfil; }

    public float getNoteMoyenne() { return noteMoyenne; }
    public void setNoteMoyenne(float noteMoyenne) { this.noteMoyenne = noteMoyenne; }

    public long getWalletId() { return walletId; }
    public void setWalletId(long walletId) { this.walletId = walletId; }

    public Date getDateInscription() { return dateInscription; }
    public void setDateInscription(Date dateInscription) { this.dateInscription = dateInscription; }

    public long getProfilId() { return profilId; }
    public void setProfilId(long profilId) { this.profilId = profilId; }

    // Méthodes métier
    public void inscrire() {
        if (this.dateInscription == null) {
            this.dateInscription = new Date(); // date actuelle
        }
        // Logique supplémentaire si nécessaire (vérification unicité email, etc.)
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
        if (montant <= 0) return; // Ignore recharge négative ou nulle

        Wallet wallet = walletDao.getById((int) this.walletId);
        if (wallet != null) {
            wallet.recharger(montant);  // ajoute le montant au solde
            walletDao.update(wallet);   // sauvegarde la mise à jour
        }
    }


    public List<Covoiturage> consulterHistorique() {
        return new ArrayList<>();
    }
}
