package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dinim3ak.services.user.UtilisateurService;

public class Page22Activity extends AppCompatActivity {
    UtilisateurService utilisateurService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page22);

        utilisateurService = new UtilisateurService(this);
        TextView logout = ((TextView) findViewById(R.id.logout));
        logout.setOnClickListener(v -> {
            utilisateurService.logout();
            startActivity(new Intent(Page22Activity.this, Page5Activity.class));
        });
    }

    public void goBack(View view){
        finish() ;
    }
}