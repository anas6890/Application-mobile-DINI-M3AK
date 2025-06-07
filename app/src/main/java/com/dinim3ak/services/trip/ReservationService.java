package com.dinim3ak.services.trip;

import android.content.Context;

import com.dinim3ak.data.repositories.CovoiturageRepository;
import com.dinim3ak.data.repositories.ReservationRepository;
import com.dinim3ak.data.session.UtilisateurSession;
import com.dinim3ak.model.Covoiturage;
import com.dinim3ak.model.Reservation;
import com.dinim3ak.model.ReservationStatus;
import com.dinim3ak.model.Utilisateur;

import java.util.Date;
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

    public Reservation reserver(long tripId) {
        Utilisateur currentUser = userSession.getCurrentUser();
        if (currentUser == null) {
            throw new IllegalStateException("User not logged in");
        }

        Covoiturage trip = covoiturageRepository.findById(tripId);
        if (trip == null) {
            throw new IllegalArgumentException("Trip not found");
        }

        Reservation reservation = new Reservation();
        reservation.setId(tripId);
        reservation.setPassagerId(currentUser.getId());
        reservation.setDateReservation(new Date());
        reservation.setStatut(ReservationStatus.EN_ATTENTE);

        reservationRepository.insert(reservation);

        // Update available seats
        trip.setNombrePlaces(trip.getNombrePlaces() - 1);
        covoiturageRepository.update(trip);

        return reservation;
    }

    public void cancelReservation(long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId);
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation not found");
        }

        Utilisateur currentUser = userSession.getCurrentUser();
        if (currentUser == null || reservation.getPassagerId() != currentUser.getId()) {
            throw new IllegalStateException("Unauthorized to cancel this reservation");
        }

        // Restore available seats
        Covoiturage trip = covoiturageRepository.findById(reservation.getTrajetId());
        trip.setNombrePlaces(trip.getNombrePlaces() + 1);
        covoiturageRepository.update(trip);

        reservationRepository.delete(reservationId);
    }

    public List<Reservation> getUserReservations() {
        Utilisateur currentUser = userSession.getCurrentUser();
        if (currentUser == null) {
            throw new IllegalStateException("User not logged in");
        }
        return reservationRepository.getReservationsByUserId(currentUser.getId());
    }

    public void updateReservationStatus(long reservationId, ReservationStatus status) {
        Reservation reservation = reservationRepository.findById(reservationId);
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation not found");
        }

        reservation.setStatut(status);
        reservationRepository.update(reservation);
    }

    public List<Reservation> getTripReservations(long tripId) {
        return reservationRepository.getReservationsByCovoiturageId(tripId);
    }
}