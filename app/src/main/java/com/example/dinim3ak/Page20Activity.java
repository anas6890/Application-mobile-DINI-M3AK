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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dinim3ak.model.CovoiturageStatus;
import com.dinim3ak.model.Covoiturage;
import com.dinim3ak.services.trip.CovoiturageService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Page20Activity extends AppCompatActivity {

    //private static final String TAG = "Page20Activity";
    Handler handler = new Handler();
    Runnable refresh_function = this::refreshRecyclers;

    private void refreshRecyclers() {
        Log.d("Page20Activity", "Item count: " + adapter.getItemCount());
        Log.d("Page20Activity", "REFRESHING...");
        adapter.notifyDataSetChanged();
        adapter1.notifyDataSetChanged();
    }

    RecyclerView recyclerView;
    RecyclerView recyclerView1;
    OffreItemAdapter adapter;
    OffreItemAdapter adapter1;
    List<OffreItem> rideList;
    List<OffreItem> rideList1;

    private CovoiturageService covoiturageService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page20);

        covoiturageService = new CovoiturageService(this);

        // Setup UI
        setupRecyclerViews();

        // Initialize lists
        rideList = new ArrayList<>();
        rideList1 = new ArrayList<>();

        // Initialize adapters
        /*rideList.add(new OffreItem(0,"Hicham", "demain 17 janv.", "Casablanca", "14:00",
                "Rabat", "15:04", "30.50", "4","1"));

        rideList.add(new OffreItem(1,"Yassine", "vendredi 19 janv.", "Rabat", "08:30",
                "Kenitra", "09:15", "41.30", "2", "2"));
        */


        loadDriverOffers();


        TextView refresh_active = ((TextView) findViewById(R.id.refresh_button));
        refresh_active.setOnClickListener(v -> {
            Log.i("RESERVATION", "REFRESHING...");
            adapter.notifyDataSetChanged();
        });

        TextView refresh_non_active = ((TextView) findViewById(R.id.refresh_button_2));
        refresh_non_active.setOnClickListener(v -> {
            adapter1.notifyDataSetChanged();
        });


        TextView reservations_tab = ((TextView) findViewById(R.id.tab_reservations));
        reservations_tab.setOnClickListener(v -> {
            startActivity(new Intent(this, Page19Activity.class));
        });
    }

    private void setupRecyclerViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadDriverOffers() {
        try {
            covoiturageService.getMyActiveTrips(this, offreItems -> {
                if (offreItems == null) {
                    runOnUiThread(() ->
                            Toast.makeText(this, "Aucune offre trouvée", Toast.LENGTH_SHORT).show()
                    );
                }else{

                    rideList = offreItems;
                    runOnUiThread(() -> {
                        adapter = new OffreItemAdapter(rideList, offre -> {});
                        recyclerView.setAdapter(adapter);
                    });

                }
            });

            covoiturageService.getMyNonActiveTrips(this, offreItems -> {
                if (offreItems == null) {
                    runOnUiThread(() ->
                            Toast.makeText(this, "Aucune offre trouvée", Toast.LENGTH_SHORT).show()
                    );
                }else{
                    rideList1 = offreItems;
                    runOnUiThread(() -> {
                        adapter1 = new OffreItemAdapter(rideList1, offre -> {});
                        recyclerView1.setAdapter(adapter1);
                    });
                }
            });
        } catch (Exception e) {
            Log.e("page20", "Erreur lors du chargement des offres", e);
            runOnUiThread(() ->
                    Toast.makeText(this, "Erreur: " + e.getMessage(), Toast.LENGTH_LONG).show()
            );
        }
    }


    public void refreshOffers() {
        loadDriverOffers();
    }

    @Override
    public void onResume(){
        super.onResume();
        refreshOffers();
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

}
