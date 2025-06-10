package com.dinim3ak.services.trip;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;

import com.dinim3ak.data.repositories.CovoiturageRepository;
import com.dinim3ak.data.repositories.ReservationRepository;
import com.dinim3ak.data.session.UtilisateurSession;
import com.dinim3ak.model.Covoiturage;
import com.dinim3ak.model.Reservation;
import com.dinim3ak.model.ReservationStatus;
import com.dinim3ak.model.Utilisateur;

import java.time.LocalDate;
import java.util.List;

public class ReservationService {
    private ReservationRepository reservationRepository;
    private CovoiturageRepository covoiturageRepository;
    private UtilisateurSession userSession;

    public ReservationService(Context context) {
        reservationRepository = new ReservationRepository(context);
        covoiturageRepository = new CovoiturageRepository(context);
        userSession = UtilisateurSession.getInstance(context);
    }

    public void reserver(LifecycleOwner lifecycleOwner, long tripId, int nbPlaces) {
        Utilisateur currentUser = userSession.getCurrentUser();
        if (currentUser == null) {
            throw new IllegalStateException("User not logged in");
        }

        covoiturageRepository.findById(tripId).observe(lifecycleOwner, covoiturage -> {
            if (covoiturage == null) {
                throw new IllegalArgumentException("Trip not found");
            }
            if (covoiturage.getNombrePlaces()-covoiturage.getNombrePlacesReservees() < nbPlaces) {
                throw new IllegalArgumentException("Not enough available seats");
            }

            Reservation reservation = new Reservation();
            reservation.setPassagerId(currentUser.getId());
            reservation.setTrajetId(tripId);
            reservation.setDateReservation(LocalDate.now());
            reservation.setStatut(ReservationStatus.EN_ATTENTE);

            reservationRepository.insert(reservation);

            // Update available seats
            //covoiturage.setNombrePlacesReservees(covoiturage.getNombrePlacesReservees() + nbPlaces);
            //covoiturageRepository.update(covoiturage);
        });
    }

    public void cancelReservation(LifecycleOwner lifecycleOwner, long reservationId) {
        Utilisateur currentUser = userSession.getCurrentUser();

        reservationRepository.findById(reservationId).observe(lifecycleOwner, reservation -> {
            if (reservation == null) {
                throw new IllegalArgumentException("Reservation not found");
            }
            if (currentUser == null || reservation.getPassagerId() != currentUser.getId()) {
                throw new IllegalStateException("Unauthorized to cancel this reservation");
            }

            // Restore available seats
            covoiturageRepository.findById(reservation.getTrajetId()).observe(lifecycleOwner, covoiturage -> {
                covoiturage.setNombrePlaces(covoiturage.getNombrePlaces() + reservation.getNombrePlaces());
                covoiturageRepository.update(covoiturage);

                reservationRepository.delete(reservation);
            });
        });
    }

    public List<Reservation> getUserReservations() {
        Utilisateur currentUser = userSession.getCurrentUser();
        if (currentUser == null) {
            throw new IllegalStateException("User not logged in");
        }
        return reservationRepository.getReservationsByUserId(currentUser.getId());
    }

    public void updateReservationStatus(LifecycleOwner lifecycleOwner, long reservationId, ReservationStatus status) {
        reservationRepository.findById(reservationId).observe(lifecycleOwner, reservation -> {
            if (reservation == null) {
                throw new IllegalArgumentException("Reservation not found");
            }
            reservation.setStatut(status);
            reservationRepository.update(reservation);
        });

    }

    public List<Reservation> getTripReservations(long tripId) {
        return reservationRepository.getReservationsByCovoiturageId(tripId);
    }
}