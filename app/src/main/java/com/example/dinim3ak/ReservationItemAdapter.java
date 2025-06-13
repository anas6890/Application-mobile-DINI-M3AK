package com.example.dinim3ak;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ReservationItemAdapter extends RecyclerView.Adapter<ReservationItemAdapter.ReservationViewHolder> {

    private List<ReservationItem> reservationItemList;
    ReservationItemAdapter.OnCancelClickListener listener;
    public interface  OnCancelClickListener{
        public void onCancelClick(ReservationItem reservationItem);
    }
    public ReservationItemAdapter(ReservationItemAdapter.OnCancelClickListener listener) {
        reservationItemList = new ArrayList<>();
        this.listener = listener;
    }

    public void updateItems(List<ReservationItem> newItems){
        this.reservationItemList.clear();
        this.reservationItemList.addAll(newItems);
        this.notifyDataSetChanged();
    }
    public List<ReservationItem> getReservationItemList() {return reservationItemList;}
    public void setReservationItemList(List<ReservationItem> reservationItemList) {
        this.reservationItemList.clear();
        this.reservationItemList.addAll(reservationItemList);
        Log.d("Bind", "In set list count: "+ this.reservationItemList.size());
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ReservationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("Adapter", "onCreateViewHolder called");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reservation_item_layout, parent, false);
        return new ReservationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationViewHolder holder, int position) {
        ReservationItem reservationItem = reservationItemList.get(position);
        Log.d("Bind", "Binding Item: " + reservationItem);
        holder.passagerName.setText(reservationItem.passagerName);
        holder.date.setText(reservationItem.date);
        holder.fromCity.setText(reservationItem.fromCity);
        holder.fromTime.setText(reservationItem.fromTime);
        holder.toCity.setText(reservationItem.toCity);
        holder.toTime.setText(reservationItem.toTime);
        holder.status.setText(reservationItem.status);
        holder.nombrePlaces.setText(reservationItem.nombrePlaces);
        holder.cancel.setOnClickListener(v -> {listener.onCancelClick(reservationItem);});
        Log.d("Bind", "User: " + reservationItem.id); // Add logging

    }

    @Override
    public int getItemCount() {
        return reservationItemList.size();
    }

    public static class ReservationViewHolder extends RecyclerView.ViewHolder {

        TextView passagerName, date, fromCity, fromTime, toCity, toTime, status, nombrePlaces;
        ImageView profileImage;
        Button cancel;

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
            cancel = itemView.findViewById(R.id.cancel_button);
        }
    }
}
