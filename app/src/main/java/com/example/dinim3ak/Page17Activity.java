package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Page17Activity extends AppCompatActivity {

    private String selectedDate = null;
    private String villeDepart;
    private String villeDestination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page17);

        try {
            // Récupérer les deux villes depuis l'Intent
            villeDepart = getIntent().getStringExtra("villeDepart");
            villeDestination = getIntent().getStringExtra("villeDestination");

            // Valeurs par défaut si aucune ville n'est passée
            if (villeDepart == null) villeDepart = "";
            if (villeDestination == null) villeDestination = "";

            // Afficher les villes dans l'interface
            displayTrajet();

            // Initialiser les autres composants
            setupComponents();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Erreur lors de l'initialisation", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayTrajet() {
        // Afficher le trajet complet (ajustez l'ID selon votre layout)
        TextView trajetTextView = findViewById(R.id.trajetTextView);
        if (trajetTextView != null && !villeDepart.isEmpty() && !villeDestination.isEmpty()) {
            trajetTextView.setText( villeDepart + " -> " + villeDestination);
        }
    }

    private void setupComponents() {
        Button nextButton = findViewById(R.id.buttonNext);
        ImageView backButton = findViewById(R.id.backButton);
        Button selectDate = findViewById(R.id.selectDate);

        // Sélection de date
        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Quand souhaites-tu partir ?")
                        .build();

                // Show the picker
                datePicker.show(getSupportFragmentManager(), "DATE_PICKER");

                // Handle the result
                datePicker.addOnPositiveButtonClickListener(selection -> {
                    // Format pour l'affichage à l'utilisateur
                    String displayDate = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                            .format(new Date(selection));

                    // Format ISO pour le parsing (yyyy-MM-dd)
                    selectedDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                            .format(new Date(selection));

                    Toast.makeText(Page17Activity.this, "Date sélectionnée : " + displayDate, Toast.LENGTH_SHORT).show();
                });
            }
        });

        // Clic sur "Suivant"
        nextButton.setOnClickListener(v -> {
            if (selectedDate != null) {
                Intent intent = new Intent(Page17Activity.this, Page18Activity.class);

                // Passer toutes les informations vers Page18
                intent.putExtra("villeDepart", villeDepart);
                intent.putExtra("villeDestination", villeDestination);
                intent.putExtra("selectedDate", selectedDate);

                startActivity(intent);
            } else {
                Toast.makeText(this, "Veuillez sélectionner une date", Toast.LENGTH_SHORT).show();
            }
        });

        // Clic sur "Retour"
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(Page17Activity.this, Page16Activity.class);
            startActivity(intent);
        });
    }
}