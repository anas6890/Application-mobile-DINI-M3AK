package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Page9Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page9);
        Button boutonPage9 = findViewById(R.id.buttonoffre1);

        boutonPage9.setOnClickListener(v -> {
            Intent intent = new Intent(Page9Activity.this, Page10Activity.class);
            startActivity(intent);
        });

    }
}