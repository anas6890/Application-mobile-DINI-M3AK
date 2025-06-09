package com.dinim3ak.services.trip;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dinim3ak.model.Utilisateur;
import com.example.dinim3ak.DetailsCovoiturageActivity;
import com.example.dinim3ak.R;

import java.util.List;

public class PassagerAdapter extends RecyclerView.Adapter<PassagerAdapter.PassagerViewHolder> {

    private List<Utilisateur> passagers;

    public PassagerAdapter(List<Utilisateur> passagers) {
        this.passagers = passagers;
    }

    public PassagerAdapter(List<Utilisateur> passagers, long id, DetailsCovoiturageActivity detailsCovoiturageActivity) {

    }

    @NonNull
    @Override
    public PassagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_passager, parent, false);
        return new PassagerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PassagerViewHolder holder, int position) {
        Utilisateur passager = passagers.get(position);
        String nomComplet = passager.getPrenom() + " " + passager.getNom();
        holder.nomPassagerTextView.setText(nomComplet);
    }

    @Override
    public int getItemCount() {
        return passagers.size();
    }

    public static class PassagerViewHolder extends RecyclerView.ViewHolder {
        TextView nomPassagerTextView;

        public PassagerViewHolder(@NonNull View itemView) {
            super(itemView);
            nomPassagerTextView = itemView.findViewById(R.id.nomPassager);
        }
    }
}
