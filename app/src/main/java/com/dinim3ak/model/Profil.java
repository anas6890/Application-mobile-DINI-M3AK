package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity
public class Profil {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String photoProfil;
    private String bio;
    private float noteMoyenne;
    @TypeConverters(DateConverter.class)
    private Date dateNaissance;
    private Sex sexe;
    @TypeConverters(DateConverter.class)
    private Date membreDepuis;



    // Constructeur avec tous les champs sauf ID (auto-généré)
    public Profil(String photoProfil, String bio, float noteMoyenne, Date dateNaissance, Sex sexe, Date membreDepuis) {
        this.photoProfil = photoProfil;
        this.bio = bio;
        this.noteMoyenne = noteMoyenne;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.membreDepuis = membreDepuis;
    }

    // Getters et Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhotoProfil() {
        return photoProfil;
    }

    public void setPhotoProfil(String photoProfil) {
        this.photoProfil = photoProfil;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public float getNoteMoyenne() {
        return noteMoyenne;
    }

    public void setNoteMoyenne(float noteMoyenne) {
        this.noteMoyenne = noteMoyenne;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Sex getSexe() {
        return sexe;
    }

    public void setSexe(Sex sexe) {
        this.sexe = sexe;
    }

    public Date getMembreDepuis() {
        return membreDepuis;
    }

    public void setMembreDepuis(Date membreDepuis) {
        this.membreDepuis = membreDepuis;
    }

    // Méthodes existantes
    public void modifierPhoto() {}
    public void modifierInfos() {}
    public String afficherPublic() {
        return bio;
    }
}
