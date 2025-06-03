package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Page5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page5);

        ImageView btn_profil = findViewById(R.id.btn_profil);
        EditText btn_rech = findViewById(R.id.btn_rech);
        ImageView btn_passager = findViewById(R.id.btn_passager);
        ImageView btn_covoiturage = findViewById(R.id.btn_covoiturage);
        ImageView btn_conducteur = findViewById(R.id.btn_conducteur);
        ImageView btn_wallet = findViewById(R.id.btn_wallet);


        btn_profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Page5Activity.this, Page22Activity.class);
                startActivity(intent);
            }
        });

        btn_rech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Page5Activity.this, Page6Activity.class);
                startActivity(intent);
            }
        });
        btn_passager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Page5Activity.this, Page5Activity.class);
                startActivity(intent);
            }
        });
        btn_conducteur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Page5Activity.this, Page14Activity.class);
                startActivity(intent);
            }
        });
        btn_covoiturage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Page5Activity.this, Page19Activity.class);
                startActivity(intent);
            }
        });
        btn_wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Page5Activity.this, Page21Activity.class);
                startActivity(intent);
            }
        });
    }
}

