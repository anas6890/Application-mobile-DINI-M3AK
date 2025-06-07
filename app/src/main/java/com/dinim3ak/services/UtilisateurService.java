package com.dinim3ak.services;

import android.content.Context;

import com.dinim3ak.data.repositories.UtilisateurRepository;
import com.dinim3ak.data.session.UtilisateurSession;
import com.dinim3ak.model.Utilisateur;

import java.util.Date;

public class UtilisateurService {
    private UtilisateurRepository utilisateurRepository;
    private UtilisateurSession utilisateurSession;

    public UtilisateurService(Context context) {
        utilisateurRepository = new UtilisateurRepository(context);
        utilisateurSession = UtilisateurSession.getInstance(context);
    }

    public boolean loginUser(String email, String password) {
        Utilisateur user = utilisateurRepository.getByEmail(email);
        if (user != null && validatePassword(password, user.getMotDePasse())) {
            utilisateurSession.setCurrentUser(user);
            return true;
        }
        return false;
    }

    public void registerUser(String nom, String email, String password) {
        if (isEmailValid(email)) {
            Utilisateur newUser = new Utilisateur();
            newUser.setNom(nom);
            newUser.setEmail(email);
            newUser.setMotDePasse(hashPassword(password));

            utilisateurRepository.insert(newUser);

            // Auto-login after registration
            utilisateurSession.setCurrentUser(newUser);
        }
    }

    public void logout() {
        utilisateurSession.logout();
    }

    public Utilisateur getCurrentUser() {
        return utilisateurSession.getCurrentUser();
    }

    public boolean validatePassword(String password, String userPassword){
        return password.equals(userPassword);
    }

    public boolean isEmailValid(String email){
        return false;
    }
}