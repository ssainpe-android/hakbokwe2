package com.example.hakbokwe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.CalendarView;

import com.example.hakbokwe.databinding.ActivityRentContentBinding;

public class RentContentActivity extends AppCompatActivity {
    ActivityRentContentBinding binding;
    BottomSheetDialogFragment bottomSheetDialogFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bottomSheetDialogFragment = new BottomSheetDialogFragment();
        super.onCreate(savedInstanceState);
        binding = ActivityRentContentBinding.inflate(getLayoutInflater());
        //뒤로가기 버튼
        binding.rentContentBackBtn.setOnClickListener(v -> {
            finish();
        });
        //바텀쉿다이얼로그 띄우기
        binding.rentContentRentalBtn.setOnClickListener(v -> {
            bottomSheetDialogFragment.show(getSupportFragmentManager(),bottomSheetDialogFragment.getTag());
        });
        setContentView(binding.getRoot());
    }

}