package com.example.hakbokwe;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hakbokwe.databinding.FragmentHomeBinding;
import com.example.hakbokwe.databinding.ItemRentalBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    List<Rental> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        list.add(new Rental("우산", "수량: 14개", R.drawable.umbrella_icon));
        list.add(new Rental("천막", "수량:  7개", R.drawable.tent));
        list.add(new Rental("보조배터리", "수량: 20개", R.drawable.battery));
        list.add(new Rental("우산", "수량: 14개", R.drawable.umbrella_icon));
        list.add(new Rental("천막", "수량:  7개", R.drawable.tent));
        list.add(new Rental("보조배터리", "수량: 20개", R.drawable.battery));
        list.add(new Rental("우산", "수량: 14개", R.drawable.umbrella_icon));
        list.add(new Rental("천막", "수량:  7개", R.drawable.tent));
        list.add(new Rental("보조배터리", "수량: 20개", R.drawable.battery));
        list.add(new Rental("우산", "수량: 14개", R.drawable.umbrella_icon));
        list.add(new Rental("천막", "수량:  7개", R.drawable.tent));
        list.add(new Rental("보조배터리", "수량: 20개", R.drawable.battery));

        binding.homeItemlistRv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.homeItemlistRv.setAdapter(new MyAdapter());
        return view;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemRentalBinding binding;

        public MyViewHolder(@NonNull ItemRentalBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Rental rental) {
            binding.itemRentalName.setText(rental.name);
            binding.itemRentalIcon.setImageResource(rental.icon);
            binding.itemRentalQuantity.setText(rental.quatity);
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ItemRentalBinding binding = ItemRentalBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

            return new MyViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.bind(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}

