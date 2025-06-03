package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Page2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2);

        Button btnGoogleSignup = findViewById(R.id.btnGoogleSignup);
        Button btnCreateAccount = findViewById(R.id.btnCreateAccount);

        btnGoogleSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // À implémenter plus tard : authentification Google avec Firebase, etc.
                // Exemple temporaire : afficher un message ou rester inactif
            }
        });

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Page2Activity.this, Page4Activity.class);
                startActivity(intent);
            }
        });
    }
}
