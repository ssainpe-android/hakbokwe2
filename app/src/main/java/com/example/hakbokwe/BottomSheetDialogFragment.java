package com.example.hakbokwe;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hakbokwe.databinding.FragmentBottomSheetDialogBinding;

public class BottomSheetDialogFragment extends com.google.android.material.bottomsheet.BottomSheetDialogFragment {

    private int deposit;
    public BottomSheetDialogFragment(int deposit) {
        this.deposit = deposit;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentBottomSheetDialogBinding binding = FragmentBottomSheetDialogBinding.inflate(inflater,container,false);
        //보증금 바인딩
        binding.bottomSheetDialogDeposit.setText(String.valueOf(deposit));
        binding.bottomSheetDialogCheckBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),RentAccountActivity.class);
            intent.putExtra("deposit",deposit);
            startActivity(intent);
        });
        // Inflate the layout for this fragment
        return  binding.getRoot();
    }

}