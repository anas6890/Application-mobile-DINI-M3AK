package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dinim3ak.model.Covoiturage;
import com.dinim3ak.services.trip.CovoiturageService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Page18Activity extends AppCompatActivity {

    // Déclaration des éléments de l'interface
    private EditText editHeureDepart;
    private EditText editNombrePlaces;
    private EditText editMarqueVoiture;
    private Button confirmerButton;
    private String selectedDate = null;
    private String villeDepart;
    private String villeDestination;
    private CovoiturageService covoiturageService;
    private EditText price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page18);

        covoiturageService = new CovoiturageService(this);

        initViews();
        setupBackButton();
        setupConfirmerButton();

        villeDepart = getIntent().getStringExtra("villeDepart");
        villeDestination = getIntent().getStringExtra("villeDestination");
        selectedDate = getIntent().getStringExtra("selectedDate");

    }

    private void initViews() {
        editHeureDepart = findViewById(R.id.edit_heure_depart);
        editNombrePlaces = findViewById(R.id.edit_nombre_places);
        editMarqueVoiture = findViewById(R.id.edit_marque_voiture);
        confirmerButton = findViewById(R.id.button_confirmer);
        price = findViewById(R.id.price);
    }

    private void setupBackButton() {
        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Page18Activity.this, Page17Activity.class);
                startActivity(intent);
            }
        });
    }

    private void setupConfirmerButton() {
        confirmerButton.setOnClickListener(v -> {
            String heureDepartStr = editHeureDepart.getText().toString().trim();
            String nombrePlacesStr = editNombrePlaces.getText().toString().trim();
            String marqueVoiture = editMarqueVoiture.getText().toString().trim();
            String prixStr = price.getText().toString().trim();

            if (!validateInputs(heureDepartStr, nombrePlacesStr, marqueVoiture, prixStr)) return;

            try {
                int nombrePlaces = Integer.parseInt(nombrePlacesStr);
                float prix = Float.parseFloat(prixStr);

                // Parsing de l'heure avec gestion d'erreur spécifique
                LocalTime heureDepart;
                try {
                    // Essayer d'abord le format HH:mm
                    heureDepart = LocalTime.parse(heureDepartStr, DateTimeFormatter.ofPattern("HH:mm"));
                } catch (DateTimeParseException e1) {
                    try {
                        // Essayer le format H:mm
                        heureDepart = LocalTime.parse(heureDepartStr, DateTimeFormatter.ofPattern("H:mm"));
                    } catch (DateTimeParseException e2) {
                        Toast.makeText(this, "Format d'heure invalide. Utilisez HH:mm (ex: 14:30)", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                // Parsing de la date avec gestion d'erreur spécifique
                LocalDate date;
                if (selectedDate == null || selectedDate.isEmpty()) {
                    Toast.makeText(this, "Aucune date sélectionnée", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    // Essayer d'abord le format ISO (yyyy-MM-dd)
                    date = LocalDate.parse(selectedDate);
                } catch (DateTimeParseException e1) {
                    try {
                        // Essayer le format dd/MM/yyyy
                        date = LocalDate.parse(selectedDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    } catch (DateTimeParseException e2) {
                        try {
                            // Essayer le format MM/dd/yyyy
                            date = LocalDate.parse(selectedDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                        } catch (DateTimeParseException e3) {
                            Toast.makeText(this, "Format de date invalide: " + selectedDate, Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                }

                LocalTime finalHeureDepart = heureDepart;
                LocalDate finalDate = date;
                new Thread(() -> {
                    try {
                        Covoiturage created = covoiturageService.createCovoiturage(
                                villeDepart, villeDestination, finalDate, finalHeureDepart, nombrePlaces, prix
                        );

                        runOnUiThread(() -> {
                            Toast.makeText(this, "Covoiturage créé avec succès !", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(this, Page14Activity.class));
                            finish();
                        });
                    } catch (Exception e) {
                        runOnUiThread(() -> {
                            Toast.makeText(this, "Erreur lors de la création: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        });
                    }
                }).start();

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Format de nombre invalide pour les places ou le prix", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateInputs(String heureDepart, String nombrePlacesStr, String marqueVoiture, String prixStr) {
        if (heureDepart.isEmpty()) {
            Toast.makeText(this, "Veuillez saisir l'heure de départ (format HH:mm)", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (nombrePlacesStr.isEmpty()) {
            Toast.makeText(this, "Veuillez saisir le nombre de places", Toast.LENGTH_SHORT).show();
            return false;
        }

        try {
            int nombrePlaces = Integer.parseInt(nombrePlacesStr);
            if (nombrePlaces <= 0) {
                Toast.makeText(this, "Le nombre de places doit être supérieur à 0", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Veuillez saisir un nombre valide pour les places", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (marqueVoiture.isEmpty()) {
            Toast.makeText(this, "Veuillez saisir la marque de voiture", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (prixStr.isEmpty()) {
            Toast.makeText(this, "Veuillez saisir le prix", Toast.LENGTH_SHORT).show();
            return false;
        }

        try {
            float prix = Float.parseFloat(prixStr);
            if (prix < 0) {
                Toast.makeText(this, "Le prix doit être positif", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Veuillez saisir un prix valide (ex: 25.50)", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }



    // Méthode utilitaire pour récupérer toutes les données de l'utilisateur
    public Bundle getAllUserData() {
        Bundle data = new Bundle();

        String heureDepart = editHeureDepart.getText().toString().trim();
        String nombrePlacesStr = editNombrePlaces.getText().toString().trim();
        String marqueVoiture = editMarqueVoiture.getText().toString().trim();

        data.putString("heure_depart", heureDepart);
        if (!nombrePlacesStr.isEmpty()) {
            try {
                data.putInt("nombre_places", Integer.parseInt(nombrePlacesStr));
            } catch (NumberFormatException e) {
                data.putInt("nombre_places", 0);
            }
        }
        data.putString("marque_voiture", marqueVoiture);

        return data;
    }
}