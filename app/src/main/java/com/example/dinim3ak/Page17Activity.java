package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Page17Activity extends AppCompatActivity {

    private String selectedDate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page17); // Assure-toi que ce layout existe

        Button nextButton;
        nextButton = findViewById(R.id.buttonNext);
        TableLayout calendar = findViewById(R.id.calendarJanuary);
        ImageView backButton = findViewById(R.id.backButton);

        // Parcours du tableau de janvier
        for (int i = 0; i < calendar.getChildCount(); i++) {
            TableRow row = (TableRow) calendar.getChildAt(i);
            for (int j = 0; j < row.getChildCount(); j++) {
                TextView dayView = (TextView) row.getChildAt(j);
                String dayText = dayView.getText().toString();
                if (!dayText.isEmpty()) {
                    dayView.setOnClickListener(v -> {
                        selectedDate = dayText + " janvier 2025";
                        Toast.makeText(this, "Date sélectionnée : " + selectedDate, Toast.LENGTH_SHORT).show();
                    });
                }
            }
        }

        // Clic sur "Suivant"
        nextButton.setOnClickListener(v -> {
            if (selectedDate != null) {
                Intent intent = new Intent(Page17Activity.this, Page18Activity.class);
                intent.putExtra("selectedDate", selectedDate);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Veuillez sélectionner une date", Toast.LENGTH_SHORT).show();
            }
        });

        // Clic sur "Retour"
        backButton.setOnClickListener(v -> finish());
    }
}
