package com.example.hakbokwe.Common;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;
import com.example.hakbokwe.Ui.BottomSheetDialogFragment;
import com.example.hakbokwe.databinding.ActivityRentContentBinding;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

public class RentContentActivity extends AppCompatActivity {
    ActivityRentContentBinding binding;
    BottomSheetDialogFragment bottomSheetDialogFragment;
    private int deposit;
    private int quantity;
    private int howMany;
    private String documentid;
    private String name;
    private String profile;

    private CalendarDay startDate;
    private CalendarDay endDate;
    private RadioGroup radioGroup;

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

        //캘린더에서 시작일과 종료일 받기 startDate와 endDate에 받음 이변수는 스트링이 아니라 메소드로 년월일 추출해야함
        binding.rentContentCalender.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(MaterialCalendarView widget, CalendarDay date, boolean selected) {
                // 선택된 날짜를 처리합니다.
                if (startDate == null || endDate != null) {
                    // 시작일이 선택되지 않았거나 이미 종료일이 선택된 경우
                    startDate = date;
                    endDate = null;
                } else if (startDate != null && endDate == null) {
                    // 시작일이 선택되었고 종료일이 선택되지 않은 경우
                    endDate = date;
                }
            }
        });
        // 선택한 방문시간 받기 selectedText에 시간정보가 담겨있음
        binding.rentContentRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // 선택한 라디오버튼의 ID를 가져옴
                RadioButton selectedRadioButton = findViewById(checkedId);

                if (selectedRadioButton != null) {
                    // 선택한 라디오버튼의 텍스트를 가져옴
                    String selectedText = selectedRadioButton.getText().toString();
                }
            }
        });
        binding.rentContentRentalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //바텀쉿다이얼로그 띄우기
                bottomsheet_init();
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
        bottomSheetDialogFragment = new BottomSheetDialogFragment(deposit, howMany, documentid, name, profile);
        bottomSheetDialogFragment.show(getSupportFragmentManager(),bottomSheetDialogFragment.getTag());
    }

    private void getstuff_info() {
        //인텐트 받기
        Intent getIntent = getIntent();
        //받은 정보 저장
        name = getIntent.getStringExtra("name");
        profile = getIntent.getStringExtra("profile");
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