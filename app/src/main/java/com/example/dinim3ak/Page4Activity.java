package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Page4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page4);

        Button btnContinuer = findViewById(R.id.btnContinuer);
        ImageView backButton = findViewById(R.id.backButton4);

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(Page4Activity.this, Page2Activity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        btnContinuer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Page4Activity.this, Page5Activity.class);
                startActivity(intent);
            }
        });

    }
}

