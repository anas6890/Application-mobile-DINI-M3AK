package com.dinim3ak.services.user;

import android.content.Context;
import android.util.Base64;

import com.dinim3ak.data.repositories.UtilisateurRepository;
import com.dinim3ak.data.session.UtilisateurSession;
import com.dinim3ak.model.Utilisateur;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        return hashPassword(password).equals(userPassword);
    }

    public static boolean isEmailValid(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        // Regex for a common email pattern.
        // It allows letters, digits, dots, underscores, percents, plus, and hyphens before the @.
        // It requires at least one character, followed by @, then domain name (letters, digits, hyphens)
        // and finally a dot followed by 2 to 6 characters for the TLD.
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
            // Get an instance of the SHA-256 MessageDigest
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Compute the hash
            byte[] hash = digest.digest(password.getBytes("UTF-8"));

            // Encode the hash bytes to a Base64 string for easier storage and display.
            // Using android.util.Base64 for API Level 24 compatibility.
            // NO_WRAP flag ensures the output is not wrapped with newline characters.
            return Base64.encodeToString(hash, Base64.NO_WRAP);

        } catch (NoSuchAlgorithmException e) {
            System.err.println("SHA-256 algorithm not found: " + e.getMessage());
            // Log the exception properly in a real application
            return null;
        } catch (java.io.UnsupportedEncodingException e) {
            System.err.println("UTF-8 encoding not supported: " + e.getMessage());
            // Log the exception properly in a real application
            return null;
        }
    }
}