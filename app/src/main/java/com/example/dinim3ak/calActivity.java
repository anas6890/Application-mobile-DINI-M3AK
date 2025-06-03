package com.example.dinim3ak;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class calActivity extends AppCompatActivity {
    Button btnPickDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cal); // ton layout

        btnPickDate = findViewById(R.id.btnPickDate);

        btnPickDate.setOnClickListener(view -> {
            // Obtenir la date actuelle
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // Afficher la boîte de dialogue pour choisir une date
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    calActivity.this,
                    (view1, yearSelected, monthSelected, dayOfMonthSelected) -> {
                        String selectedDate = dayOfMonthSelected + "/" + (monthSelected + 1) + "/" + yearSelected;
                        Toast.makeText(getApplicationContext(), "Date choisie : " + selectedDate, Toast.LENGTH_SHORT).show();
                    },
                    year, month, day
            );
            datePickerDialog.show();
        });
    }
}
