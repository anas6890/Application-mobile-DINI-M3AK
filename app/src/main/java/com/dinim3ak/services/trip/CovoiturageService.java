package com.dinim3ak.services.trip;

import android.content.Context;

import com.dinim3ak.data.repositories.CovoiturageRepository;
import com.dinim3ak.data.repositories.UtilisateurRepository;
import com.dinim3ak.data.session.UtilisateurSession;
import com.dinim3ak.model.Covoiturage;
import com.dinim3ak.model.Utilisateur;
import com.dinim3ak.services.user.UtilisateurService;
import com.example.dinim3ak.OffreItem;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class CovoiturageService {
    private static CovoiturageRepository covoiturageRepository;
    private UtilisateurRepository utilisateurRepository;
    private UtilisateurSession userSession;

    public CovoiturageService(Context context) {
        covoiturageRepository = new CovoiturageRepository(context);
        utilisateurRepository = new UtilisateurRepository(context);
        userSession = UtilisateurSession.getInstance(context);
    }

    public Covoiturage createCovoiturage(String depart, String destination, LocalDate date, LocalTime heureDepart,
                                         int availableSeats, float price) {
        Utilisateur currentUser = userSession.getCurrentUser();
        if (currentUser == null) {
            throw new IllegalStateException("User not logged in");
        }

        Covoiturage trip = new Covoiturage();
        trip.setVilleDepart(depart);
        trip.setVilleArrivee(destination);
        trip.setHeureDepart(heureDepart);
        trip.setDateDepart(date);
        trip.setNombrePlaces(availableSeats);
        trip.setPrixParPassager(price);
        trip.setConducteurId(currentUser.getId());

        covoiturageRepository.insert(trip);

        return trip;
    }

    public OffreItem covoiturageToOffre(Covoiturage covoiturage){
        //Utilisateur driver = utilisateurRepository.findById(covoiturage.getConducteurId());
        //String driverName = driver.getNom() + " " + driver.getPrenom();

        return null;
    }
    public List<OffreItem> searchTrips(String departure, String destination, Date date) {
        List<Covoiturage> matchingCovoiturages = covoiturageRepository.searchCovoiturage(departure, destination, date);
        List<OffreItem> matchingOffres = new ArrayList<>();
        for(Covoiturage covoiturage : matchingCovoiturages){
        }
        return matchingOffres;
    }

    public List<Covoiturage> getMyTrips() {
        Utilisateur currentUser = userSession.getCurrentUser();
        if (currentUser == null) {
            throw new IllegalStateException("User not logged in");
        }
        return covoiturageRepository.getCovoiturageByConducteurId(currentUser.getId());
    }

    public List<Covoiturage> getMyBookedTrips() {
        Utilisateur currentUser = userSession.getCurrentUser();
        if (currentUser == null) {
            throw new IllegalStateException("User not logged in");
        }
        return covoiturageRepository.getCovoiturageReserveByUserId(currentUser.getId());
    }

    public void cancelTrip(long tripId) {
        Covoiturage trip = covoiturageRepository.findById(tripId);
        if (trip == null) {
            throw new IllegalArgumentException("Trip not found");
        }

        Utilisateur currentUser = userSession.getCurrentUser();
        if (currentUser == null || trip.getConducteurId() != currentUser.getId()) {
            throw new IllegalStateException("Unauthorized to cancel this trip");
        }

        covoiturageRepository.delete(tripId);
    }

    public Covoiturage findTripById(long tripId) {
        return covoiturageRepository.findById(tripId);
    }
    public static List<Utilisateur> getPassagersPourCovoiturage(long tripId) {
        return covoiturageRepository.getPassagersByCovoiturageId(tripId);
    }

}


