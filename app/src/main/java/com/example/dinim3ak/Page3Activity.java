package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Page3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page3);

        Button btnContinuer = findViewById(R.id.btnContinuer);         // ID dans page3.xml
        Button btnConnexionGoogle = findViewById(R.id.btnGoogleSignup); // ID aussi dans page3.xml
        ImageView backButton = findViewById(R.id.backButton3);

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(Page3Activity.this, Page1Activity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
        btnContinuer.setOnClickListener(v -> {
            Intent intent = new Intent(Page3Activity.this, Page5Activity.class);
            startActivity(intent);
        });

        btnConnexionGoogle.setOnClickListener(v -> {
            Toast.makeText(this, "Fonctionnalité indisponible", Toast.LENGTH_SHORT).show();
        });
    }
}

