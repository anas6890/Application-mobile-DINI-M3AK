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

public class Page7Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page7);

        LinearLayout rabatLayout = findViewById(R.id.rabatLayout);
        rabatLayout.setOnClickListener(v -> {
            Intent intent = new Intent(Page7Activity.this, Page8Activity.class);
            startActivity(intent);
        });

        LinearLayout FesLayout2 = findViewById(R.id.Fes2Layout);
        FesLayout2.setOnClickListener(v -> {
            Intent intent = new Intent(Page7Activity.this, Page8Activity.class);
            startActivity(intent);
        });

        LinearLayout SaleLayout2 = findViewById(R.id.Sale2Layout);
        SaleLayout2.setOnClickListener(v -> {
            Intent intent = new Intent(Page7Activity.this, Page8Activity.class);
            startActivity(intent);
        });

        LinearLayout TangerLayout2 = findViewById(R.id.Tanger2Layout);
        TangerLayout2.setOnClickListener(v -> {
            Intent intent = new Intent(Page7Activity.this, Page8Activity.class);
            startActivity(intent);
        });

        EditText editTextVille2 = findViewById(R.id.searchField2);
        ImageView searchIcon2 = findViewById(R.id.searchIcon2);

        // Clic sur l'icône
        searchIcon2.setOnClickListener(v -> {
            lancerRecherche(editTextVille2);
        });

        // Clic sur le bouton OK (Enter) du clavier
        editTextVille2.setOnEditorActionListener((TextView v, int actionId, KeyEvent event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE
                    || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)) {
                lancerRecherche(editTextVille2);
                return true;
            }
            return false;
        });

        ImageView backIcon = findViewById(R.id.backButton7);
        backIcon.setOnClickListener(v -> {
            Intent intent = new Intent(Page7Activity.this, Page6Activity.class);
            startActivity(intent);
            finish();
        });
    }

    // Méthode de lancement de recherche
    private void lancerRecherche(EditText editTextVille2) {
        String ville2 = editTextVille2.getText().toString().trim();

        if (!ville2.isEmpty()) {
            Intent intent = new Intent(Page7Activity.this, Page8Activity.class);
            intent.putExtra("ville", ville2); // facultatif
            startActivity(intent);
        } else {
            Toast.makeText(this, "Veuillez entrer une ville", Toast.LENGTH_SHORT).show();
        }
    }
}
