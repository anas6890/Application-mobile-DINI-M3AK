package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Page8Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page8);

        ImageView backIcon = findViewById(R.id.backButton8);

        backIcon.setOnClickListener(v -> {
            // Revenir à Page6Activity par exemple
            Intent intent = new Intent(Page8Activity.this, Page7Activity.class);
            startActivity(intent);
            finish(); // facultatif : empêche de revenir à Page7 avec le bouton retour
        });

        Button boutonsauter = findViewById(R.id.button8);

        boutonsauter.setOnClickListener(v -> {
            Intent intent = new Intent(Page8Activity.this, Page9Activity.class);
            startActivity(intent);
        });


    }
}