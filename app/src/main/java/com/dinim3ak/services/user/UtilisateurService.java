package com.dinim3ak.services.user;

import android.content.Context;
import android.util.Base64;

import com.dinim3ak.data.repositories.UtilisateurRepository;
import com.dinim3ak.data.session.UtilisateurSession;
import com.dinim3ak.model.Sex;
import com.dinim3ak.model.Utilisateur;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurSession utilisateurSession;

    public UtilisateurService(Context context) {
        utilisateurRepository = new UtilisateurRepository(context);
        utilisateurSession = UtilisateurSession.getInstance(context);
    }

    public boolean loginUser(String email, String password) {
        Utilisateur user = utilisateurRepository.findByEmail(email);
        if (user != null && validatePassword(password, user.getMotDePasse())) {
            utilisateurSession.setCurrentUser(user);
            return true;
        }
        return false;
    }

    public boolean registerUser(String nom, String prenom, LocalDate dateNaissance, String email, String password, Sex sex, String tel) {
        if (isEmailValid(email)) {
            if (utilisateurRepository.findByEmail(email) != null) return false;

            Utilisateur newUser = new Utilisateur();
            newUser.setNom(nom);
            newUser.setPrenom(prenom);
            newUser.setEmail(email);
            newUser.setMotDePasse(hashPassword(password));
            newUser.setDateNaissance(dateNaissance);
            newUser.setSexe(sex);
            newUser.setNumeroTelephone(tel);
            newUser.setDateInscription(LocalDate.now()); // Remplace new Date()

            utilisateurRepository.insert(newUser);

            // Auto-login after registration
            utilisateurSession.setCurrentUser(newUser);
            return true;
        }
        return false;
    }

    public boolean isLoggedIn() {
        return utilisateurSession.isLoggedIn();
    }

    public void logout() {
        utilisateurSession.logout();
    }

    public Utilisateur getCurrentUser() {
        return utilisateurSession.getCurrentUser();
    }

    public boolean validatePassword(String password, String userPassword) {
        return hashPassword(password).equals(userPassword);
    }

    public static boolean isEmailValid(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String hashPassword(String password) {
        if (password == null || password.isEmpty()) {
            return null;
        }

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.encodeToString(hash, Base64.NO_WRAP);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("SHA-256 algorithm not found: " + e.getMessage());
            return null;
        }
    }
}
