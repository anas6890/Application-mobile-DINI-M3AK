package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Page11Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page11);
        ImageView back_arrow = findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(v -> {
            Intent intent = new Intent(Page11Activity.this, Page10Activity.class);
            startActivity(intent);
        });

        Button confirm_button = findViewById(R.id.confirm_button);
        confirm_button.setOnClickListener(v -> {
            Intent intent = new Intent(Page11Activity.this, Page12Activity.class);
            startActivity(intent);
        });

    }
}