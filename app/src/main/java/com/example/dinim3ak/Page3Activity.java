package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Page3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page3);

        Button btnContinuer = findViewById(R.id.btnContinuer);         // ID dans page3.xml
        Button btnConnexionGoogle = findViewById(R.id.btnGoogleSignup); // ID aussi dans page3.xml

        btnContinuer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Page3Activity.this, Page5Activity.class);
                startActivity(intent);
            }
        });

        btnConnexionGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code de connexion Google à ajouter plus tard
            }
        });
    }
}

