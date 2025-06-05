package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Page14Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page14);
    }

    public void on_publier_covoiturage_click(View view){
        Intent i = new Intent(this, Page5Activity.class);
        this.startActivity(i);
    }

    public void goToPassager(View view){

    }
    public void goToConducteur(View view){

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