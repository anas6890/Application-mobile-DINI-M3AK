package com.example.dinim3ak;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Page4Activity extends AppCompatActivity {

    private EditText dateEditText;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page4);

        Button btnContinuer = findViewById(R.id.btnContinuer);
        ImageView backButton = findViewById(R.id.backButton4);

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(Page4Activity.this, Page2Activity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        btnContinuer.setOnClickListener(v -> {
            Intent intent = new Intent(Page4Activity.this, Page5Activity.class);
            startActivity(intent);
        });

        dateEditText = findViewById(R.id.dateEditText);
        calendar = Calendar.getInstance();

        // Désactive le clavier pour ce champ (on ne veut pas saisie manuelle)
        dateEditText.setShowSoftInputOnFocus(false);

        dateEditText.setOnClickListener(v -> showDatePicker());

        // Si tu veux que ça marche aussi au focus (ex: via navigation clavier)
        dateEditText.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                showDatePicker();
            }
        });
    }

    private void showDatePicker() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog pickerDialog = new DatePickerDialog(
                this,
                (DatePicker view, int selectedYear, int selectedMonth, int selectedDay) -> {
                    // Met à jour la date choisie dans l'EditText au format jj/MM/aaaa
                    String dateStr = String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear);
                    dateEditText.setText(dateStr);

                    // Met à jour le calendrier interne
                    calendar.set(selectedYear, selectedMonth, selectedDay);
                },
                year,
                month,
                day);

        pickerDialog.show();
    }
}
