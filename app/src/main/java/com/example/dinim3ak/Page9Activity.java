package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Page9Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    OffreItemAdapter adapter;
    List<OffreItem> offreList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newpage9);

        ImageView profil = findViewById(R.id.avatar9);
        profil.setOnClickListener(v -> {
            Intent intent = new Intent(Page9Activity.this, Page22Activity.class);
            startActivity(intent);
        });

        Button btnhelp2=findViewById(R.id.btnHelp2);
        btnhelp2.setOnClickListener(v -> {
            Toast.makeText(this, "Fonctionnalité indisponible", Toast.LENGTH_SHORT).show();
        });

        ImageView notification2 = findViewById(R.id.notification2);
        profil.setOnClickListener(v -> {
            Toast.makeText(this, "Fonctionnalité indisponible", Toast.LENGTH_SHORT).show();

        });

        recyclerView = findViewById(R.id.offreView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Sample data
        offreList = new ArrayList<>();
        offreList.add(new OffreItem("Hicham", "demain 17 janv.", "Casablanca", "14:00", "Rabat", "15:04", "30.50", "4 passagers"));
        offreList.add(new OffreItem("Yassine", "vendredi 19 janv.", "Rabat", "08:30", "Kenitra", "09:15", "41.30", "2 passagers"));

        adapter = new OffreItemAdapter(offreList, new OffreItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(OffreItem offreItem) {
                Intent i = new Intent(Page9Activity.this, Page10Activity.class);
                startActivity(i);
            }
        });
        recyclerView.setAdapter(adapter);

    }

    public void goToPassager(View view){

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

    }
}