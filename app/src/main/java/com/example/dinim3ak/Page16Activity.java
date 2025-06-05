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

public class Page16Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page16);

        LinearLayout casablancaLayout1 = findViewById(R.id.rabatLayout);
        casablancaLayout1.setOnClickListener(v -> {
            Intent intent = new Intent(Page16Activity.this, Page17Activity.class);
            startActivity(intent);
        });

        LinearLayout FesLayout1 = findViewById(R.id.Fes2Layout);
        FesLayout1.setOnClickListener(v -> {
            Intent intent = new Intent(Page16Activity.this, Page17Activity.class);
            startActivity(intent);
        });

        LinearLayout SaleLayout1 = findViewById(R.id.Sale2Layout);
        SaleLayout1.setOnClickListener(v -> {
            Intent intent = new Intent(Page16Activity.this, Page17Activity.class);
            startActivity(intent);
        });

        LinearLayout TangerLayout1 = findViewById(R.id.Tanger2Layout);
        TangerLayout1.setOnClickListener(v -> {
            Intent intent = new Intent(Page16Activity.this, Page17Activity.class);
            startActivity(intent);
        });

        ImageView backButton = findViewById(R.id.backButton7);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(Page16Activity.this, Page15Activity.class);
            startActivity(intent);
        });

        EditText editTextVille1 = findViewById(R.id.searchField2);
        ImageView searchIcon1 = findViewById(R.id.searchIcon2);

        // Clic sur l’icône
        searchIcon1.setOnClickListener(v -> lancerRecherche(editTextVille1));

        // Clic sur le bouton OK (Enter) du clavier
        editTextVille1.setOnEditorActionListener((TextView v, int actionId, KeyEvent event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE
                    || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)) {
                lancerRecherche(editTextVille1);
                return true;
            }
            return false;
        });
    }

    // Méthode de lancement de la recherche
    private void lancerRecherche(EditText editText) {
        String ville = editText.getText().toString().trim();
        if (!ville.isEmpty()) {
            Intent intent = new Intent(Page16Activity.this, Page17Activity.class);
            intent.putExtra("ville", ville); // facultatif
            startActivity(intent);
        } else {
            Toast.makeText(this, "Veuillez entrer une ville", Toast.LENGTH_SHORT).show();
        }
    }
}
