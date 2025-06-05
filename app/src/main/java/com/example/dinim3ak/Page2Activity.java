package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Page2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2);

        Button btnGoogleSignup = findViewById(R.id.btnGoogleSignup);
        Button btnCreateAccount = findViewById(R.id.btnCreateAccount);
        ImageView backButton = findViewById(R.id.backButton2);

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(Page2Activity.this, Page1Activity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        btnGoogleSignup.setOnClickListener(v -> {
            Toast.makeText(this, "Fonctionnalité indisponible", Toast.LENGTH_SHORT).show();
        });


        btnCreateAccount.setOnClickListener(v -> {
            Intent intent = new Intent(Page2Activity.this, Page4Activity.class);
            startActivity(intent);
        });
    }
}
