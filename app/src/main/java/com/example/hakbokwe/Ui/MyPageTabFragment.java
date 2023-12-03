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

public class MyPageTabFragment extends Fragment implements RecyclerViewItemClickListener {
    final private String RENTEDITEMS_PATH = "rentedItems";
    final private String FAVORITES_PATH = "favorites";
    FragmentTabBinding binding;
    private int category;

    public MyPageTabFragment(String category) {
        switch (category) {
            case "빌린 물품":
                this.category = 0;
                break;
            case "즐겨찾기":
                this.category = 1;
                break;
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTabBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.tabItemlistRv.setLayoutManager(new LinearLayoutManager(getContext()));

        if (category == 0) {
            setRentedItemsTab();
        } else if (category == 1) {
            setFavoritesTab();
        }

        return view;
    }

    private void setRentedItemsTab() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String id = new UserSessionManager().getUidInSession(getContext());
        ArrayList<RentedItem> items = new ArrayList<>();

        db.collection("users").document(id).collection(RENTEDITEMS_PATH).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot document : task.getResult()) {
                            RentedItem item = document.toObject(RentedItem.class);
                            items.add(item);
                        }

                        RentedItemsAdapter adapter = new RentedItemsAdapter(items, this);
                        binding.tabItemlistRv.setAdapter(adapter);
                    } else {
                        Log.d("park", "Error getting documents: ", task.getException());
                    }
                });

        Log.d("park", "Get items from db, list size = " + items.size());
    }

    private void setFavoritesTab() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String id = new UserSessionManager().getUidInSession(getContext());
        ArrayList<Favorite> items = new ArrayList<>();

        db.collection("users").document(id).collection(FAVORITES_PATH).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot document : task.getResult()) {
                            Favorite favorite = document.toObject(Favorite.class);
                            items.add(favorite);
                        }

                        FavoritesAdapter adapter = new FavoritesAdapter(items, this);
                        binding.tabItemlistRv.setAdapter(adapter);
                    } else {
                        Log.d("park", "Error getting documents: ", task.getException());
                    }
                });

        Log.d("park", "Get items from db, list size = " + items.size());
    }

    @Override
    public void onItemClick(int position, String name, String profile ,int quantity, int deposit,String documentid){
        Intent intent = new Intent(getActivity(),RentContentActivity.class);
        intent.putExtra("name",name);
        intent.putExtra("profile",profile);
        intent.putExtra("quantity",quantity);
        intent.putExtra("deposit",deposit);
        intent.putExtra("documentid",documentid);
        startActivity(intent);
    }
}