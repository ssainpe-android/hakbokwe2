package com.example.hakbokwe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.hakbokwe.databinding.ActivityRentAccountBinding;

public class RentAccountActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityRentAccountBinding binding = ActivityRentAccountBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        //뒤로가기 버튼
        binding.rentAccountBackBtn.setOnClickListener(v -> {
            finish();
        });
        setContentView(binding.getRoot());
    }
}