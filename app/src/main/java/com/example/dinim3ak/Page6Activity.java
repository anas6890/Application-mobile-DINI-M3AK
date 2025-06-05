package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Page6Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page6);

        LinearLayout casablancaLayout1 = findViewById(R.id.casablanca1Layout);
        casablancaLayout1.setOnClickListener(v -> {
            Intent intent = new Intent(Page6Activity.this, Page7Activity.class);
            startActivity(intent);
        });

        LinearLayout FesLayout1 = findViewById(R.id.Fes1Layout);
        FesLayout1.setOnClickListener(v -> {
            Intent intent = new Intent(Page6Activity.this, Page7Activity.class);
            startActivity(intent);
        });

        LinearLayout SaleLayout1 = findViewById(R.id.Sale1Layout);
        SaleLayout1.setOnClickListener(v -> {
            Intent intent = new Intent(Page6Activity.this, Page7Activity.class);
            startActivity(intent);
        });

        LinearLayout TangerLayout1 = findViewById(R.id.Tanger1Layout);
        TangerLayout1.setOnClickListener(v -> {
            Intent intent = new Intent(Page6Activity.this, Page7Activity.class);
            startActivity(intent);
        });

        ImageView backButton = findViewById(R.id.backButton6);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(Page6Activity.this, Page5Activity.class);
            startActivity(intent);
        });

        EditText editTextVille1 = findViewById(R.id.searchField1);
        ImageView searchIcon1 = findViewById(R.id.searchIcon1);

        // Clic sur l'icône OK
        searchIcon1.setOnClickListener(v -> {
            lancerRecherche(editTextVille1);
        });

        // Clic sur la touche OK du clavier
        editTextVille1.setOnEditorActionListener((TextView v, int actionId, KeyEvent event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE
                    || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)) {
                lancerRecherche(editTextVille1);
                return true;
            }
            return false;
        });
    }

    // Méthode pour lancer la recherche et passer à la page suivante
    private void lancerRecherche(EditText editTextVille1) {
        String ville1 = editTextVille1.getText().toString().trim();

        if (!ville1.isEmpty()) {
            Intent intent = new Intent(Page6Activity.this, Page7Activity.class);
            intent.putExtra("ville", ville1); // facultatif, si tu veux transmettre la ville
            startActivity(intent);
        } else {
            Toast.makeText(this, "Veuillez entrer une ville", Toast.LENGTH_SHORT).show();
        }
    }
}
