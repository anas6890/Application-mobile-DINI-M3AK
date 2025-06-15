package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dinim3ak.model.ReservationStatus;
import com.dinim3ak.services.trip.CovoiturageService;
import com.dinim3ak.services.trip.ReservationService;

import java.util.ArrayList;
import java.util.List;

public class DemandeActivity extends AppCompatActivity {
    Handler handler = new Handler();
    Runnable refresh_function = this::refreshRecyclers;

    private void refreshRecyclers() {
        Log.d("Page20Activity", "REFRESHING...");
        adapterDemande.notifyDataSetChanged();
    }
    RecyclerView recyclerView, recyclerView1;
    ReservationDemandeItemAdapter adapterDemande, adapterConfirme;
    List<ReservationDemandeItem> rideListDemande = new ArrayList<>();
    List<ReservationDemandeItem> rideListConfirme = new ArrayList<>();

    ReservationService reservationService;
    CovoiturageService covoiturageService;


    // Informations de l'offre sélectionnée
    private long offreId;
    private String offreDriver;
    private String offreDate;
    private String offreFrom;
    private String offreTo;
    private List<ReservationDemandeItem> demandesSelectionnees, demandesRefusees;
    private int nbPlacesDisponibles;
    private boolean offre_overte;

    private boolean isHistory = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demandes_activity_layout);
        demandesSelectionnees  = new ArrayList<>();
        demandesRefusees = new ArrayList<>();

        covoiturageService = new CovoiturageService(this);
        reservationService = new ReservationService(this);
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
            offre_overte = intent.getBooleanExtra("COVOITURAGE_OUVERT", false);
        }


        // Mettre à jour le titre avec les informations de l'offre
        updateTitle();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        LinearLayout demandesTitle = ((LinearLayout) findViewById(R.id.demandes));


        // Charger les demandes pour cette offre spécifique
        if(offre_overte)
            loadDemandesForOffre();
        else
            loadDemandesConfirmees();

        // Configuration des adapters
        adapterDemande = new ReservationDemandeItemAdapter(this, offre_overte, (item, position) -> {
            item.setSelected(!item.isSelected());
            rideListConfirme.add(item);// Ajouter à la sélection
            Log.d("ACCEPTER", ""+rideListDemande.size());
            rideListDemande.remove(position);
            Toast.makeText(this, "Demande marquée comme acceptée", Toast.LENGTH_SHORT).show();

            adapterDemande.setDemandesList(rideListDemande);
            adapterConfirme.setDemandesList(rideListConfirme);
            adapterDemande.notifyDataSetChanged();
            adapterConfirme.notifyDataSetChanged();
        });
        adapterDemande.setDemandesList(rideListDemande);


        // Adapter sans boutons pour les demandes confirmées
        adapterConfirme = new ReservationDemandeItemAdapter(this, offre_overte, (item, position) -> {
            item.setSelected(!item.isSelected());
            rideListConfirme.remove(position);// Ajouter à la sélection
            rideListDemande.add(item);
            Toast.makeText(this, "Demande marquée comme refusee", Toast.LENGTH_SHORT).show();

            adapterDemande.setDemandesList(rideListDemande);
            adapterConfirme.setDemandesList(rideListConfirme);
            adapterDemande.notifyDataSetChanged();
            adapterConfirme.notifyDataSetChanged();
        });
        adapterConfirme.setDemandesList(rideListConfirme);
        recyclerView.setAdapter(adapterDemande);
        recyclerView1.setAdapter(adapterConfirme);
        Button btn = findViewById(R.id.btn_confirmer_selection);

        if(!offre_overte){
            demandesTitle.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
            btn.setText("Terminer");
        }

        btn.setOnClickListener(v -> {
            if(offre_overte){
                int totalPlacesDemandées = 0;

                for (ReservationDemandeItem demande : rideListConfirme) {
                    totalPlacesDemandées += demande.getNbPassager().toCharArray()[0]-'0';
                }

                if (totalPlacesDemandées > nbPlacesDisponibles) {
                    Log.d("ACCEPTER", ""+totalPlacesDemandées);

                    Toast.makeText(this, "Le nombre total de passagers dépasse les places disponibles !", Toast.LENGTH_LONG).show();
                    return;
                }

                // Tout est OK : ajouter à la liste des confirmés
                for (ReservationDemandeItem demande : rideListConfirme) {
                    reservationService.accepterReservation(DemandeActivity.this, demande.getId());
                }
                for (ReservationDemandeItem demande : rideListDemande){
                    reservationService.refuserReservation(DemandeActivity.this, demande.getId());
                }
                Toast.makeText(this, "Demandes confirmées avec succès", Toast.LENGTH_SHORT).show();
                covoiturageService.fermerCovoiturage(DemandeActivity.this, offreId);
                finish();
            }else{
                //Terminer covoiturage
                covoiturageService.terminerCovoiturage(DemandeActivity.this, offreId);
                for(ReservationDemandeItem demande : rideListConfirme){
                    reservationService.finaliserReservation(DemandeActivity.this, demande.getId());
                }
                finish();
            }
        });

        Button annulerOffre = findViewById(R.id.btn_annuler_offre);
        annulerOffre.setOnClickListener(v -> {
            covoiturageService.cancelTrip(DemandeActivity.this, offreId);
            finish();
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
        try {
            reservationService.getTripReservations(this, offreId, ReservationStatus.EN_ATTENTE).observe(this, new Observer<List<ReservationDemandeItem>>() {
                @Override
                public void onChanged(List<ReservationDemandeItem> reservationDemandeItems) {
                    if (reservationDemandeItems == null) {
                        runOnUiThread(() ->
                                Toast.makeText(DemandeActivity.this, "Aucune reservation trouvée", Toast.LENGTH_SHORT).show()
                        );
                    } else {
                        runOnUiThread(() -> {
                            rideListDemande = reservationDemandeItems;
                            adapterDemande.setDemandesList(rideListDemande);
                            Log.d("ACCETPER", ""+adapterDemande.getItemCount());
                        });
                    }
                }
            });

        } catch (Exception e) {
            Log.e("Page19", "Erreur lors du chargement des offres", e);
            runOnUiThread(() ->
                    Toast.makeText(this, "Erreur: " + e.getMessage(), Toast.LENGTH_LONG).show()
            );
        }
    }


    private void loadDemandesConfirmees() {
        try {
            reservationService.getTripReservations(this, offreId, ReservationStatus.ACCEPTEE).observe(this, new Observer<List<ReservationDemandeItem>>() {
                @Override
                public void onChanged(List<ReservationDemandeItem> reservationDemandeItems) {
                    if (reservationDemandeItems == null) {
                        runOnUiThread(() ->
                                Toast.makeText(DemandeActivity.this, "Aucune reservation trouvée", Toast.LENGTH_SHORT).show()
                        );
                    } else {
                        runOnUiThread(() -> {
                            rideListConfirme = reservationDemandeItems;
                            adapterConfirme.setDemandesList(rideListConfirme);
                            Log.d("ACCETPER", ""+adapterConfirme.getItemCount());
                        });
                    }
                }
            });

        } catch (Exception e) {
            Log.e("Page19", "Erreur lors du chargement des offres", e);
            runOnUiThread(() ->
                    Toast.makeText(this, "Erreur: " + e.getMessage(), Toast.LENGTH_LONG).show()
            );
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

    public void refreshDemandes() {

        if(offre_overte) loadDemandesForOffre();
        else loadDemandesConfirmees();
    }

    @Override
    public void onResume(){
        super.onResume();
        refreshDemandes();
        long timeToRefresh = 1000; //ms
        handler.postDelayed(refresh_function, timeToRefresh);
    }

    @Override
    public void onPause(){
        super.onPause();
        handler.removeCallbacks(refresh_function);
    }

    // Méthode pour le bouton retour
    public void goBack(View view) {
        finish(); // Retour à la page précédente
    }
}