package com.example.dinim3ak;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class ReservationDemandeItemAdapter extends RecyclerView.Adapter<ReservationDemandeItemAdapter.ViewHolder> {

    private List<ReservationDemandeItem> rideList;
    private OnButtonClickListener buttonClickListener;
    private boolean showButtons;
    private Context context;
    public void setDemandesList(List<ReservationDemandeItem> reservationDemandeItems) {
        this.rideList.clear();
        this.rideList.addAll(reservationDemandeItems);
        notifyDataSetChanged();
    }

    public interface OnButtonClickListener {
        void onButtonClick(ReservationDemandeItem item, int position);
    }

    public ReservationDemandeItemAdapter(Context context, boolean showButtons, OnButtonClickListener listener) {
        this.rideList = rideList;
        this.showButtons = showButtons;
        this.buttonClickListener = listener;
        this.context = context;
        this.rideList = new ArrayList<>();
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
            holder.btn.setVisibility(View.VISIBLE);
            if(item.isSelected()){
                holder.btn.setText("Refuser");
                holder.btn.setIconResource(R.drawable.refuser_icon);
                int color = ContextCompat.getColor(context, R.color.red);
                ColorStateList colorStateList = ColorStateList.valueOf(color);
                holder.btn.setIconTint(colorStateList);
            }else{
                holder.btn.setText("Accepter");
                holder.btn.setIconResource(R.drawable.ic_verified);
                int color = ContextCompat.getColor(context, R.color.green);
                ColorStateList colorStateList = ColorStateList.valueOf(color);
                holder.btn.setIconTint(colorStateList);
            }

            holder.btn.setOnClickListener(v -> {
                buttonClickListener.onButtonClick(item, position);
            });
        } else {
            holder.btn.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return rideList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, date, depart, heureDepart, destination, heureDestination, nbrPlaces;
        MaterialButton btn;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.driver_name);
            date = itemView.findViewById(R.id.ride_date);
            depart = itemView.findViewById(R.id.from_city);
            heureDepart = itemView.findViewById(R.id.from_time);
            destination = itemView.findViewById(R.id.to_city);
            heureDestination = itemView.findViewById(R.id.to_time);
            nbrPlaces = itemView.findViewById(R.id.nbplaces);
            btn = itemView.findViewById(R.id.refuse_button);
        }
    }
}
