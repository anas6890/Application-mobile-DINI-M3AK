package com.example.dinim3ak;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
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
    Handler handler = new Handler();
    Runnable refresh_function = this::refreshRecyclers;

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
        setContentView(R.layout.page19);

        reservationService = new ReservationService(this);

        // Setup UI
        setupRecyclerViews();

        // Initialize lists
        reservationList = new ArrayList<>();
        reservationList1 = new ArrayList<>();

        adapter = new ReservationItemAdapter(reservationItem -> {
            /*reservationService.cancelReservation(this, reservationItem.id);
            loadUserReservations();*/
            //refreshRecyclers();
        });
        recyclerView.setAdapter(adapter);

        adapter1 = new ReservationItemAdapter(reservationItem -> {});
        recyclerView1.setAdapter(adapter1);

        loadUserReservations();

        TextView refresh_active = ((TextView) findViewById(R.id.refresh_button));
        refresh_active.setOnClickListener(v -> {
            adapter.notifyDataSetChanged();
        });

        TextView refresh_non_active = ((TextView) findViewById(R.id.refresh_button_2));
        refresh_non_active.setOnClickListener(v -> {
            adapter1.notifyDataSetChanged();
        });
    }

    private void refreshRecyclers() {
        adapter.notifyDataSetChanged();
        adapter1.notifyDataSetChanged();
    }

    private void setupRecyclerViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadUserReservations() {
        try {
            reservationService.getUserActiveReservations().observe(this, new Observer<List<ReservationItem>>() {
                @Override
                public void onChanged(List<ReservationItem> reservationItems) {
                    if (reservationItems == null) {
                        runOnUiThread(() ->
                                Toast.makeText(Page19Activity.this, "Aucune offre trouvée", Toast.LENGTH_SHORT).show()
                        );
                    } else {
                        runOnUiThread(() -> {
                            adapter.setReservationItemList(reservationItems);
                        });
                    }
                }
            });

            reservationService.getUserNonActiveReservations(this, reservationItems -> {
                if (reservationItems == null) {
                    runOnUiThread(() ->
                            Toast.makeText(this, "Aucune offre trouvée", Toast.LENGTH_SHORT).show()
                    );
                }else{
                    runOnUiThread(() -> {
                        adapter1.setReservationItemList(reservationItems);
                    });

                }
            });
        } catch (Exception e) {
            Log.e("Page19", "Erreur lors du chargement des offres", e);
            runOnUiThread(() ->
                    Toast.makeText(this, "Erreur: " + e.getMessage(), Toast.LENGTH_LONG).show()
            );
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        long timeToRefresh = 1000; //ms
        handler.postDelayed(refresh_function, timeToRefresh);
    }

    @Override
    public void onPause(){
        super.onPause();
        handler.removeCallbacks(refresh_function);
    }
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
