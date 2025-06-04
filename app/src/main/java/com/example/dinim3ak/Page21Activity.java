package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Page21Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page21);
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
        Intent i = new Intent(this, Page19Activity.class);
        this.startActivity(i);
    }
    public void goToWallet(View view){

    }
}