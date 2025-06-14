package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

    // Informations de l'offre sélectionnée
    private long offreId;
    private String offreDriver;
    private String offreDate;
    private String offreFrom;
    private String offreTo;
    private List<ReservationDemandeItem> demandesSelectionnees = new ArrayList<>();
    private int nbPlacesDisponibles;

    private boolean isHistory = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demandes_activity_layout);

        // Récupérer les données de l'offre depuis l'Intent
        Intent intent = getIntent();
        if (intent != null) {
            offreId = intent.getLongExtra("offre_id", -1);
            offreDriver = intent.getStringExtra("offre_driver");
            offreDate = intent.getStringExtra("offre_date");
            offreFrom = intent.getStringExtra("offre_from");
            offreTo = intent.getStringExtra("offre_to");
            isHistory = intent.getBooleanExtra("is_history", false);
            nbPlacesDisponibles = intent.getIntExtra("offre_places_disponibles", 0);

        }

        // Mettre à jour le titre avec les informations de l'offre
        updateTitle();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        // Charger les demandes pour cette offre spécifique
        loadDemandesForOffre();

        // Configuration des adapters
        adapterDemande = new ReservationDemandeItemAdapter(rideListDemande, !isHistory, (item, position, isAccepted) -> {
            if (isAccepted) {
                demandesSelectionnees.add(item); // Ajouter à la sélection
                Toast.makeText(this, "Demande marquée comme acceptée", Toast.LENGTH_SHORT).show();
            } else {
                demandesSelectionnees.remove(item); // Retirer de la sélection
                Toast.makeText(this, "Demande refusée", Toast.LENGTH_SHORT).show();
            }

            rideListDemande.remove(position);
            adapterDemande.notifyItemRemoved(position);
        });


        // Adapter sans boutons pour les demandes confirmées
        adapterConfirme = new ReservationDemandeItemAdapter(rideListConfirme, false, null);

        recyclerView.setAdapter(adapterDemande);
        recyclerView1.setAdapter(adapterConfirme);

        Button btnConfirmer = findViewById(R.id.btn_confirmer_selection);
        btnConfirmer.setOnClickListener(v -> {
            int totalPlacesDemandées = 0;

            for (ReservationDemandeItem demande : demandesSelectionnees) {
                totalPlacesDemandées += Integer.parseInt(demande.getNbPassager());
            }

            if (totalPlacesDemandées > nbPlacesDisponibles) {
                Toast.makeText(this, "Le nombre total de passagers dépasse les places disponibles !", Toast.LENGTH_LONG).show();
                return;
            }

            // Tout est OK : ajouter à la liste des confirmés
            for (ReservationDemandeItem demande : demandesSelectionnees) {
                rideListConfirme.add(demande);

                // Appel au service pour confirmer (si tu veux)
                // confirmReservation(demande.getId(), true);
            }

            adapterConfirme.notifyDataSetChanged();
            demandesSelectionnees.clear();

            Toast.makeText(this, "Demandes confirmées avec succès", Toast.LENGTH_SHORT).show();
        });

    }

    private void updateTitle() {
        TextView titleTextView = findViewById(R.id.title_text);
        if (titleTextView != null && offreFrom != null && offreTo != null) {
            String title = offreFrom + " → " + offreTo;
            if (offreDate != null) {
                title += " - " + offreDate;
            }
            titleTextView.setText(title);
        }
    }

    private void loadDemandesForOffre() {
        // TODO: Remplacer par un appel à votre service pour récupérer les vraies demandes
        // En attendant, on utilise des données de test basées sur l'offre sélectionnée

        if (offreId != -1) {
            // Simuler le chargement des demandes pour cette offre
            loadTestDemandes();

            // Ici vous devriez appeler votre service:
            // reservationService.getDemandesForOffre(offreId, this::onDemandesLoaded);
        } else {
            // Données de test par défaut
            loadTestDemandes();
        }
    }

    private void loadTestDemandes() {
        // Données fictives pour les tests - à remplacer par vos vraies données
        rideListDemande.clear();
        rideListConfirme.clear();

        // Demandes en attente
        rideListDemande.add(new ReservationDemandeItem(1, "Leila", offreDate != null ? offreDate : "21 juin",
                offreFrom != null ? offreFrom : "Fès", "15:30",
                offreTo != null ? offreTo : "Casablanca", "18:30", "2"));
        rideListDemande.add(new ReservationDemandeItem(2, "Yassine", offreDate != null ? offreDate : "22 juin",
                offreFrom != null ? offreFrom : "Rabat", "10:00",
                offreTo != null ? offreTo : "Tanger", "14:00", "1"));

        // Demandes déjà confirmées (exemple)
        if (isHistory) {
            rideListConfirme.add(new ReservationDemandeItem(3, "Ahmed", offreDate != null ? offreDate : "20 juin",
                    offreFrom != null ? offreFrom : "Fès", "15:30",
                    offreTo != null ? offreTo : "Casablanca", "18:30", "1"));
        }
    }

    // Méthode pour confirmer/refuser une réservation via votre service
    private void confirmReservation(long reservationId, boolean isAccepted) {
        // TODO: Implémenter l'appel à votre service
        // reservationService.confirmReservation(reservationId, isAccepted, this::onReservationConfirmed);
    }

    // Callback pour la confirmation de réservation
    private void onReservationConfirmed(boolean success, String message) {
        runOnUiThread(() -> {
            if (success) {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Erreur: " + message, Toast.LENGTH_LONG).show();
            }
        });
    }

    // Méthode pour le bouton retour
    public void goBack(View view) {
        finish(); // Retour à la page précédente
    }
}