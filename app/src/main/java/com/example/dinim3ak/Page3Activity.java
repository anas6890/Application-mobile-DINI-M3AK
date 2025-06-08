package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dinim3ak.services.user.UtilisateurService;

public class Page3Activity extends AppCompatActivity {

    private UtilisateurService utilisateurService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page3);

        utilisateurService = new UtilisateurService(this);
        Button btnContinuer = findViewById(R.id.btnContinuer);         // ID dans page3.xml
        Button btnConnexionGoogle = findViewById(R.id.btnGoogleSignup); // ID aussi dans page3.xml
        ImageView backButton = findViewById(R.id.backButton3);

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(Page3Activity.this, Page1Activity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
        btnContinuer.setOnClickListener(v -> {
            String login = ((TextView)findViewById(R.id.email_tel_input)).getText().toString();
            String password = ((TextView)findViewById(R.id.password_input)).getText().toString();
            if(login.isBlank() || password.isBlank()) Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            else{
                utilisateurService.loginUser(this, login, password, isLoggedIn -> {
                    if(!isLoggedIn){
                        Toast.makeText(this, "Email ou mot de passe incorrect!", Toast.LENGTH_SHORT).show();
                    }else{
                        startActivity(new Intent(this, Page5Activity.class));
                    }
                });
            }

        });

        btnConnexionGoogle.setOnClickListener(v -> {
            Toast.makeText(this, "Fonctionnalité indisponible", Toast.LENGTH_SHORT).show();
        });
    }
}

