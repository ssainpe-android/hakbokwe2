package com.example.hakbokwe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;

import com.bumptech.glide.Glide;
import com.example.hakbokwe.databinding.ActivityRentContentBinding;

public class RentContentActivity extends AppCompatActivity {
    ActivityRentContentBinding binding;
    BottomSheetDialogFragment bottomSheetDialogFragment;
    private int deposit;
    private int quantity;
    private int howMany;
    private String documentid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRentContentBinding.inflate(getLayoutInflater());
        //뒤로가기 버튼
        back();
        //리사이클러뷰에서 물품 정보 가져오기
        getstuff_info();

        setContentView(binding.getRoot());

        //마이너스 버튼 리스너
        minusButtonLogic();

        //플러스 버튼 리스너
        plusButtonLogic();

        binding.rentContentRentalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //바텀쉿다이얼로그 띄우기
                bottomsheet_init();
                Log.d("YYC", "button clicked");
            }
        });
    }

    private void plusButtonLogic() {
        binding.rentContentPlusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(String.valueOf(binding.rentContentHowManyTv.getText())) < quantity) {
                    int n = Integer.parseInt((String) binding.rentContentHowManyTv.getText());
                    n += 1;
                    binding.rentContentHowManyTv.setText(String.valueOf(n));
                    howMany = n;
                }
            }
        });
    }

    private void minusButtonLogic() {
        binding.rentContentMinusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(String.valueOf(binding.rentContentHowManyTv.getText())) > 0) {
                    int n = Integer.parseInt((String) binding.rentContentHowManyTv.getText());
                    n -= 1;
                    binding.rentContentHowManyTv.setText(String.valueOf(n));
                    howMany = n;
                }
            }
        });
    }

    private void back() {
        binding.rentContentBackBtn.setOnClickListener(v -> {
            finish();
        });
    }

    private void bottomsheet_init() {
        bottomSheetDialogFragment = new BottomSheetDialogFragment(deposit, howMany, documentid);
        bottomSheetDialogFragment.show(getSupportFragmentManager(),bottomSheetDialogFragment.getTag());
        Log.d("YYC", howMany + "" + documentid + " 1st");
    }

    private void getstuff_info() {
        //인텐트 받기
        Intent getIntent = getIntent();
        //받은 정보 저장
        String name = getIntent.getStringExtra("name");
        String profile = getIntent.getStringExtra("profile");
        quantity = getIntent.getIntExtra("quantity",0);
        deposit = getIntent.getIntExtra("deposit",0);
        documentid = getIntent.getStringExtra("documentid");
        //받은 정보 바인딩
        binding.rentContentItemNameTv.setText(name);
        binding.rentContentItemLeftQuantity.setText(String.valueOf(quantity));
        binding.rentContentDeposit.setText(String.valueOf(deposit));
        Glide.with(this).load(profile).into(binding.rentContentItemImageIv);
    }

}