package com.dinim3ak.services.trip;

import android.content.Context;

import com.dinim3ak.data.repositories.CovoiturageRepository;
import com.dinim3ak.data.session.UtilisateurSession;
import com.dinim3ak.model.Covoiturage;
import com.dinim3ak.model.Utilisateur;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class CovoiturageService {
    private CovoiturageRepository covoiturageRepository;
    private UtilisateurSession userSession;

    public CovoiturageService(Context context) {
        covoiturageRepository = new CovoiturageRepository(context);
        userSession = UtilisateurSession.getInstance(context);
    }

    public Covoiturage createCovoiturage(String departure, String destination, LocalTime heureDepart,LocalDate date ,
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

    public List<Covoiturage> searchTrips(String departure, String destination, Date date) {
        return covoiturageRepository.searchCovoiturage(departure, destination, date);
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

    public Covoiturage getTripById(long tripId) {
        return covoiturageRepository.findById(tripId);
    }
}
