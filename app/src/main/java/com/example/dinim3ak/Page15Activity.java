package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Page15Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page15);

        TextView layoutCasablanca = findViewById(R.id.layoutCasablanca);
        TextView layoutFes = findViewById(R.id.layoutFes);
        TextView layoutSale = findViewById(R.id.layoutSale);
        TextView layoutTanger = findViewById(R.id.layoutTanger);

        layoutCasablanca.setOnClickListener(v -> goToNextPage("Casablanca"));
        layoutFes.setOnClickListener(v -> goToNextPage("Fès"));
        layoutSale.setOnClickListener(v -> goToNextPage("Salé"));
        layoutTanger.setOnClickListener(v -> goToNextPage("Tanger"));
    }

    private void goToNextPage(String villeDepart) {
        Intent intent = new Intent(Page15Activity.this, Page16Activity.class);
        intent.putExtra("ville_depart", villeDepart);
        startActivity(intent);
    }
}
