package com.example.dinim3ak;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Page5Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page5);


        // Initialisation des vues
        ImageView btn_profil = findViewById(R.id.btn_profil);
        EditText btn_rech = findViewById(R.id.btn_rech);
        LinearLayout btn_passager = findViewById(R.id.btn_passager);
        LinearLayout btn_covoiturage = findViewById(R.id.btn_covoiturage);
        LinearLayout btn_conducteur = findViewById(R.id.btn_conducteur);
        LinearLayout btn_wallet = findViewById(R.id.btn_wallet);
        Button btnHelp = findViewById(R.id.btnHelp);
        ImageView notification = findViewById(R.id.notification);
        // Désactiver le clavier pour l'EditText
        btn_rech.setFocusable(false);
        btn_rech.setOnClickListener(v -> {
            // Ouvrir directement l'activité de sélection de ville de départ
            startActivity(new Intent(Page5Activity.this, Page6Activity.class));
        });

        notification.setOnClickListener(v -> Toast.makeText(this, "Fonctionnalité indisponible", Toast.LENGTH_SHORT).show());

        btnHelp.setOnClickListener(v -> Toast.makeText(this, "Fonctionnalité indisponible", Toast.LENGTH_SHORT).show());

        // Initialisation du RecyclerView
        RecyclerView recyclerRecentSearch = findViewById(R.id.recyclerRecentSearch);
        recyclerRecentSearch.setLayoutManager(new LinearLayoutManager(this));


        // Navigation entre pages
        btn_profil.setOnClickListener(v ->
                startActivity(new Intent(Page5Activity.this, Page22Activity.class)));

        btn_passager.setOnClickListener(v ->
                startActivity(new Intent(Page5Activity.this, Page5Activity.class)));

        btn_conducteur.setOnClickListener(v ->
                startActivity(new Intent(Page5Activity.this, Page14Activity.class)));

        btn_covoiturage.setOnClickListener(v ->
                startActivity(new Intent(Page5Activity.this, Page19Activity.class)));

        btn_wallet.setOnClickListener(v ->
                startActivity(new Intent(Page5Activity.this, Page21Activity.class)));
    }

    /** @noinspection deprecation*/
    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit Confirmation")
                .setMessage("Are you sure you want to leave this screen? Any unsaved changes will be lost.")
                .setPositiveButton("Yes", (dialog, which) -> {
                    // Quitter complètement l'application
                    finishAffinity(); // Termine toutes les activités et quitte l'application
                })
                .setNegativeButton("No", (dialog, which) -> {
                    dialog.dismiss(); // Ne rien faire, fermer la boîte de dialogue
                })
                .show();
    }
}


