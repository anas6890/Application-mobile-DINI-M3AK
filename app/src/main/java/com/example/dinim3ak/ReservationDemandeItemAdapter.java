package com.example.dinim3ak;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReservationDemandeItemAdapter extends RecyclerView.Adapter<ReservationDemandeItemAdapter.ViewHolder> {

    private List<ReservationDemandeItem> rideList;
    private OnButtonClickListener buttonClickListener;
    private boolean showButtons;

    public void setDemandesList(List<ReservationDemandeItem> reservationDemandeItems) {
        this.rideList.clear();
        this.rideList.addAll(reservationDemandeItems);
        notifyDataSetChanged();
    }

    public interface OnButtonClickListener {
        void onButtonClick(ReservationDemandeItem item, int position, boolean isAccepted);
    }

    public ReservationDemandeItemAdapter(List<ReservationDemandeItem> rideList, boolean showButtons, OnButtonClickListener listener) {
        this.rideList = rideList;
        this.showButtons = showButtons;
        this.buttonClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.demande_item_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ReservationDemandeItem item = rideList.get(position);

        holder.name.setText(item.getDriverName());
        holder.date.setText(item.getDate());
        holder.depart.setText(item.getFromCity());
        holder.heureDepart.setText(item.getFromTime());
        holder.destination.setText(item.getToCity());
        holder.heureDestination.setText(item.getToTime());
        holder.nbrPlaces.setText(item.getNbPassager());

        if (showButtons) {
            holder.btnAccepter.setVisibility(View.VISIBLE);
            holder.btnRefuser.setVisibility(View.VISIBLE);

            holder.btnAccepter.setOnClickListener(v -> buttonClickListener.onButtonClick(item, position, true));
            holder.btnRefuser.setOnClickListener(v -> buttonClickListener.onButtonClick(item, position, false));
        } else {
            holder.btnAccepter.setVisibility(View.GONE);
            holder.btnRefuser.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return rideList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, date, depart, heureDepart, destination, heureDestination, nbrPlaces;
        Button btnAccepter, btnRefuser;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.driver_name);
            date = itemView.findViewById(R.id.ride_date);
            depart = itemView.findViewById(R.id.from_city);
            heureDepart = itemView.findViewById(R.id.from_time);
            destination = itemView.findViewById(R.id.to_city);
            heureDestination = itemView.findViewById(R.id.to_time);
            nbrPlaces = itemView.findViewById(R.id.nbplaces);
            btnAccepter = itemView.findViewById(R.id.accept_button);
            btnRefuser = itemView.findViewById(R.id.refuse_button);
        }
    }
}
