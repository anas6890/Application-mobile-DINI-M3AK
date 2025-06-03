package com.example.dinim3ak;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Page6Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page6);
        LinearLayout casablancaLayout1 = findViewById(R.id.casablanca1Layout);
        casablancaLayout1.setOnClickListener(v -> {
            Toast.makeText(Page6Activity.this, "Layout cliqué", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), Page7Activity.class);
            startActivity(intent);
        });
        LinearLayout FesLayout1 = findViewById(R.id.Fes1Layout);
        FesLayout1.setOnClickListener(v -> {
            Toast.makeText(Page6Activity.this, "Layout cliqué", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), Page7Activity.class);
            startActivity(intent);
        });
        LinearLayout SaleLayout1 = findViewById(R.id.Sale1Layout);
        SaleLayout1.setOnClickListener(v -> {
            Toast.makeText(Page6Activity.this, "Layout cliqué", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), Page7Activity.class);
            startActivity(intent);
        });
        LinearLayout TangerLayout1 = findViewById(R.id.Tanger1Layout);
        TangerLayout1.setOnClickListener(v -> {
            Toast.makeText(Page6Activity.this, "Layout cliqué", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), Page7Activity.class);
            startActivity(intent);
        });

        EditText editTextVille1 = findViewById(R.id.searchField1);
        ImageView searchIcon1 = findViewById(R.id.searchIcon1);

        searchIcon1.setOnClickListener(v -> {
            String ville1 = editTextVille1.getText().toString().trim();

            if (!ville1.isEmpty()) {
                // Tu peux passer la ville à la page suivante si tu veux
                Intent intent = new Intent(Page6Activity.this, Page7Activity.class);
                intent.putExtra("ville", ville1); // facultatif
                startActivity(intent);
            } else {
                Toast.makeText(this, "Veuillez entrer une ville", Toast.LENGTH_SHORT).show();
            }
        });


    }
}