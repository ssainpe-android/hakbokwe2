package com.example.hakbokwe;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hakbokwe.databinding.FragmentHomeBinding;
import com.example.hakbokwe.databinding.FragmentMypageBinding;

public class MypageFragment extends Fragment {
    private FragmentMypageBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMypageBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        binding.mypageProfileFaceIv.setImageResource(R.drawable.profileface);
        return view;
    }
}