package com.dinim3ak.services.trip;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

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
import com.example.dinim3ak.DemandeActivity;
import com.example.dinim3ak.OffreItem;
import com.example.dinim3ak.ReservationDemandeItem;
import com.example.dinim3ak.ReservationItem;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

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
                reservation.setNombrePlaces(nbPlaces);

                reservationRepository.insert(reservation);
                callback.onResult(true);
                // Update available seats
                //covoiturage.setNombrePlacesReservees(covoiturage.getNombrePlacesReservees() + nbPlaces);
                //covoiturageRepository.update(covoiturage);
            }
        });
    }

    @SuppressLint("CheckResult")

    public LiveData<Boolean> cancelReservation(LifecycleOwner lifecycleOwner, long reservationId) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();

        reservationRepository.findById(reservationId).observe(lifecycleOwner, reservation -> {
            if (reservation == null) {
                Log.e("CANCELING", "Reservation not found.");
                result.postValue(false);
                return;
            }

            covoiturageRepository.findById(reservation.getTrajetId()).observe(lifecycleOwner, covoiturage -> {
                if (covoiturage == null) {
                    Log.e("CANCELING", "Covoiturage not found.");
                    result.postValue(false);
                    return;
                }

                Executors.newSingleThreadExecutor().execute(() -> {
                    try {
                        covoiturage.setNombrePlaces(covoiturage.getNombrePlaces() + reservation.getNombrePlaces());
                        covoiturageRepository.update(covoiturage);
                        reservation.setStatut(ReservationStatus.ANNULEE);
                        reservationRepository.update(reservation);
                        Log.i("CANCELING", "Reservation Status "+reservation.getStatut());

                        Log.i("CANCELING", "Success");
                        result.postValue(true);
                    } catch (Exception e) {
                        Log.e("CANCELING", "Error", e);
                        result.postValue(false);
                    }
                });
            });
        });

        return result;
    }


    public LiveData<List<ReservationItem>> getMyReservationItems() {
        // Step 1: Get LiveData<List<Reservation>> for the current user
        // No Transformations.switchMap needed for currentUserId anymore
        Utilisateur currentUser = userSession.getCurrentUser();
        LiveData<List<Reservation>> reservationsLiveData = reservationRepository.getReservationsByUserId(currentUser.getId());


        // Step 2: Create LiveData for all Covoiturages needed based on current reservations
        LiveData<List<Covoiturage>> allCovoituragesLiveData = Transformations.switchMap(reservationsLiveData, reservations -> {
            if (reservations == null || reservations.isEmpty()) {
                return new MutableLiveData<>(new ArrayList<>());
            }
            List<Long> covoiturageIds = reservations.stream()
                    .map(Reservation::getTrajetId)
                    .distinct()
                    .collect(Collectors.toList());
            return covoiturageRepository.getCovoituragesByIds(covoiturageIds);
        });

        // Step 3: Combine both sources using MediatorLiveData
        // No need to observe currentUserDetailsLiveData now
        MediatorLiveData<List<ReservationItem>> finalReservationItemsLiveData = new MediatorLiveData<>();

        finalReservationItemsLiveData.addSource(reservationsLiveData, reservations ->
                combineAndEmit(reservations, allCovoituragesLiveData.getValue(), currentUser.getPrenom(), finalReservationItemsLiveData)
        );
        finalReservationItemsLiveData.addSource(allCovoituragesLiveData, covoiturages ->
                combineAndEmit(reservationsLiveData.getValue(), covoiturages, currentUser.getPrenom(), finalReservationItemsLiveData)
        );

        return finalReservationItemsLiveData;
    }

    // Helper method to perform the actual combination and set value
    // Now directly takes a String for userName
    private void combineAndEmit(
            List<Reservation> reservations,
            List<Covoiturage> covoiturages,
            String userName, // <-- Direct String, no User object
            MutableLiveData<List<ReservationItem>> targetLiveData) {

        // Ensure all required data has arrived before attempting to combine
        // No need to check for currentUser != null as userName is assumed present
        if (reservations == null || covoiturages == null) {
            return;
        }

        // Create map for efficient Covoiturage lookup
        Map<Long, Covoiturage> covoiturageMap = covoiturages.stream()
                .collect(Collectors.toMap(Covoiturage::getId, c -> c));

        List<ReservationItem> reservationItems = new ArrayList<>();
        for (Reservation reservation : reservations) {
            Covoiturage matchedCovoiturage = covoiturageMap.get(reservation.getTrajetId());

            // Create the ReservationItem only if the necessary Covoiturage component is found
            if (matchedCovoiturage != null) {
                // Pass the currentUserName directly
                reservationItems.add(reservationToItem(reservation, matchedCovoiturage, userName));
            } else {
                // Handle cases where Covoiturage might be missing
                System.out.println("Missing Covoiturage for reservation " + reservation.getId()); // Or Log.w()
            }
        }
        targetLiveData.setValue(reservationItems);
    }
    public ReservationItem reservationToItem(Reservation reservation, Covoiturage covoiturage, String passagerPrenom){
        long id = reservation.getId();
        String date = covoiturage.getDateDepart().toString();
        String villeDepart = covoiturage.getVilleDepart();
        String heureDepart = covoiturage.getHeureDepart().toString();
        String villeArrivee = covoiturage.getVilleArrivee();
        String heureArrivee = (LocalTime.ofSecondOfDay(covoiturage.getHeureDepart().toSecondOfDay() +
                (int)(covoiturage.getDureeEstimee()*60))).toString();
        String statut = reservation.getStatut().toString();
        String nbPlaces = String.valueOf(reservation.getNombrePlaces());
        return new ReservationItem(id, passagerPrenom, date, villeDepart,
                heureDepart, villeArrivee, heureArrivee, statut, nbPlaces);

    }

    public ReservationDemandeItem reservationToDemandeItem(Reservation reservation, Covoiturage covoiturage, String passagerPrenom){
        long id = reservation.getId();
        String date = covoiturage.getDateDepart().toString();
        String villeDepart = covoiturage.getVilleDepart();
        String heureDepart = covoiturage.getHeureDepart().toString();
        String villeArrivee = covoiturage.getVilleArrivee();
        String heureArrivee = (LocalTime.ofSecondOfDay(covoiturage.getHeureDepart().toSecondOfDay() +
                (int)(covoiturage.getDureeEstimee()*60))).toString();
        String statut = reservation.getStatut().toString();
        String nbPlaces = String.valueOf(reservation.getNombrePlaces());
        return new ReservationDemandeItem(id, passagerPrenom, date, villeDepart,
                heureDepart, villeArrivee, heureArrivee, nbPlaces);

    }

    public LiveData<List<ReservationItem>> getUserActiveReservations() {
        LiveData<List<ReservationItem>> reservationItemsLiveData = getMyReservationItems();
        return Transformations.switchMap(reservationItemsLiveData,
                reservationItems -> {
                    Log.d("Bind", "Returned item count: "+reservationItems.size());

                    List<ReservationItem> activeReservations = reservationItems.stream().filter(reservationItem ->
            {
                return ReservationStatus.valueOf(reservationItem.status) == ReservationStatus.EN_ATTENTE;
            }).collect(Collectors.toList());
            return new MutableLiveData<>(activeReservations);
        });
    }

    public void getUserNonActiveReservations(LifecycleOwner lifecycleOwner, Callback<List<ReservationItem>> callback) {
        getMyReservationItems().observe(lifecycleOwner, reservationItems -> {
            List<ReservationItem> activeReservations = reservationItems.stream().filter(reservationItem ->
            {return !(ReservationStatus.valueOf(reservationItem.status) == ReservationStatus.EN_ATTENTE);
            }).collect(Collectors.toList());
            callback.onResult(activeReservations);
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

    public LiveData<List<ReservationDemandeItem>> getTripReservations(LifecycleOwner lifecycleOwner, long tripId, ReservationStatus status) {
        LiveData<List<Reservation>> allReservationLiveData = reservationRepository.getReservationsByCovoiturageId(tripId);
        LiveData<List<Reservation>> reservationLiveData = Transformations.switchMap(allReservationLiveData, allReservations -> {
            List<Reservation> reservations = new ArrayList<>();
            if (allReservations != null) {
                for(Reservation reservation : allReservations){
                    if(reservation.getStatut() == status){
                        reservations.add(reservation);
                    }
                }
            }
            return new MutableLiveData<>(reservations);
        });
        LiveData<List<Utilisateur>> passagersLiveData = Transformations.switchMap(reservationLiveData, reservations -> {
            List<Long> userIds = new ArrayList<>();
            if (reservations != null) {
                userIds = reservations.stream().map(Reservation::getPassagerId).collect(Collectors.toList());
            }
            return utilisateurRepository.findUtilisateursByIds(userIds);
        });

        LiveData<Covoiturage> covoiturageLiveData = covoiturageRepository.findById(tripId);

        MediatorLiveData<List<ReservationDemandeItem>> mediatorLiveData = new MediatorLiveData<>();

        mediatorLiveData.addSource(reservationLiveData, reservations -> {
            combine(reservations, covoiturageLiveData.getValue(), passagersLiveData.getValue(), mediatorLiveData);
        });

        mediatorLiveData.addSource(covoiturageLiveData, covoiturage -> {
            combine(reservationLiveData.getValue(), covoiturage, passagersLiveData.getValue(), mediatorLiveData);
        });

        mediatorLiveData.addSource(passagersLiveData, passagers -> {
            combine(reservationLiveData.getValue(), covoiturageLiveData.getValue(), passagers, mediatorLiveData);
        });

        return mediatorLiveData;
    }

    private void combine(
            List<Reservation> reservations,
            Covoiturage covoiturage,
            List<Utilisateur> passagers,
            MutableLiveData<List<ReservationDemandeItem>> targetLiveData) {

        if (reservations == null || covoiturage == null || passagers == null) {
            return;
        }

        Map<Long, Utilisateur> passagerMap = passagers.stream()
                .collect(Collectors.toMap(Utilisateur::getId, passager -> passager));

        List<ReservationDemandeItem> reservationItems = new ArrayList<>();
        for (Reservation reservation : reservations) {
            Utilisateur passager = passagerMap.get(reservation.getPassagerId());
            if (passager != null) {
                reservationItems.add(reservationToDemandeItem(reservation, covoiturage, passager.getPrenom()));
            }
        }
        targetLiveData.setValue(reservationItems);
    }

    public void accepterReservation(LifecycleOwner lifecycleOwner, long reservationId){
        updateReservationStatus(lifecycleOwner, reservationId, ReservationStatus.ACCEPTEE);
    }

    public void refuserReservation(LifecycleOwner lifecycleOwner, long reservationId){
        updateReservationStatus(lifecycleOwner, reservationId, ReservationStatus.REFUSEE);
    }

    public void finaliserReservation(LifecycleOwner lifecycleOwner, long reservationId) {
        updateReservationStatus(lifecycleOwner, reservationId, ReservationStatus.FINALISEE);
    }

}