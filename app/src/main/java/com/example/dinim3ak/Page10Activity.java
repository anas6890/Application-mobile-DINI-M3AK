package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Page10Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page10);
        ImageView backbutton10 = findViewById(R.id.backButton10);
        backbutton10.setOnClickListener(v -> {
            Intent intent = new Intent(Page10Activity.this, Page9Activity.class);
            startActivity(intent);
        });
    }
}