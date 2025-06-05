package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Page1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page1);

        Button btnConnexion = findViewById(R.id.btnConnexion);
        Button btnInscription = findViewById(R.id.btnInscription);

        btnConnexion.setOnClickListener(v -> {
            Intent intent = new Intent(Page1Activity.this, Page3Activity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        btnInscription.setOnClickListener(v -> {
            Intent intent = new Intent(Page1Activity.this, Page2Activity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        });
    }
}

