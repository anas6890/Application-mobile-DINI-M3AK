package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Page19Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    ReservationItemAdapter adapter;
    List<ReservationItem> rideList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page19);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Sample data
        rideList = new ArrayList<>();
        rideList.add(new ReservationItem("Hicham", "demain 17 janv.", "Casablanca", "14:00", "Rabat", "15:04", "Confirmé"));
        rideList.add(new ReservationItem("Yassine", "vendredi 19 janv.", "Rabat", "08:30", "Kenitra", "09:15", "Confirmé"));

        adapter = new ReservationItemAdapter(rideList);
        recyclerView.setAdapter(adapter);
    }

    public void goBack(View view){
        finish();
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

    }
    public void goToWallet(View view){
        Intent i = new Intent(this, Page22Activity.class);
        this.startActivity(i);
    }

    public void goToOffres(View view){
        Intent i = new Intent(this, Page20Activity.class);
        this.startActivity(i);
    }
}