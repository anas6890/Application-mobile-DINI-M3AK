package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dinim3ak.data.repositories.UtilisateurRepository;
import com.dinim3ak.services.user.UtilisateurService;

public class Page14Activity extends AppCompatActivity {
    UtilisateurService utilisateurService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page14);

        utilisateurService = new UtilisateurService(this);
        ((TextView) findViewById(R.id.welcomeText)).setText(utilisateurService.getCurrentUser().getPrenom()+
                ", où souhaite tu voyager?");
        ImageButton userIcon = findViewById(R.id.userIcon);
        userIcon.setOnClickListener(v -> {
            Intent intent = new Intent(Page14Activity.this, Page22Activity.class);
            startActivity(intent);
        });

        Button helpButton=findViewById(R.id.helpButton);
        helpButton.setOnClickListener(v -> Toast.makeText(this, "Fonctionnalité indisponible", Toast.LENGTH_SHORT).show());
    }

    public void on_publier_covoiturage_click(View view){
        Intent i = new Intent(this, Page15Activity.class);
        this.startActivity(i);
    }

    public void goToPassager(View view){
        Intent i = new Intent(this, Page5Activity.class);
        this.startActivity(i);
    }
    public void goToConducteur(View view){

    }
    public void goToCovoiturage(View view){
        Intent i = new Intent(this, Page19Activity.class);
        this.startActivity(i);
    }
    public void goToWallet(View view){
        Intent i = new Intent(this, Page21Activity.class);
        this.startActivity(i);
    }
}