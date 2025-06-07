package com.example.dinim3ak;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dinim3ak.model.Sex;
import com.dinim3ak.services.user.UtilisateurService;

import java.util.Calendar;
import java.util.Date;

public class Page4Activity extends AppCompatActivity {

    private UtilisateurService utilisateurService;

    private EditText dateEditText;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page4);

        utilisateurService = new UtilisateurService(this);
        Button btnContinuer = findViewById(R.id.btnContinuer);
        ImageView backButton = findViewById(R.id.backButton4);

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(Page4Activity.this, Page2Activity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        btnContinuer.setOnClickListener(v -> {
            String email = ((TextView)findViewById(R.id.email_input)).getText().toString();
            String password = ((TextView)findViewById(R.id.password_input)).getText().toString();
            String nom = ((TextView)findViewById(R.id.nom_input)).getText().toString();
            String prenom = ((TextView)findViewById(R.id.prenom_input)).getText().toString();
            Date dateNaissance = new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            String tel = ((TextView)findViewById(R.id.phone_input)).getText().toString();
            String sexstr = ((RadioButton)findViewById(((RadioGroup)findViewById(R.id.radio_button_group)).getCheckedRadioButtonId())).getText().toString();
            Sex sex = sexstr.equals("homme") ? Sex.HOMME : Sex.FEMME;
            if(!utilisateurService.registerUser(nom, prenom, dateNaissance, email, password, sex, tel)){
                Toast.makeText(this, "Email invalide!", Toast.LENGTH_SHORT).show();
            }else{
                startActivity(new Intent(this, Page5Activity.class));
            }
        });

        dateEditText = findViewById(R.id.dateEditText);
        calendar = Calendar.getInstance();

        // Désactive le clavier pour ce champ (on ne veut pas saisie manuelle)
        dateEditText.setShowSoftInputOnFocus(false);

        dateEditText.setOnClickListener(v -> showDatePicker());

        // Si tu veux que ça marche aussi au focus (ex: via navigation clavier)
        dateEditText.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                showDatePicker();
            }
        });
    }

    private void showDatePicker() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog pickerDialog = new DatePickerDialog(
                this,
                (DatePicker view, int selectedYear, int selectedMonth, int selectedDay) -> {
                    // Met à jour la date choisie dans l'EditText au format jj/MM/aaaa
                    String dateStr = String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear);
                    dateEditText.setText(dateStr);

                    // Met à jour le calendrier interne
                    calendar.set(selectedYear, selectedMonth, selectedDay);
                },
                year,
                month,
                day);

        pickerDialog.show();
    }
}
