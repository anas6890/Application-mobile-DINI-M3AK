package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Page10Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page10);
        Intent startingintent = getIntent();

        TextView villeDepartTV = ((TextView) findViewById(R.id.villeDepart));
        TextView villeArriveeTV = ((TextView) findViewById(R.id.villeArrivee));
        TextView heureDepartTV = ((TextView) findViewById(R.id.heureDepart));
        TextView heureArriveeTV = ((TextView) findViewById(R.id.heureArrivee));
        TextView placesRestantesTV = ((TextView) findViewById(R.id.placesRestants));
        TextView placesTotalesTV = ((TextView) findViewById(R.id.placesTotales));
        TextView prixUnitaireTV = ((TextView) findViewById(R.id.prixPourUnPassager));
        LinearLayout user = ((LinearLayout) findViewById(R.id.user));

        if (startingintent != null) {
            OffreItem receivedOffer;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                receivedOffer = startingintent.getParcelableExtra("offre_data", OffreItem.class);
            } else {
                // For API < 33 (most common current use)
                receivedOffer = startingintent.getParcelableExtra("offre_data");
            }
            assert receivedOffer != null;
            villeDepartTV.setText(receivedOffer.getFromCity());
            heureDepartTV.setText(receivedOffer.getFromTime());
            villeArriveeTV.setText(receivedOffer.getToCity());
            heureArriveeTV.setText(receivedOffer.getToTime());
            prixUnitaireTV.setText(receivedOffer.getPrice());
            placesTotalesTV.setText(receivedOffer.getNbPassager());
            placesRestantesTV.setText(receivedOffer.getNbPassagerRestant());
        }
        ImageView backbutton10 = findViewById(R.id.backButton10);
        backbutton10.setOnClickListener(v -> {
            finish();
        });
        Button reserver10 = findViewById(R.id.btnreserver10);
        reserver10.setOnClickListener(v -> {
            Intent intent = new Intent(Page10Activity.this, Page11Activity.class);
            Intent startingIntent = getIntent();
            if (startingintent != null) {
                OffreItem receivedOffer;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                    receivedOffer = startingintent.getParcelableExtra("offre_data", OffreItem.class);
                } else {
                    // For API < 33 (most common current use)
                    receivedOffer = startingintent.getParcelableExtra("offre_data");
                }
                intent.putExtra("offre_data", receivedOffer);
            }
            startActivity(intent);
        });
        ImageView imageView30=findViewById(R.id.profile_image);
        imageView30.setOnClickListener(v -> {
            Intent intent = new Intent(Page10Activity.this, Page13Activity.class);
            startActivity(intent);
        });

    }
}