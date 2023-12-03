package com.example.hakbokwe.Ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.hakbokwe.Adaptor.CustomAdapter;
import com.example.hakbokwe.Collections.RentedItem;
import com.example.hakbokwe.Collections.Stuff;
import com.example.hakbokwe.Common.RentContentActivity;
import com.example.hakbokwe.RecyclerViewItemClickListener;
import com.example.hakbokwe.databinding.FragmentTabBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class HomeTabFragment extends Fragment implements RecyclerViewItemClickListener {
    FragmentTabBinding binding;
    private int category;
    ArrayList<Stuff> items = new ArrayList<>();


    public HomeTabFragment(String category) {
        switch (category) {
            case "전체":
                this.category = 0;
                break;
            case "행사":
                this.category = 1;
                break;
            case "체육":
                this.category = 2;
                break;
            case "생활":
                this.category = 3;
                break;
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String[] tabElement = new String[]{"전체", "행사", "체육", "생활"};

        binding = FragmentTabBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.tabItemlistRv.setLayoutManager(new LinearLayoutManager(getContext()));

        if (category == 0) {
            setAllStuffsTab();
        } else if (category == 1) {
            setStuffsTabByCategory(tabElement[1]);
        } else if (category == 2) {
            setStuffsTabByCategory(tabElement[2]);
        } else if (category == 3) {
            setStuffsTabByCategory(tabElement[3]);
        }

        return view;
    }

    @SuppressLint("NotifyDataSetChanged")
    private void setAllStuffsTab() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("stuff")
                .addSnapshotListener((value, error) -> {
                    if (error != null) {
                        Log.d("park", "Listen failed.");
                    }
                    if (value != null) {
                        items.clear();
                        for (DocumentSnapshot snapshot : value.getDocuments()) {
                            Stuff stuff = snapshot.toObject(Stuff.class);
                            if (stuff != null) {
                                items.add(stuff);
                            }
                        }

                        CustomAdapter adapter = new CustomAdapter(items, this);
                        binding.tabItemlistRv.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });
    }
    @SuppressLint("NotifyDataSetChanged")
    private void setStuffsTabByCategory(final String CATEGORY) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("stuff").whereEqualTo("category", CATEGORY)
                .addSnapshotListener((value, error) -> {
            if (error != null) {
                Log.d("park", "Listen failed.");
            }
            if (value != null) {
                items.clear();
                for (DocumentSnapshot snapshot : value.getDocuments()) {
                    Stuff stuff = snapshot.toObject(Stuff.class);
                    if (stuff != null) {
                        items.add(stuff);
                    }
                }

                CustomAdapter adapter = new CustomAdapter(items, this);
                binding.tabItemlistRv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void updateSearchResults(String searchText) {
        ArrayList<Stuff> filteredList = new ArrayList<>();

        for (Stuff item : items) {
            if (item.getName().toLowerCase().contains(searchText.toLowerCase())) {
                filteredList.add(item);
            }
        }

        CustomAdapter adapter = (CustomAdapter) binding.tabItemlistRv.getAdapter();
        if (adapter != null) {
            adapter.updateList(filteredList);
        }
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
