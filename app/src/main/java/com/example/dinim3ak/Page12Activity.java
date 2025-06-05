package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Page12Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("confirmation_error", "This is a message to see if this bit gets executed..");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page12);
        Button statut_de_reservation = findViewById(R.id.button);
        statut_de_reservation.setOnClickListener(v -> {
            Intent intent = new Intent(Page12Activity.this, Page19Activity.class);
            startActivity(intent);
        });
        Button retour_a_la_recherche = findViewById(R.id.button3);
        retour_a_la_recherche.setOnClickListener(v -> {
            Intent intent = new Intent(Page12Activity.this, Page5Activity.class);
            startActivity(intent);
        });

    }
}