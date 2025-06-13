package com.example.dinim3ak;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DemandeActivity extends AppCompatActivity {

    RecyclerView recyclerView, recyclerView1;
    ReservationDemandeItemAdapter adapterDemande, adapterConfirme;
    List<ReservationDemandeItem> rideListDemande = new ArrayList<>();
    List<ReservationDemandeItem> rideListConfirme = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demandes_activity_layout);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        // Remplir les données fictives pour les tests
        rideListDemande.add(new ReservationDemandeItem(1, "Leila", "21 juin", "Fès", "15:30", "Casablanca", "18:30", "2"));
        rideListDemande.add(new ReservationDemandeItem(2, "Yassine", "22 juin", "Rabat", "10:00", "Tanger", "14:00", "1"));

        adapterDemande = new ReservationDemandeItemAdapter(rideListDemande, true, (item, position, isAccepted) -> {
            if (isAccepted) {
                rideListConfirme.add(item);
                adapterConfirme.notifyItemInserted(rideListConfirme.size() - 1);
                Toast.makeText(this, "Demande acceptée", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Demande refusée", Toast.LENGTH_SHORT).show();
            }

            rideListDemande.remove(position);
            adapterDemande.notifyItemRemoved(position);
        });

// Adapter sans boutons pour les demandes confirmées
        adapterConfirme = new ReservationDemandeItemAdapter(rideListConfirme, false, null);


        recyclerView.setAdapter(adapterDemande);
        recyclerView1.setAdapter(adapterConfirme);
    }
}
