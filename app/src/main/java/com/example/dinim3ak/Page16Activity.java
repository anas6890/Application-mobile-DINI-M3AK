package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Page16Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page16);

        // Récupère la ville de départ passée depuis Page15
        String villeDepart = getIntent().getStringExtra("ville_depart");

        // Liaison avec les TextViews du layout
        TextView layoutRabat = findViewById(R.id.layoutRabat);
        TextView layoutFes = findViewById(R.id.layoutFes);
        TextView layoutSale = findViewById(R.id.layoutSale);
        TextView layoutTanger = findViewById(R.id.layoutTanger);

        // Écouteur commun
        View.OnClickListener listener = view -> {
            String villeArrivee = ((TextView) view).getText().toString();
            Intent intent = new Intent(Page16Activity.this, Page17Activity.class);
            intent.putExtra("ville_depart", villeDepart);
            intent.putExtra("ville_arrivee", villeArrivee);
            startActivity(intent);
        };

        // Affectation de l'écouteur à chaque ville
        layoutRabat.setOnClickListener(listener);
        layoutFes.setOnClickListener(listener);
        layoutSale.setOnClickListener(listener);
        layoutTanger.setOnClickListener(listener);
    }
}
