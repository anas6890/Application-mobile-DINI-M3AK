package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Page18Activity extends AppCompatActivity {

    Button confirmerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page18); // Assure-toi que le bon layout est bien utilisé

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Correction ici : le code de l'intention est placé dans onClick
                Intent intent = new Intent(Page18Activity.this, Page9Activity.class);
                startActivity(intent);
            }
        });

        confirmerButton = findViewById(R.id.button_confirmer);
        confirmerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Transition vers la page 14
                Intent intent = new Intent(Page18Activity.this, Page14Activity.class);
                startActivity(intent);
            }
        });
    }
}
