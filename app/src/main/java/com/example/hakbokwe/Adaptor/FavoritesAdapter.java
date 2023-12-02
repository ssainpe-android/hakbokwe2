package com.example.hakbokwe.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hakbokwe.Collections.Favorite;
import com.example.hakbokwe.R;
import com.example.hakbokwe.RecyclerViewItemClickListener;

import java.util.ArrayList;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {
    private ArrayList<Favorite> favorites;
    private RecyclerViewItemClickListener listener;

    public FavoritesAdapter(ArrayList<Favorite> favorites, RecyclerViewItemClickListener listener) {
        this.favorites = favorites;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rental, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(favorites.get(position).getName());
        holder.quantity.setText(String.valueOf(favorites.get(position).getQuantity()));
        Glide.with(holder.profile.getContext()).load(favorites.get(position).getProfile()).into(holder.profile);

        String name=favorites.get(position).getName();
        String profile = favorites.get(position).getProfile();
        int quantity=favorites.get(position).getQuantity();
        int deposit = favorites.get(position).getDeposit();

        holder.itemView.setOnClickListener(v -> {
            listener.onItemClick(position,name,profile,quantity,deposit);
        });
    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView profile;
        private TextView name;
        private TextView quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profile = itemView.findViewById(R.id.item_rental_profile);
            name = itemView.findViewById(R.id.item_rental_name);
            quantity = itemView.findViewById(R.id.item_rental_quantity);
        }
    }
}
