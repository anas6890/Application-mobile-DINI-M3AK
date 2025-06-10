package com.dinim3ak.services.trip;

import android.content.Context;
import android.telecom.Call;

import androidx.lifecycle.LifecycleOwner;

import com.dinim3ak.data.repositories.CovoiturageRepository;
import com.dinim3ak.data.repositories.UtilisateurRepository;
import com.dinim3ak.data.session.UtilisateurSession;
import com.dinim3ak.model.Covoiturage;
import com.dinim3ak.model.Utilisateur;
import com.dinim3ak.services.Callback;
import com.dinim3ak.services.user.UtilisateurService;
import com.example.dinim3ak.OffreItem;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class CovoiturageService {
    private CovoiturageRepository covoiturageRepository;
    private UtilisateurRepository utilisateurRepository;
    private UtilisateurSession userSession;

    public CovoiturageService(Context context) {
        covoiturageRepository = new CovoiturageRepository(context);
        utilisateurRepository = new UtilisateurRepository(context);
        userSession = UtilisateurSession.getInstance(context);
    }

    public Covoiturage createCovoiturage(String departure, String destination, LocalDate date, LocalTime heureDepart,
                                         int availableSeats, float price) {
        Utilisateur currentUser = userSession.getCurrentUser();
        if (currentUser == null) {
            throw new IllegalStateException("User not logged in");
        }

        Covoiturage trip = new Covoiturage();
        trip.setVilleDepart(departure);
        trip.setVilleArrivee(destination);
        trip.setHeureDepart(heureDepart);
        trip.setDateDepart(date);
        trip.setNombrePlaces(availableSeats);
        trip.setPrixParPassager(price);
        trip.setConducteurId(currentUser.getId());

        covoiturageRepository.insert(trip);

        return trip;
    }

    public void covoiturageToOffre(LifecycleOwner lifecycleOwner, Covoiturage covoiturage, Callback<OffreItem> callback){
        utilisateurRepository.findById(covoiturage.getConducteurId()).observe(lifecycleOwner, driver -> {
            long id = covoiturage.getId();
            String driverName = driver.getNom() + " " + driver.getPrenom();
            String date = covoiturage.getDateDepart().toString();
            String villeDepart = covoiturage.getVilleDepart();
            String heureDepart = covoiturage.getHeureDepart().toString();
            String villeArrivee = covoiturage.getVilleArrivee();
            String heureArrivee = (LocalTime.ofSecondOfDay(covoiturage.getHeureDepart().toSecondOfDay() +
                    (int)(covoiturage.getDureeEstimee()*60))).toString();
            String prix = String.valueOf(covoiturage.getPrixParPassager());
            String nbPassager = String.valueOf(covoiturage.getNombrePlaces());
            String nbPassagerRestants = String.valueOf(covoiturage.getNombrePlaces()-covoiturage.getNombrePlacesReservees());
            callback.onResult(new OffreItem(id, driverName, date, villeDepart, villeArrivee,
                    heureDepart, heureArrivee, prix, nbPassager, nbPassagerRestants));
        });
    }
    public void searchTrips(LifecycleOwner lifecycleOwner, String departure, String destination,
                            LocalDate date, Callback<List<OffreItem>> callback) {
        covoiturageRepository.searchCovoiturage(departure, destination, date).observe(lifecycleOwner, covoiturages -> {
            List<OffreItem> offreItems = new ArrayList<>();
            for(Covoiturage covoiturage : covoiturages){
                covoiturageToOffre(lifecycleOwner, covoiturage, offreItems::add);
            }
            callback.onResult(offreItems);
        });
    }

    public void getMyTrips(LifecycleOwner lifecycleOwner, Callback<List<Covoiturage>> callback) {
        Utilisateur currentUser = userSession.getCurrentUser();
        if (currentUser == null) {
            throw new IllegalStateException("User not logged in");
        }
        covoiturageRepository.getCovoiturageByConducteurId(currentUser.getId()).observe(lifecycleOwner,
                callback::onResult);
    }

    public void cancelTrip(LifecycleOwner lifecycleOwner, long tripId) {
        covoiturageRepository.findById(tripId).observe(lifecycleOwner, covoiturage -> {
            if (covoiturage == null) {
                throw new IllegalArgumentException("Trip not found");
            }

            Utilisateur currentUser = userSession.getCurrentUser();
            if (currentUser == null || covoiturage.getConducteurId() != currentUser.getId()) {
                throw new IllegalStateException("Unauthorized to cancel this trip");
            }
            covoiturageRepository.delete(covoiturage);
        });

    }

    public void findTripById(LifecycleOwner lifecycleOwner, long tripId, Callback<Covoiturage> callback) {
        covoiturageRepository.findById(tripId).observe(lifecycleOwner, callback::onResult);
    }


}
