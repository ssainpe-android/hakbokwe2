package com.example.hakbokwe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.hakbokwe.databinding.ActivityRentAccountBinding;

public class RentAccountActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityRentAccountBinding binding = ActivityRentAccountBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
    }
}