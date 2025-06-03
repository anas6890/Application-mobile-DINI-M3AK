package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Page7Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page7);
        LinearLayout rabatLayout = findViewById(R.id.rabatLayout);
        rabatLayout.setOnClickListener(v -> {
            Toast.makeText(Page7Activity.this, "Layout cliqué", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Page7Activity.this, Page8Activity.class);
            startActivity(intent);
        });
        LinearLayout FesLayout2 = findViewById(R.id.Fes2Layout);
        FesLayout2.setOnClickListener(v -> {
            Toast.makeText(Page7Activity.this, "Layout cliqué", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Page7Activity.this, Page8Activity.class);
            startActivity(intent);
        });
        LinearLayout SaleLayout2 = findViewById(R.id.Sale2Layout);
        SaleLayout2.setOnClickListener(v -> {
            Toast.makeText(Page7Activity.this, "Layout cliqué", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Page7Activity.this, Page8Activity.class);
            startActivity(intent);
        });
        LinearLayout TangerLayout2 = findViewById(R.id.Tanger2Layout);
        TangerLayout2.setOnClickListener(v -> {
            Toast.makeText(Page7Activity.this, "Layout cliqué", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Page7Activity.this, Page8Activity.class);
            startActivity(intent);
        });

        EditText editTextVille2 = findViewById(R.id.searchField2);
        ImageView searchIcon2 = findViewById(R.id.searchIcon2);

        searchIcon2.setOnClickListener(v -> {
            String ville2 = editTextVille2.getText().toString().trim();

            if (!ville2.isEmpty()) {
                // Tu peux passer la ville à la page suivante si tu veux
                Intent intent = new Intent(Page7Activity.this, Page8Activity.class);
                intent.putExtra("ville", ville2); // facultatif
                startActivity(intent);
            } else {
                Toast.makeText(this, "Veuillez entrer une ville", Toast.LENGTH_SHORT).show();
            }
        });

        ImageView backIcon = findViewById(R.id.backButton7);

        backIcon.setOnClickListener(v -> {
            // Revenir à Page6Activity par exemple
            Intent intent = new Intent(Page7Activity.this, Page6Activity.class);
            startActivity(intent);
            finish(); // facultatif : empêche de revenir à Page7 avec le bouton retour
        });


    }
}