package com.example.hakbokwe.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.hakbokwe.Ui.BottomSheetDialogFragment;
import com.example.hakbokwe.databinding.ActivityRentContentBinding;

public class RentContentActivity extends AppCompatActivity {
    ActivityRentContentBinding binding;
    BottomSheetDialogFragment bottomSheetDialogFragment;
    private int deposit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRentContentBinding.inflate(getLayoutInflater());
        //뒤로가기 버튼
        back();
        //리사이클러뷰에서 물품 정보 가져오기
        getstuff_info();
        //바텀쉿다이얼로그 띄우기
        bottomsheet_init();
        setContentView(binding.getRoot());
    }

    private void back() {
        binding.rentContentBackBtn.setOnClickListener(v -> {
            finish();
        });
    }

    private void bottomsheet_init() {
        bottomSheetDialogFragment = new BottomSheetDialogFragment(deposit);
        binding.rentContentRentalBtn.setOnClickListener(v -> {
            bottomSheetDialogFragment.show(getSupportFragmentManager(),bottomSheetDialogFragment.getTag());
        });
    }

    private void getstuff_info() {
        //인텐트 받기
        Intent getIntent = getIntent();
        //받은 정보 저장
        String name = getIntent.getStringExtra("name");
        String profile = getIntent.getStringExtra("profile");
        int quantity = getIntent.getIntExtra("quantity",0);
        deposit = getIntent.getIntExtra("deposit",0);
        //받은 정보 바인딩
        binding.rentContentItemNameTv.setText(name);
        binding.rentContentItemLeftQuantity.setText(String.valueOf(quantity));
        binding.rentContentDeposit.setText(String.valueOf(deposit));
        Glide.with(this).load(profile).into(binding.rentContentItemImageIv);
    }

}