package com.example.dinim3ak;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dinim3ak.model.CovoiturageStatus;
import com.dinim3ak.model.Covoiturage;
import com.dinim3ak.services.trip.CovoiturageService;
import com.dinim3ak.services.trip.ReservationService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Page19Activity extends AppCompatActivity {

    private static final String TAG = "Page20Activity";

    RecyclerView recyclerView;
    RecyclerView recyclerView1;
    ReservationItemAdapter adapter;
    ReservationItemAdapter adapter1;
    List<ReservationItem> reservationList;
    List<ReservationItem> reservationList1;

    private ReservationService reservationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page20);

        Log.d(TAG, "onCreate: Initialisation de Page20Activity");

        reservationService = new ReservationService(this);

        // Setup UI
        setupRecyclerViews();

        // Initialize lists
        reservationList = new ArrayList<>();
        reservationList1 = new ArrayList<>();

        loadUserReservations();

        adapter = new ReservationItemAdapter(reservationList);
        recyclerView.setAdapter(adapter);
        adapter1 = new ReservationItemAdapter(reservationList1);
        recyclerView1.setAdapter(adapter1);

        TextView refresh_active = ((TextView) findViewById(R.id.refresh_button));
        refresh_active.setOnClickListener(v -> {
            Log.i("RESERVATION", "REFRESHING...");
            adapter.notifyDataSetChanged();
        });

        TextView refresh_non_active = ((TextView) findViewById(R.id.refresh_button_2));
        refresh_non_active.setOnClickListener(v -> {
            adapter1.notifyDataSetChanged();
        });
    }

    private void setupRecyclerViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadUserReservations() {
        try {
            reservationService.getUserActiveReservations(this, reservationItems -> {
                if (reservationItems == null) {
                    runOnUiThread(() ->
                            Toast.makeText(this, "Aucune offre trouvée", Toast.LENGTH_SHORT).show()
                    );
                }else{

                    reservationList = reservationItems;
                    runOnUiThread(() -> {
                        adapter = new ReservationItemAdapter(reservationList);
                        recyclerView.setAdapter(adapter);
                    });

                }
            });

            reservationService.getUserNonActiveReservations(this, reservationItems -> {
                if (reservationItems == null) {
                    runOnUiThread(() ->
                            Toast.makeText(this, "Aucune offre trouvée", Toast.LENGTH_SHORT).show()
                    );
                }else{

                    reservationList1 = reservationItems;
                    runOnUiThread(() -> {
                        adapter1 = new ReservationItemAdapter(reservationList1);
                        recyclerView.setAdapter(adapter1);
                    });

                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Erreur lors du chargement des offres", e);
            runOnUiThread(() ->
                    Toast.makeText(this, "Erreur: " + e.getMessage(), Toast.LENGTH_LONG).show()
            );
        }
    }

    /*private void separateOffersByStatus(List<Covoiturage> covoiturages) {
        rideList.clear();
        rideList1.clear();

        if (covoiturages.isEmpty()) {
            runOnUiThread(() -> {
                adapter.notifyDataSetChanged();
                adapter1.notifyDataSetChanged();
                Toast.makeText(this, "Aucune offre à afficher", Toast.LENGTH_SHORT).show();
            });
            return;
        }

        AtomicInteger pendingConversions = new AtomicInteger(covoiturages.size());

        for (Covoiturage covoiturage : covoiturages) {
            try {
                covoiturageService.covoiturageToOffre(this, covoiturage, offreItem -> {
                    runOnUiThread(() -> {
                        if (isActiveOffer(covoiturage)) {
                            rideList.add(offreItem);
                        } else {
                            rideList1.add(offreItem);
                        }

                        int remaining = pendingConversions.decrementAndGet();
                        if (remaining == 0) {
                            adapter.notifyDataSetChanged();
                            adapter1.notifyDataSetChanged();

                            if (rideList.isEmpty() && rideList1.isEmpty()) {
                                Toast.makeText(this, "Aucune offre à afficher après conversion", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                });
            } catch (Exception e) {
                Log.e(TAG, "Erreur lors de la conversion du covoiturage", e);
                int remaining = pendingConversions.decrementAndGet();
                if (remaining == 0) {
                    runOnUiThread(() -> {
                        adapter.notifyDataSetChanged();
                        adapter1.notifyDataSetChanged();
                    });
                }
            }
        }
    }*/



    // Navigation
    public void goBack(View view) {
        finish();
    }

    public void goToPassager(View view) {
        startActivity(new Intent(this, Page5Activity.class));
    }

    public void goToConducteur(View view) {
        startActivity(new Intent(this, Page14Activity.class));
    }

    public void goToCovoiturage(View view) {
        // Implémentation future
    }

    public void goToWallet(View view) {
        startActivity(new Intent(this, Page22Activity.class));
    }

    public void goToOffres(View view) {
        startActivity(new Intent(this, Page20Activity.class));
    }
}
