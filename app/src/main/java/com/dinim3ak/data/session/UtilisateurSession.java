package com.dinim3ak.data.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.dinim3ak.model.Utilisateur;

import java.time.LocalDate;

public class UtilisateurSession {
    private static UtilisateurSession instance;
    private Utilisateur currentUser;
    private Context context;

    private UtilisateurSession(Context context) {
        this.context = context.getApplicationContext();
    }

    public static UtilisateurSession getInstance(Context context) {
        if (instance == null) {
            instance = new UtilisateurSession(context);
        }
        return instance;
    }

    public void setCurrentUser(Utilisateur user) {
        this.currentUser = user;
        // Optionally save to SharedPreferences for persistence
        saveUserToPreferences(user);
    }

    public Utilisateur getCurrentUser() {
        if (currentUser == null) {
            // Try to load from SharedPreferences
            currentUser = loadUserFromPreferences();
        }
        return currentUser;
    }

    public boolean isLoggedIn() {
        return getCurrentUser() != null;
    }

    public void logout() {
        currentUser = null;
        clearUserPreferences();
    }

    private void saveUserToPreferences(Utilisateur user) {
        SharedPreferences prefs = context.getSharedPreferences("user_session", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong("user_id", user.getId());
        editor.putString("user_email", user.getEmail());
        editor.putString("user_name", user.getNom());
        editor.putString("user_first_name", user.getPrenom());
        editor.putString("user_phone", user.getNumeroTelephone());
        editor.putString("date_inscription", user.getDateInscription().toString());
        // Add other necessary fields
        editor.apply();
    }

    private Utilisateur loadUserFromPreferences() {
        SharedPreferences prefs = context.getSharedPreferences("user_session", Context.MODE_PRIVATE);
        long userId = prefs.getLong("user_id", -1);
        Log.i("SavedUSERID", String.valueOf(userId));
        if (userId != -1) {
            // Create user object from preferences or fetch from database
            Utilisateur user = new Utilisateur();
            user.setId(userId);
            user.setEmail(prefs.getString("user_email", ""));
            user.setNom(prefs.getString("user_name", ""));
            user.setPrenom(prefs.getString("user_first_name", ""));
            user.setDateInscription(LocalDate.parse(prefs.getString("date_inscription", "")));
            user.setNumeroTelephone(prefs.getString("user_phone", ""));
            return user;
        }
        return null;
    }

    private void clearUserPreferences() {
        SharedPreferences prefs = context.getSharedPreferences("user_session", Context.MODE_PRIVATE);
        prefs.edit().clear().apply();
    }
}