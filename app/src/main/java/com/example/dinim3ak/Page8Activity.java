package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Page8Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newpage8);

        Button selectDate = findViewById(R.id.selectDate);
        selectDate.setOnClickListener(v -> {
            MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Quand souhaites-tu partir ?")
                    .build();

            // Show the picker
            datePicker.show(getSupportFragmentManager(), "DATE_PICKER");

            // Handle the result
            datePicker.addOnPositiveButtonClickListener(selection -> {
                String selectedDate = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                        .format(new Date(selection));

                Intent i = new Intent(Page8Activity.this, Page9Activity.class);
                startActivity(i);
            });
        });



        ImageView backIcon = findViewById(R.id.backButton8);

        backIcon.setOnClickListener(v -> {
            // Revenir à Page6Activity par exemple
            Intent intent = new Intent(Page8Activity.this, Page7Activity.class);
            startActivity(intent);
            finish(); // facultatif : empêche de revenir à Page7 avec le bouton retour
        });

        Button boutonsauter = findViewById(R.id.button8);

        boutonsauter.setOnClickListener(v -> {
            Intent intent = new Intent(Page8Activity.this, Page9Activity.class);
            startActivity(intent);
        });


    }
}