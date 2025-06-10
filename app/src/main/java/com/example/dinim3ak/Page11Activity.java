package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dinim3ak.services.trip.ReservationService;

public class Page11Activity extends AppCompatActivity {
    ReservationService reservationService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page11);

        reservationService = new ReservationService(this);

        TextView villeDepartTV = ((TextView) findViewById(R.id.from_city));
        TextView villeArriveeTV = ((TextView) findViewById(R.id.to_city));
        TextView heureDepartTV = ((TextView) findViewById(R.id.from_time));
        TextView heureArriveeTV = ((TextView) findViewById(R.id.to_time));
        TextView prixUnitaireTV = ((TextView) findViewById(R.id.price_value));
        TextView dateTV = ((TextView) findViewById(R.id.date_text));

        Intent startingintent = getIntent();
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
            dateTV.setText(receivedOffer.getDate());
        }

        ImageView back_arrow = findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(v -> {
            finish();
        });

        Button confirm_button = findViewById(R.id.confirm_button);
        confirm_button.setOnClickListener(v -> {
            Intent intent = new Intent(Page11Activity.this, Page12Activity.class);
            if (startingintent != null) {
                OffreItem receivedOffer;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                    receivedOffer = startingintent.getParcelableExtra("offre_data", OffreItem.class);
                } else {
                    // For API < 33 (most common current use)
                    receivedOffer = startingintent.getParcelableExtra("offre_data");
                }
                assert receivedOffer != null;
                try {
                    int nbPlaces = Integer.parseInt(((EditText) findViewById(R.id.nbplacesEdit)).getText().toString());
                    long offreId = receivedOffer.getId();
                    reservationService.reserver(this, offreId, nbPlaces);
                    startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(this, "Veuillez entrer un nombre", Toast.LENGTH_SHORT).show();
                }
            }


        });

    }
}