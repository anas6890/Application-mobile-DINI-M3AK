package com.example.dinim3ak;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReservationItemAdapter extends RecyclerView.Adapter<ReservationItemAdapter.CarpoolViewHolder> {

    private List<ReservationItem> rideList;

    public ReservationItemAdapter(List<ReservationItem> rideList) {
        this.rideList = rideList;
    }

    @NonNull
    @Override
    public CarpoolViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reservation_item_layout, parent, false);
        return new CarpoolViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarpoolViewHolder holder, int position) {
        ReservationItem ride = rideList.get(position);
        holder.driverName.setText(ride.driverName);
        holder.date.setText(ride.date);
        holder.fromCity.setText(ride.fromCity);
        holder.fromTime.setText(ride.fromTime);
        holder.toCity.setText(ride.toCity);
        holder.toTime.setText(ride.toTime);
        holder.status.setText(ride.status);
    }

    @Override
    public int getItemCount() {
        return rideList.size();
    }

    public static class CarpoolViewHolder extends RecyclerView.ViewHolder {

        TextView driverName, date, fromCity, fromTime, toCity, toTime, status;
        ImageView profileImage;

        public CarpoolViewHolder(@NonNull View itemView) {
            super(itemView);
            driverName = itemView.findViewById(R.id.driver_name);
            date = itemView.findViewById(R.id.ride_date);
            fromCity = itemView.findViewById(R.id.from_city);
            fromTime = itemView.findViewById(R.id.from_time);
            toCity = itemView.findViewById(R.id.to_city);
            toTime = itemView.findViewById(R.id.to_time);
            status = itemView.findViewById(R.id.status_text);
            profileImage = itemView.findViewById(R.id.profile_image); // Optional: Set image if needed
        }
    }
}
