
package com.example.dinim3ak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Page18Activity extends AppCompatActivity {

    Button confirmerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page18); // le fichier XML s'appelle activity_page18.xml

        confirmerButton = findViewById(R.id.button_confirmer);

        confirmerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Transition vers la page 14
                Intent intent = new Intent(Page18Activity.this, Page14Activity.class);
                startActivity(intent);
            }
        });
    }
}
