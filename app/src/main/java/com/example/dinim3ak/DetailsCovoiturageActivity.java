package com.example.dinim3ak;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dinim3ak.model.Covoiturage;
import com.dinim3ak.model.Utilisateur;
import com.dinim3ak.services.trip.CovoiturageService;
import com.dinim3ak.services.trip.PassagerAdapter;

import java.util.List;

public class DetailsCovoiturageActivity extends AppCompatActivity {

    private TextView textViewDestination, textViewPrix;
    private RecyclerView recyclerViewPassagers;
    private CovoiturageService covoiturageService;
    private Covoiturage covoiturage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailscovoiturage);

        textViewDestination = findViewById(R.id.texteDestination);
        textViewPrix = findViewById(R.id.textePrix);
        recyclerViewPassagers = findViewById(R.id.recyclerViewPassagers);

        covoiturageService = new CovoiturageService(this);

        long tripId = getIntent().getLongExtra("COVOIT_ID", -1);
        if (tripId == -1) {
            Toast.makeText(this, "Covoiturage introuvable", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        covoiturage = covoiturageService.findTripById(tripId);

        if (covoiturage != null) {
            textViewDestination.setText("Destination : " + covoiturage.getVilleArrivee());
            textViewPrix.setText("Prix : " + covoiturage.getPrixParPassager() + " MAD");

            // Charge les passagers associés à ce covoiturage
            List<Utilisateur> passagers = CovoiturageService.getPassagersPourCovoiturage(tripId); // À adapter selon ta structure

            // Initialise l’adapter
            recyclerViewPassagers.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewPassagers.setAdapter(new PassagerAdapter(passagers, covoiturage.getId(), this));
        }
    }
}
