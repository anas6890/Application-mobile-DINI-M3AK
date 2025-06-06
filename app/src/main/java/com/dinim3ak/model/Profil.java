package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity
public class Profil {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String photoProfil;
    public String bio;
    public float noteMoyenne;
    @TypeConverters(DateConverter.class)
    public Date dateNaissance;
    public Sex sexe;
    @TypeConverters(DateConverter.class)
    public Date membreDepuis;

    public void modifierPhoto() {}
    public void modifierInfos() {}
    public String afficherPublic() { return bio; }
}