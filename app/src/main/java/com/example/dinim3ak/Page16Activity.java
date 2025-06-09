package com.example.dinim3ak;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Page16Activity extends AppCompatActivity {

    private EditText searchField;
    private TextView suggestedCities;
    private LinearLayout citiesList;
    private String villeDepartReceived; // Ville reçue de Page15

    // Liste simplifiée des villes du Maroc
    private List<String> villesMaroc = Arrays.asList(
            "Casablanca", "Rabat", "Fes", "Marrakech", "Agadir", "Tanger", "Meknes",
            "Oujda", "Kenitra", "Tetouan", "Safi", "Mohammedia", "Khouribga",
            "Beni Mellal", "El Jadida", "Nador", "Taza", "Settat", "Berrechid",
            "Khemisset", "Inezgane", "Ksar El Kebir", "Larache", "Guelmim", "Berkane",
            "Sale", "Essaouira", "Chefchaouen", "Al Hoceima", "Taroudant", "Ouarzazate"
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page16);

        try {
            // Récupérer la ville de départ depuis l'Intent
            villeDepartReceived = getIntent().getStringExtra("ville");
            if (villeDepartReceived == null) {
                villeDepartReceived = ""; // Valeur par défaut si aucune ville n'est passée
            }

            initializeViews();
            setupClickListeners();
            setupSearchFunctionality();
            displayVilleDepart(); // Afficher la ville de départ
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Erreur lors de l'initialisation", Toast.LENGTH_SHORT).show();
        }
    }

    private void initializeViews() {
        searchField = findViewById(R.id.searchField2);
        suggestedCities = findViewById(R.id.suggestedCities); // Ajustez l'ID selon votre layout
        citiesList = findViewById(R.id.citiesList); // Ajustez l'ID selon votre layout
    }

    @SuppressLint("SetTextI18n")
    private void displayVilleDepart() {
        // Afficher la ville de départ dans le titre ou dans un TextView dédié
        TextView villeDepartTextView = findViewById(R.id.searchField1); // Ajustez l'ID selon votre layout
        if (villeDepartTextView != null && !villeDepartReceived.isEmpty()) {
            villeDepartTextView.setText("De: " + villeDepartReceived);
        }
    }

    private void setupClickListeners() {
        // Boutons des villes existantes
        findViewById(R.id.rabatLayout).setOnClickListener(v -> navigateToPage17("Rabat"));
        findViewById(R.id.Fes2Layout).setOnClickListener(v -> navigateToPage17("Fes"));
        findViewById(R.id.Sale2Layout).setOnClickListener(v -> navigateToPage17("Sale"));
        findViewById(R.id.Tanger2Layout).setOnClickListener(v -> navigateToPage17("Tanger"));

        // Bouton retour
        findViewById(R.id.backButton7).setOnClickListener(v -> {
            Intent intent = new Intent(Page16Activity.this,Page15Activity.class);
            startActivity(intent);
        });

        // Icône de recherche
        findViewById(R.id.searchIcon2).setOnClickListener(v -> lancerRecherche(searchField));
    }

    private void setupSearchFunctionality() {
        if (searchField == null) return;

        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString().trim();
                if (query.length() > 0) {
                    updateCitiesList(query);
                } else {
                    resetCitiesList();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        searchField.setOnEditorActionListener((TextView v, int actionId, KeyEvent event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE
                    || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                lancerRecherche(searchField);
                return true;
            }
            return false;
        });
    }

    private void updateCitiesList(String query) {
        if (citiesList == null || suggestedCities == null) return;

        try {
            // Filtrer les villes qui commencent par la requête ET qui ne sont pas la ville de départ
            List<String> suggestions = new ArrayList<>();
            String queryLower = query.toLowerCase();

            for (String ville : villesMaroc) {
                if (ville.toLowerCase().startsWith(queryLower) &&
                        !ville.equalsIgnoreCase(villeDepartReceived)) {
                    suggestions.add(ville);
                }
            }

            // Mettre à jour le titre
            if (suggestions.isEmpty()) {
                if (query.equalsIgnoreCase(villeDepartReceived)) {
                    suggestedCities.setText("Vous ne pouvez pas choisir la même ville de départ");
                } else {
                    suggestedCities.setText("Aucune ville trouvée");
                }
            } else {
                suggestedCities.setText("Villes trouvées (" + suggestions.size() + ")");
            }

            // Cacher les villes par défaut et afficher les suggestions
            citiesList.removeAllViews();

            // Ajouter les suggestions (max 6)
            int maxSuggestions = Math.min(suggestions.size(), 6);
            for (int i = 0; i < maxSuggestions; i++) {
                String ville = suggestions.get(i);
                LinearLayout suggestionLayout = createSimpleSuggestionLayout(ville);
                citiesList.addView(suggestionLayout);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void resetCitiesList() {
        if (suggestedCities == null || citiesList == null) return;

        try {
            suggestedCities.setText("Villes suggérées");

            // Remettre les villes par défaut
            citiesList.removeAllViews();

            // Recréer les layouts des villes par défaut
            String[] defaultCities = {"Rabat", "Fes", "Sale", "Tanger"};
            for (String ville : defaultCities) {
                LinearLayout cityLayout = createSimpleSuggestionLayout(ville);
                citiesList.addView(cityLayout);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private LinearLayout createSimpleSuggestionLayout(String ville) {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setPadding(24, 24, 24, 24);

        // TextView pour le nom de la ville
        TextView textView = new TextView(this);
        textView.setText(ville);
        textView.setTextSize(16);
        textView.setTextColor(0xFF000000);
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));

        // Flèche
        TextView chevron = new TextView(this);
        chevron.setText(">");
        chevron.setTextSize(20);
        chevron.setTextColor(0xFF000000);

        layout.addView(textView);
        layout.addView(chevron);

        // Click listener
        layout.setOnClickListener(v -> {
            searchField.setText(ville);
            navigateToPage17(ville);
        });

        return layout;
    }

    private void navigateToPage17(String villeDestination) {
        try {
            Intent intent = new Intent(Page16Activity.this, Page17Activity.class);

            // Envoyer la ville de départ (reçue de Page15)
            intent.putExtra("villeDepart", villeDepartReceived);

            // Envoyer la ville de destination (sélectionnée dans Page16)
            intent.putExtra("villeDestination", villeDestination);

            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Erreur de navigation", Toast.LENGTH_SHORT).show();
        }
    }


    private void lancerRecherche(EditText editText) {
        if (editText == null) return;

        String ville = editText.getText().toString().trim();
        if (!ville.isEmpty()) {
            navigateToPage17(ville);
        } else {
            Toast.makeText(this, "Veuillez entrer une ville", Toast.LENGTH_SHORT).show();
        }
    }
}