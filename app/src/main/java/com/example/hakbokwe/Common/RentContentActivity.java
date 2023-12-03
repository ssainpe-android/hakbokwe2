package com.example.hakbokwe.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.hakbokwe.Ui.BottomSheetDialogFragment;
import com.example.hakbokwe.databinding.ActivityRentContentBinding;

public class RentContentActivity extends AppCompatActivity {
    ActivityRentContentBinding binding;
    BottomSheetDialogFragment bottomSheetDialogFragment;
    private int deposit;
    private int quantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRentContentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //뒤로가기 버튼
        back();
        //리사이클러뷰에서 물품 정보 가져오기
        getstuff_info();
        //바텀쉿다이얼로그 띄우기
        bottomsheet_init();
        //플러스 버튼 로직
        plusbtnlogic();
        //마이너스 버튼 로직
        minusbtnlogic();
        Log.d("hojin","oncreate()");

    }

    private void minusbtnlogic() {
        binding.rentContentMinusBtn.setOnClickListener(v -> {
            if(Integer.parseInt(String.valueOf(binding.rentContentHowManyTv.getText()))>0){
                int n = Integer.parseInt((String)binding.rentContentHowManyTv.getText());
                n-=1;
                binding.rentContentHowManyTv.setText(String.valueOf(n));
            }
        });
    }

    private void plusbtnlogic() {
        binding.rentContentPlusBtn.setOnClickListener(v -> {
            if(Integer.parseInt(String.valueOf(binding.rentContentHowManyTv.getText())) < quantity){
                int n = Integer.parseInt((String)binding.rentContentHowManyTv.getText());
                n+=1;
                binding.rentContentHowManyTv.setText(String.valueOf(n));
            }
        });
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
        quantity = getIntent.getIntExtra("quantity",0);
        deposit = getIntent.getIntExtra("deposit",0);
        //받은 정보 바인딩
        binding.rentContentItemNameTv.setText(name);
        binding.rentContentItemLeftQuantity.setText(String.valueOf(quantity));
        binding.rentContentDeposit.setText(String.valueOf(deposit));
        Glide.with(this).load(profile).into(binding.rentContentItemImageIv);
    }

}