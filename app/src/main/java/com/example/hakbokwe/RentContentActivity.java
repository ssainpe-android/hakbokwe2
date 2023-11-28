package com.example.hakbokwe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.hakbokwe.databinding.ActivityRentContentBinding;

public class RentContentActivity extends AppCompatActivity {
    ActivityRentContentBinding binding;
    BottomSheetDialogFragment bottomSheetDialogFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bottomSheetDialogFragment = new BottomSheetDialogFragment();
        super.onCreate(savedInstanceState);
        binding = ActivityRentContentBinding.inflate(getLayoutInflater());

        binding.rentContentRentalBtn.setOnClickListener(v -> {
            bottomSheetDialogFragment.show(getSupportFragmentManager(),bottomSheetDialogFragment.getTag());
        });
        setContentView(binding.getRoot());
    }

}