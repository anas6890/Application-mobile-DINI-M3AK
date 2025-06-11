package com.dinim3ak.services.user;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.dinim3ak.data.repositories.UtilisateurRepository;
import com.dinim3ak.data.session.UtilisateurSession;
import com.dinim3ak.model.Sex;
import com.dinim3ak.model.Utilisateur;
import com.dinim3ak.services.Callback;

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

    public boolean isLoginTel(String login){
        for(char c : login.toCharArray()){
            if(!Character.isDigit(c)) return false;
        }
        return true;
    }
    public void loginUser(LifecycleOwner lifecycleOwner, String login, String password, Callback<Boolean> callback) {
        if(isLoginTel(login)){
            utilisateurRepository.findByTel(login).observe(lifecycleOwner, (user) ->{
                if (user != null && validatePassword(password, user.getMotDePasse())) {
                    utilisateurSession.setCurrentUser(user);
                    callback.onResult(true);
                }
                else{callback.onResult(false);}
            });
        }else{
            utilisateurRepository.findByEmail(login).observe(lifecycleOwner, (user) ->{
                if (user != null && validatePassword(password, user.getMotDePasse())) {
                    utilisateurSession.setCurrentUser(user);
                    callback.onResult(true);
                }
                else{callback.onResult(false);}
            });
        }
    }

    public void registerUser(LifecycleOwner lifecycleOwner, String nom, String prenom, LocalDate dateNaissance, String email,
                                String password, Sex sex, String tel, Callback<Boolean> callback) {

        utilisateurRepository.findByEmail(email).observe(lifecycleOwner, utilisateur_ -> {
            if(utilisateur_ != null) callback.onResult(false);
            else {
                utilisateurRepository.findByTel(tel).observe(lifecycleOwner, utilisateur -> {
                    if(utilisateur != null) callback.onResult(false);
                    else{
                        if (isEmailValid(email)) {
                            Utilisateur newUser = new Utilisateur();
                            newUser.setNom(nom);
                            newUser.setPrenom(prenom);
                            newUser.setEmail(email);
                            newUser.setMotDePasse(hashPassword(password));
                            newUser.setDateNaissance(dateNaissance);
                            newUser.setSexe(sex);
                            newUser.setNumeroTelephone(tel);
                            newUser.setDateInscription(LocalDate.now());

                            utilisateurRepository.insert(newUser, userId -> {
                                newUser.setId(userId);
                                Log.i("USERID", String.valueOf(userId));
                                utilisateurSession.setCurrentUser(newUser);
                                callback.onResult(true);
                            });
                        }else{
                            callback.onResult(false);
                        }
                    }
                });

            }
        });
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
