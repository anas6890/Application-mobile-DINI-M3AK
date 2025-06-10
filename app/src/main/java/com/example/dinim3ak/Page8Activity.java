package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

public class Page8Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page8);

        Button selectDate = findViewById(R.id.selectDate);
        selectDate.setOnClickListener(v -> {
            MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Quand souhaites-tu partir ?")
                    .build();

            // Show the picker
            datePicker.show(getSupportFragmentManager(), "DATE_PICKER");

            // Handle the result
            datePicker.addOnPositiveButtonClickListener(selection -> {
                //String selectedDate = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date(selection));
                LocalDate selectedDate = Instant.ofEpochMilli(selection)
                        .atZone(ZoneId.systemDefault()) // Use the device's default time zone
                        .toLocalDate();

                Intent startingIntent = getIntent();
                String ville1 = "", ville2 = "";
                // Check if the intent has extras before trying to retrieve them
                if (startingIntent != null && startingIntent.getExtras() != null) {
                    ville1 = startingIntent.getStringExtra("ville1");
                    ville2 = startingIntent.getStringExtra("ville2");
                } else {
                    Toast.makeText(this, "No data received", Toast.LENGTH_SHORT).show();
                }
                Intent i = new Intent(Page8Activity.this, Page9Activity.class);
                i.putExtra("ville1", ville1);
                i.putExtra("ville2", ville2);
                i.putExtra("date", selectedDate.toString());
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
            Intent startingIntent = getIntent();
            String ville1 = "", ville2 = "";
            // Check if the intent has extras before trying to retrieve them
            if (startingIntent != null && startingIntent.getExtras() != null) {
                ville1 = startingIntent.getStringExtra("ville1");
                ville2 = startingIntent.getStringExtra("ville2");
            } else {
                Toast.makeText(this, "No data received", Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(Page8Activity.this, Page9Activity.class);
            intent.putExtra("ville1", ville1);
            intent.putExtra("ville2", ville2);
            startActivity(intent);
        });


    }
}