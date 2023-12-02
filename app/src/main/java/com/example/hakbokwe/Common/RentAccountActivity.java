package com.example.hakbokwe.Common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import com.example.hakbokwe.R;
import com.example.hakbokwe.databinding.ActivityRentAccountBinding;

public class RentAccountActivity extends AppCompatActivity {
    ActivityRentAccountBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityRentAccountBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        //뒤로가기 버튼
        back();
        getdeposit();
        finishrent();
        setContentView(binding.getRoot());
    }

    private void finishrent() {
        binding.rentAccountLayout.setOnClickListener(v -> {
            finish();
            loadFragment(new HomeFragment());
        });
    }

    private void back() {
        binding.rentAccountBackBtn.setOnClickListener(v -> {
            finish();
        });
    }

    private void getdeposit() {
        Intent getIntent = getIntent();
        int deposit = getIntent.getIntExtra("deposit",0);
        binding.rentAccountPriceTv.setText(String.valueOf(deposit));
    }
    private void loadFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_frm, fragment)
                .commit();
    }
}