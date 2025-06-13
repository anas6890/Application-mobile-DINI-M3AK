package com.example.dinim3ak;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReservationItemAdapter extends RecyclerView.Adapter<ReservationItemAdapter.ReservationViewHolder> {

    private List<ReservationItem> reservationItemList;

    public ReservationItemAdapter(List<ReservationItem> reservationItemList) {
        this.reservationItemList = reservationItemList;
    }

    @NonNull
    @Override
    public ReservationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reservation_item_layout, parent, false);
        return new ReservationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationViewHolder holder, int position) {
        ReservationItem reservationItem = reservationItemList.get(position);
        holder.passagerName.setText(reservationItem.passagerName);
        holder.date.setText(reservationItem.date);
        holder.fromCity.setText(reservationItem.fromCity);
        holder.fromTime.setText(reservationItem.fromTime);
        holder.toCity.setText(reservationItem.toCity);
        holder.toTime.setText(reservationItem.toTime);
        holder.status.setText(reservationItem.status);
        holder.nombrePlaces.setText(reservationItem.nombrePlaces);
    }

    @Override
    public int getItemCount() {
        return reservationItemList.size();
    }

    public static class ReservationViewHolder extends RecyclerView.ViewHolder {

        TextView passagerName, date, fromCity, fromTime, toCity, toTime, status, nombrePlaces;
        ImageView profileImage;

        public ReservationViewHolder(@NonNull View itemView) {
            super(itemView);
            passagerName = itemView.findViewById(R.id.passagerName);
            date = itemView.findViewById(R.id.ride_date);
            fromCity = itemView.findViewById(R.id.from_city);
            fromTime = itemView.findViewById(R.id.from_time);
            toCity = itemView.findViewById(R.id.to_city);
            toTime = itemView.findViewById(R.id.to_time);
            status = itemView.findViewById(R.id.status_text);
            nombrePlaces = itemView.findViewById(R.id.nbplaces);
            profileImage = itemView.findViewById(R.id.profile_image); // Optional: Set image if needed
        }
    }
}
