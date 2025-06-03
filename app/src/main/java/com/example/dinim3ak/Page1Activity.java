package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Page1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page1);  // Assure-toi que ton fichier XML s'appelle page1.xml

        Button btnConnexion = findViewById(R.id.btnConnexion);
        Button btnInscription = findViewById(R.id.btnInscription);

        btnConnexion.setOnClickListener(v -> {
            Intent intent = new Intent(Page1Activity.this, Page3Activity.class);
            startActivity(intent);
        });

        btnInscription.setOnClickListener(v -> {
            Intent intent = new Intent(Page1Activity.this, Page2Activity.class);
            startActivity(intent);
        });
    }
}

