package com.example.dinim3ak;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReservationDemandeItemAdapter extends RecyclerView.Adapter<ReservationDemandeItemAdapter.ReservationDemandeViewHolder> {

    private List<ReservationDemandeItem> demandesList;
    private ReservationDemandeItemAdapter.OnActionButtonClickListener listener;

    public interface  OnActionButtonClickListener{
        public void onActionButtonClick(ReservationDemandeItem reservationDemandeItem, int position, boolean isDemandeAccepted);
    }

    public ReservationDemandeItemAdapter(List<ReservationDemandeItem> demandesList, ReservationDemandeItemAdapter.OnActionButtonClickListener listener) {
        this.demandesList = demandesList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ReservationDemandeItemAdapter.ReservationDemandeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.offre_item_layout, parent, false);
        return new ReservationDemandeItemAdapter.ReservationDemandeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationDemandeItemAdapter.ReservationDemandeViewHolder holder, int position) {
        ReservationDemandeItem demande = demandesList.get(position);
        holder.driverName.setText(demande.getDriverName());
        holder.date.setText(demande.getDate());
        holder.fromCity.setText(demande.getFromCity());
        holder.fromTime.setText(demande.getFromTime());
        holder.toCity.setText(demande.getToCity());
        holder.toTime.setText(demande.getToTime());
        holder.nbPassager.setText((demande.getNbPassager()));

        holder.accept.setOnClickListener(v -> listener.onActionButtonClick(demande, position, true));
        holder.refuse.setOnClickListener(v -> listener.onActionButtonClick(demande, position, false));
    }

    @Override
    public int getItemCount() {
        return demandesList.size();
    }

    public static class ReservationDemandeViewHolder extends RecyclerView.ViewHolder {

        TextView driverName, date, fromCity, fromTime, toCity, toTime, price, nbPassager;
        ImageView profileImage;

        Button accept, refuse;
        public ReservationDemandeViewHolder(@NonNull View itemView) {
            super(itemView);
            driverName = itemView.findViewById(R.id.driver_name);
            date = itemView.findViewById(R.id.ride_date);
            fromCity = itemView.findViewById(R.id.from_city);
            fromTime = itemView.findViewById(R.id.from_time);
            toCity = itemView.findViewById(R.id.to_city);
            toTime = itemView.findViewById(R.id.to_time);
            price = itemView.findViewById(R.id.ride_price);
            nbPassager = itemView.findViewById(R.id.passenger_count);
            profileImage = itemView.findViewById(R.id.profile_image); // Optional: Set image if needed
            accept = itemView.findViewById(R.id.accept_button);
            refuse = itemView.findViewById(R.id.refuse_button);
        }
    }
}
