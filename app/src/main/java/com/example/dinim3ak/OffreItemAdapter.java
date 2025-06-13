package com.example.dinim3ak;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OffreItemAdapter extends RecyclerView.Adapter<OffreItemAdapter.OffreViewHolder> {

    private List<OffreItem> offreList;
    private OnItemClickListener listener;

    public interface  OnItemClickListener{
        public void onItemClick(OffreItem offreItem);
    }

    public OffreItemAdapter(List<OffreItem> rideList, OnItemClickListener listener) {
        this.offreList = rideList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public OffreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.offre_item_layout, parent, false);
        return new OffreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OffreViewHolder holder, int position) {
        OffreItem ride = offreList.get(position);
        holder.driverName.setText(ride.getDriverName());
        holder.date.setText(ride.getDate());
        holder.fromCity.setText(ride.getFromCity());
        holder.fromTime.setText(ride.getFromTime());
        holder.toCity.setText(ride.getToCity());
        holder.toTime.setText(ride.getToTime());
        holder.price.setText(ride.getPrice());
        holder.nbPassager.setText((ride.getNbPassager()));

        holder.itemView.setOnClickListener(v -> listener.onItemClick(ride));
        Log.d("Bind", "User: " + ride.getId()); // Add logging

    }

    @Override
    public int getItemCount() {
        return offreList.size();
    }

    public static class OffreViewHolder extends RecyclerView.ViewHolder {

        TextView driverName, date, fromCity, fromTime, toCity, toTime, price, nbPassager;
        ImageView profileImage;

        public OffreViewHolder(@NonNull View itemView) {
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
        }
    }
}
