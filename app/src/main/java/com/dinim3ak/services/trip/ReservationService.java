package com.dinim3ak.services.trip;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;

import com.dinim3ak.data.repositories.CovoiturageRepository;
import com.dinim3ak.data.repositories.ReservationRepository;
import com.dinim3ak.data.repositories.UtilisateurRepository;
import com.dinim3ak.data.session.UtilisateurSession;
import com.dinim3ak.model.Covoiturage;
import com.dinim3ak.model.Reservation;
import com.dinim3ak.model.ReservationStatus;
import com.dinim3ak.model.Utilisateur;
import com.dinim3ak.services.Callback;
import com.dinim3ak.services.user.UtilisateurService;
import com.example.dinim3ak.OffreItem;
import com.example.dinim3ak.ReservationItem;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {
    private ReservationRepository reservationRepository;
    private CovoiturageRepository covoiturageRepository;
    private UtilisateurRepository utilisateurRepository;
    private UtilisateurSession userSession;

    public ReservationService(Context context) {
        reservationRepository = new ReservationRepository(context);
        covoiturageRepository = new CovoiturageRepository(context);
        utilisateurRepository = new UtilisateurRepository(context);
        userSession = UtilisateurSession.getInstance(context);
    }

    public void reserver(LifecycleOwner lifecycleOwner, long tripId, int nbPlaces, Callback<Boolean> callback) {
        Utilisateur currentUser = userSession.getCurrentUser();
        if (currentUser == null) {
            throw new IllegalStateException("User not logged in");
        }

        covoiturageRepository.findById(tripId).observe(lifecycleOwner, covoiturage -> {
            if (covoiturage == null) {
                throw new IllegalArgumentException("Trip not found");
            }
            if (covoiturage.getNombrePlaces()-covoiturage.getNombrePlacesReservees() < nbPlaces) {
                callback.onResult(false);
            }else {

                Reservation reservation = new Reservation();
                reservation.setPassagerId(currentUser.getId());
                reservation.setTrajetId(tripId);
                reservation.setDateReservation(LocalDate.now());
                reservation.setStatut(ReservationStatus.EN_ATTENTE);

                reservationRepository.insert(reservation);
                callback.onResult(true);
                // Update available seats
                //covoiturage.setNombrePlacesReservees(covoiturage.getNombrePlacesReservees() + nbPlaces);
                //covoiturageRepository.update(covoiturage);
            }
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

    public void reservationToItem(LifecycleOwner lifecycleOwner, Reservation reservation, Callback<ReservationItem> callback){
        utilisateurRepository.findById(reservation.getPassagerId()).observe(lifecycleOwner, passager -> {
            covoiturageRepository.findById(reservation.getTrajetId()).observe(lifecycleOwner, covoiturage -> {
                String passagerName = passager.getPrenom();
                String date = covoiturage.getDateDepart().toString();
                String villeDepart = covoiturage.getVilleDepart();
                String heureDepart = covoiturage.getHeureDepart().toString();
                String villeArrivee = covoiturage.getVilleArrivee();
                String heureArrivee = (LocalTime.ofSecondOfDay(covoiturage.getHeureDepart().toSecondOfDay() +
                        (int)(covoiturage.getDureeEstimee()*60))).toString();
                String statut = reservation.getStatut().toString();
                String nbPlaces = String.valueOf(covoiturage.getNombrePlaces());
                callback.onResult(new ReservationItem(passagerName, date, villeDepart,
                        heureDepart, villeArrivee, heureArrivee, statut, nbPlaces));
            });
        });
    }

    public void getUserActiveReservations(LifecycleOwner lifecycleOwner, Callback<List<ReservationItem>> callback) {
        Utilisateur currentUser = userSession.getCurrentUser();
        if (currentUser == null) {
            throw new IllegalStateException("User not logged in");
        }
        reservationRepository.getReservationsByUserId(currentUser.getId()).observe(lifecycleOwner, reservations -> {
            List<ReservationItem> reservationItemList = new ArrayList<>();

            for(Reservation reservation : reservations){
                Log.i("RESERVATION", reservation.getStatut().toString());
                if(reservation.getStatut() == ReservationStatus.EN_ATTENTE || reservation.getStatut() == ReservationStatus.ACCEPTEE){
                    reservationToItem(lifecycleOwner, reservation, reservationItemList::add);
                }
            }
            callback.onResult(reservationItemList);
        });
    }

    public void getUserNonActiveReservations(LifecycleOwner lifecycleOwner, Callback<List<ReservationItem>> callback) {
        Utilisateur currentUser = userSession.getCurrentUser();
        if (currentUser == null) {
            throw new IllegalStateException("User not logged in");
        }
        reservationRepository.getReservationsByUserId(currentUser.getId()).observe(lifecycleOwner, reservations -> {
            List<ReservationItem> reservationItemList = new ArrayList<>();
            for(Reservation reservation : reservations){
                if(reservation.getStatut() != ReservationStatus.EN_ATTENTE && reservation.getStatut() != ReservationStatus.ACCEPTEE){
                    reservationToItem(lifecycleOwner, reservation, reservationItemList::add);
                }
            }
            callback.onResult(reservationItemList);
        });
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