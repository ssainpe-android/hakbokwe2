package com.example.hakbokwe.Ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.hakbokwe.Adaptor.FavoritesAdapter;
import com.example.hakbokwe.Adaptor.RentedItemsAdapter;
import com.example.hakbokwe.Collections.Favorite;
import com.example.hakbokwe.Collections.RentedItem;
import com.example.hakbokwe.Common.RentContentActivity;
import com.example.hakbokwe.Login.UserSessionManager;
import com.example.hakbokwe.RecyclerViewItemClickListener;
import com.example.hakbokwe.databinding.FragmentTabBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class TabFragment extends Fragment implements RecyclerViewItemClickListener {
    private String category;
    ArrayList<RentedItem> rentedItems = new ArrayList<>();
    ArrayList<Favorite> favorites = new ArrayList<>();

    public TabFragment(String category) {
        this.category = category;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentTabBinding binding = FragmentTabBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.tabItemlistRv.setLayoutManager(new LinearLayoutManager(getContext()));

        if (category.equals("빌린 물품")) {
            getRentedItemsFromDatabase();   //class field dataset로 바로 저장됨.
            RentedItemsAdapter adapter = new RentedItemsAdapter(rentedItems, this);
            binding.tabItemlistRv.setAdapter(adapter);
        } else if (category.equals("즐겨찾기")) {
            getFavoriteItemsFromDatabase();   //class field dataset로 바로 저장됨.
            FavoritesAdapter adapter = new FavoritesAdapter(favorites, this);
            binding.tabItemlistRv.setAdapter(adapter);
        }

        return view;
    }

    private void getRentedItemsFromDatabase() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String id = new UserSessionManager().getUidInSession(getContext());

        db.collection("users").document(id).collection("rentedItems")
                .addSnapshotListener((value, error) -> {
                    if (error != null) {
                        Log.d("TAG", "Listen failed.");
                        return;
                    }
                    if (value != null) {
                        rentedItems.clear();
                        for (DocumentSnapshot snapshot : value.getDocuments()) {
                            RentedItem rentedItem = snapshot.toObject(RentedItem.class);
                            if (rentedItem != null)
                                rentedItems.add(rentedItem);
                        }
                    }
                });
    }

    private void getFavoriteItemsFromDatabase() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String id = new UserSessionManager().getUidInSession(getContext());

        db.collection("users").document(id).collection("favorites")
                .addSnapshotListener((value, error) -> {
                    if (error != null) {
                        Log.d("TAG", "Listen failed.");
                        return;
                    }
                    if (value != null) {
                        favorites.clear();
                        for (DocumentSnapshot snapshot : value.getDocuments()) {
                            Favorite favorite = snapshot.toObject(Favorite.class);
                            if (favorite != null)
                                favorites.add(favorite);
                        }
                    }
                });
    }
    @Override
    public void onItemClick(int position, String name, String profile ,int quantity, int deposit){
        Intent intent = new Intent(getActivity(),RentContentActivity.class);
        intent.putExtra("name",name);
        intent.putExtra("profile",profile);
        intent.putExtra("quantity",quantity);
        intent.putExtra("deposit",deposit);
        startActivity(intent);
    }
}