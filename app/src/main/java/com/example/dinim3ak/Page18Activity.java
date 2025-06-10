package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

        // Récupérer les données de l'intent
        villeDepart = getIntent().getStringExtra("villeDepart");
        villeDestination = getIntent().getStringExtra("villeDestination");
        selectedDate = getIntent().getStringExtra("selectedDate");
    }

    private void initViews() {
        editHeureDepart = findViewById(R.id.edit_heure_depart);
        editNombrePlaces = findViewById(R.id.edit_nombre_places);
        confirmerButton = findViewById(R.id.button_confirmer);
        price = findViewById(R.id.price);
        editMarqueVoiture = findViewById(R.id.edit_marque_voiture); // Ajouté si nécessaire
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
            String prixStr = price.getText().toString().trim();

            if (!validateInputs(heureDepartStr, nombrePlacesStr, prixStr)) return;

            createCovoiturageAsync(heureDepartStr, nombrePlacesStr, prixStr);
        });
    }

    private void createCovoiturageAsync(String heureDepartStr, String nombrePlacesStr, String prixStr) {
        try {
            int nombrePlaces = Integer.parseInt(nombrePlacesStr);
            float prix = Float.parseFloat(prixStr);

            // Parsing de l'heure
            LocalTime heureDepart = parseTime(heureDepartStr);
            if (heureDepart == null) return;

            // Parsing de la date
            LocalDate date = parseDate(selectedDate);
            if (date == null) return;

            // Créer le covoiturage en arrière-plan
            new Thread(() -> {
                try {
                    Covoiturage created = covoiturageService.createCovoiturage(
                            villeDepart, villeDestination, date, heureDepart, nombrePlaces, prix
                    );

                    runOnUiThread(() -> {
                        if (created != null) {
                            Toast.makeText(this, "Covoiturage créé avec succès !", Toast.LENGTH_SHORT).show();

                            // Rediriger vers Page20Activity pour voir les offres
                            Intent intent = new Intent(this, Page20Activity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(this, "Erreur lors de la création du covoiturage", Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (IllegalStateException e) {
                    runOnUiThread(() -> {
                        Toast.makeText(this, "Vous devez être connecté pour créer un covoiturage", Toast.LENGTH_LONG).show();
                        // Rediriger vers la page de connexion si nécessaire
                        redirectToLogin();
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
    }

    private LocalTime parseTime(String timeStr) {
        try {
            // Essayer d'abord le format HH:mm
            return LocalTime.parse(timeStr, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (DateTimeParseException e1) {
            try {
                // Essayer le format H:mm
                return LocalTime.parse(timeStr, DateTimeFormatter.ofPattern("H:mm"));
            } catch (DateTimeParseException e2) {
                Toast.makeText(this, "Format d'heure invalide. Utilisez HH:mm (ex: 14:30)", Toast.LENGTH_SHORT).show();
                return null;
            }
        }
    }

    private LocalDate parseDate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            Toast.makeText(this, "Aucune date sélectionnée", Toast.LENGTH_SHORT).show();
            return null;
        }

        try {
            // Essayer d'abord le format ISO (yyyy-MM-dd)
            return LocalDate.parse(dateStr);
        } catch (DateTimeParseException e1) {
            try {
                // Essayer le format dd/MM/yyyy
                return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (DateTimeParseException e2) {
                try {
                    // Essayer le format MM/dd/yyyy
                    return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                } catch (DateTimeParseException e3) {
                    Toast.makeText(this, "Format de date invalide: " + dateStr, Toast.LENGTH_SHORT).show();
                    return null;
                }
            }
        }
    }

    private void redirectToLogin() {
        // Rediriger vers l'activité de connexion
        // Intent intent = new Intent(this, LoginActivity.class);
        // startActivity(intent);
        // finish();
    }

    private boolean validateInputs(String heureDepart, String nombrePlacesStr, String prixStr) {
        // Validation de l'heure
        if (heureDepart.isEmpty()) {
            Toast.makeText(this, "Veuillez saisir l'heure de départ (format HH:mm)", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validation du nombre de places
        if (nombrePlacesStr.isEmpty()) {
            Toast.makeText(this, "Veuillez saisir le nombre de places", Toast.LENGTH_SHORT).show();
            return false;
        }

        try {
            int nombrePlaces = Integer.parseInt(nombrePlacesStr);
            if (nombrePlaces <= 0 || nombrePlaces > 8) {
                Toast.makeText(this, "Le nombre de places doit être entre 1 et 8", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Veuillez saisir un nombre valide pour les places", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validation du prix
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
            if (prix > 1000) {
                Toast.makeText(this, "Le prix semble trop élevé", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Veuillez saisir un prix valide (ex: 25.50)", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validation des villes
        if (villeDepart == null || villeDestination == null) {
            Toast.makeText(this, "Informations de trajet manquantes", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validation de la date
        if (selectedDate == null || selectedDate.isEmpty()) {
            Toast.makeText(this, "Veuillez sélectionner une date", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    // Méthode utilitaire pour récupérer toutes les données de l'utilisateur
    public Bundle getAllUserData() {
        Bundle data = new Bundle();

        String heureDepart = editHeureDepart.getText().toString().trim();
        String nombrePlacesStr = editNombrePlaces.getText().toString().trim();
        String marqueVoiture = editMarqueVoiture != null ? editMarqueVoiture.getText().toString().trim() : "";
        String prixStr = price.getText().toString().trim();

        data.putString("heure_depart", heureDepart);
        data.putString("prix", prixStr);

        if (!nombrePlacesStr.isEmpty()) {
            try {
                data.putInt("nombre_places", Integer.parseInt(nombrePlacesStr));
            } catch (NumberFormatException e) {
                data.putInt("nombre_places", 0);
            }
        }

        data.putString("marque_voiture", marqueVoiture);
        data.putString("ville_depart", villeDepart);
        data.putString("ville_destination", villeDestination);
        data.putString("selected_date", selectedDate);

        return data;
    }
}