package com.example.dinim3ak;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Page5Activity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page5);


        // Initialisation des vues
        ImageView btn_profil = findViewById(R.id.btn_profil);
        EditText btn_rech = findViewById(R.id.btn_rech);
        ImageView btn_passager = findViewById(R.id.btn_passager);
        ImageView btn_covoiturage = findViewById(R.id.btn_covoiturage);
        ImageView btn_conducteur = findViewById(R.id.btn_conducteur);
        ImageView btn_wallet = findViewById(R.id.btn_wallet);

        // Désactiver le clavier pour l'EditText
        btn_rech.setFocusable(false);
        btn_rech.setOnClickListener(v -> {
            // Ouvrir directement l'activité de sélection de ville de départ
            startActivity(new Intent(Page5Activity.this, Page6Activity.class));
        });

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
    @Override
    public void onBackPressed() {
        // Ne rien faire, bloque le bouton retour
    }

}