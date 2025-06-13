package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dinim3ak.services.trip.CovoiturageService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Page9Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<OffreItem> offreItems;
    OffreItemAdapter adapter;
    CovoiturageService covoiturageService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page9);

        covoiturageService = new CovoiturageService(this);

        ImageView profil1 = findViewById(R.id.avatar10);
        profil1.setOnClickListener(v -> {
            Intent intent = new Intent(Page9Activity.this, Page22Activity.class);
            startActivity(intent);
        });

        Button btnhelp2=findViewById(R.id.btnHelp2);
        btnhelp2.setOnClickListener(v -> Toast.makeText(this, "Fonctionnalité indisponible", Toast.LENGTH_SHORT).show());
        ImageView notification2 = findViewById(R.id.notification2);
        notification2.setOnClickListener(v -> Toast.makeText(this, "Fonctionnalité indisponible", Toast.LENGTH_SHORT).show());

        TextView refresh = ((TextView) findViewById(R.id.refresh_button));
        refresh.setOnClickListener(v -> {
            adapter.notifyDataSetChanged();
        });
        recyclerView = findViewById(R.id.offreView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent startingIntent = getIntent();
        String ville1 = "", ville2 = "", date = "";
        // Check if the intent has extras before trying to retrieve them
        if (startingIntent != null && startingIntent.getExtras() != null) {
            ville1 = startingIntent.getStringExtra("ville1");
            ville2 = startingIntent.getStringExtra("ville2");
            date = startingIntent.getStringExtra("date");
            covoiturageService.searchTrips(this, ville1, ville2, date==null?null:LocalDate.parse(date), offreItems -> {
                this.offreItems = offreItems;
                this.offreItems.add(new OffreItem(0,"Hicham", "demain 17 janv.", "Casablanca", "14:00",
                        "Rabat", "15:04", "30.50", "4","1"));
                this.offreItems.add(new OffreItem(1,"Yassine", "vendredi 19 janv.", "Rabat", "08:30",
                        "Kenitra", "09:15", "41.30", "2", "2"));

                adapter = new OffreItemAdapter(this.offreItems, offreItem -> {
                    Intent i = new Intent(Page9Activity.this, Page10Activity.class);
                    i.putExtra("offre_data", offreItem);
                    startActivity(i);
                });
                recyclerView.setAdapter(adapter);
            });
        } else {
            Toast.makeText(this, "No data received", Toast.LENGTH_SHORT).show();
        }

    }

    public void goToPassager(View view){
        Intent i = new Intent(this, Page5Activity.class);
        this.startActivity(i);
    }
    public void goToConducteur(View view){
        Intent i = new Intent(this, Page14Activity.class);
        this.startActivity(i);
    }
    public void goToCovoiturage(View view){
        Intent i = new Intent(this, Page19Activity.class);
        this.startActivity(i);
    }
    public void goToWallet(View view){
        Intent i = new Intent(this, Page21Activity.class);
        this.startActivity(i);
    }
}