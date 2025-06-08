package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.dinim3ak.data.converter.DateConverter;
import com.dinim3ak.data.converter.SexConverter;

import java.time.LocalDate;

@TypeConverters({DateConverter.class, SexConverter.class})
@Entity
public class Profil {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String photoProfil;
    private String bio;
    private float noteMoyenne;

    @TypeConverters(DateConverter.class)
    private LocalDate dateNaissance;

    @TypeConverters(DateConverter.class)
    private LocalDate membreDepuis;

    @TypeConverters(SexConverter.class)
    private Sex sexe;

    // Constructeur avec tous les champs sauf ID (auto-généré)
    public Profil(String photoProfil, String bio, float noteMoyenne, LocalDate dateNaissance, Sex sexe, LocalDate membreDepuis) {
        this.photoProfil = photoProfil;
        this.bio = bio;
        this.noteMoyenne = noteMoyenne;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.membreDepuis = membreDepuis;
    }

    // Getters et Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getPhotoProfil() { return photoProfil; }
    public void setPhotoProfil(String photoProfil) { this.photoProfil = photoProfil; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public float getNoteMoyenne() { return noteMoyenne; }
    public void setNoteMoyenne(float noteMoyenne) { this.noteMoyenne = noteMoyenne; }

    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }

    public LocalDate getMembreDepuis() { return membreDepuis; }
    public void setMembreDepuis(LocalDate membreDepuis) { this.membreDepuis = membreDepuis; }

    public Sex getSexe() { return sexe; }
    public void setSexe(Sex sexe) { this.sexe = sexe; }

    // Méthodes
    public void modifierPhoto(String nouvellePhoto) {
        if (nouvellePhoto != null && !nouvellePhoto.isEmpty()) {
            this.photoProfil = nouvellePhoto;
            System.out.println("Photo de profil mise à jour : " + nouvellePhoto);
        } else {
            System.out.println("La nouvelle photo de profil est invalide.");
        }
    }

    public void modifierInfos(String nouvelleBio, LocalDate nouvelleDateNaissance, Sex nouveauSexe) {
        if (nouvelleBio != null) {
            this.bio = nouvelleBio;
        }
        if (nouvelleDateNaissance != null) {
            this.dateNaissance = nouvelleDateNaissance;
        }
        if (nouveauSexe != null) {
            this.sexe = nouveauSexe;
        }
        System.out.println("Informations du profil mises à jour.");
    }

    public String afficherPublic() {
        return "Bio : " + bio + "\n"
                + "Note : " + noteMoyenne + "\n"
                + "Sexe : " + (sexe != null ? sexe.toString() : "Non précisé");
    }
}
