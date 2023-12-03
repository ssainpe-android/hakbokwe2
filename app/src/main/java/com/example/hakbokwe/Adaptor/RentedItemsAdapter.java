package com.example.hakbokwe.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hakbokwe.Collections.RentedItem;
import com.example.hakbokwe.R;
import com.example.hakbokwe.RecyclerViewItemClickListener;

import java.util.ArrayList;

public class RentedItemsAdapter extends RecyclerView.Adapter<RentedItemsAdapter.ViewHolder> {
    private ArrayList<RentedItem> rentedItems;
    private RecyclerViewItemClickListener listener;

    public RentedItemsAdapter(ArrayList<RentedItem> rentedItems, RecyclerViewItemClickListener listener) {
        this.rentedItems = rentedItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_having, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(rentedItems.get(position).getName());
        holder.quantityHeld.setText(String.valueOf(rentedItems.get(position).getQuantityHeld()));
        Glide.with(holder.profile.getContext()).load(rentedItems.get(position).getProfile()).into(holder.profile);

        String name = rentedItems.get(position).getName();
        String profile = rentedItems.get(position).getProfile();
        int quantity = rentedItems.get(position).getQuantity();
        int deposit = rentedItems.get(position).getDeposit();
        String documentid =rentedItems.get(position).getDocumentid();

        holder.itemView.setOnClickListener(v -> {
            listener.onItemClick(position,name,profile,quantity,deposit,documentid);
        });
    }

    @Override
    public int getItemCount() {
        return rentedItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView profile;
        private TextView quantityHeld;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_having_name);
            profile = itemView.findViewById(R.id.item_having_profile);
            quantityHeld = itemView.findViewById(R.id.item_having_quantity);
        }
    }
}