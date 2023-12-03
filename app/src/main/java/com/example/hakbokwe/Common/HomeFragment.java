package com.example.hakbokwe.Common;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.hakbokwe.Adaptor.ViewPagerFragmentAdapter;
import com.example.hakbokwe.Collections.Stuff;
import com.example.hakbokwe.Ui.HomeTabFragment;
import com.example.hakbokwe.Ui.MyPageTabFragment;
import com.example.hakbokwe.databinding.FragmentHomeBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment{
    FragmentHomeBinding binding;
    ViewPagerFragmentAdapter adapterForViewPager;
    HomeTabFragment tabFragment;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String[] tabElement = new String[]{"전체", "행사", "체육", "생활"};

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        //ViewPager2에 TabFragments 전달(Adapter pattern)
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeTabFragment(tabElement[0]));
        fragments.add(new HomeTabFragment(tabElement[1]));
        fragments.add(new HomeTabFragment(tabElement[2]));
        fragments.add(new HomeTabFragment(tabElement[3]));
        adapterForViewPager = new ViewPagerFragmentAdapter(getActivity(), fragments);
        binding.homeItemlistVp.setAdapter(adapterForViewPager);

        //TabLayout과 ViewPager2 연동
        new TabLayoutMediator(binding.homeCategoryTl, binding.homeItemlistVp, (tab, position) -> {
            tab.setText(tabElement[position]);
        }).attach();

        //현재 선택된 탭 리턴
        binding.homeCategoryTl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int selectedTabIndex = tab.getPosition();
                Fragment selectedFragment = adapterForViewPager.createFragment(selectedTabIndex);

                if (selectedFragment instanceof HomeTabFragment) {
                    tabFragment = (HomeTabFragment) selectedFragment;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        //검색 기능 구현
        binding.homeSearchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchText = binding.homeSearchEt.getText().toString();
                updateSearchResults(searchText);
            }
        });

        return view;
    }

    private void updateSearchResults(String searchText) {
        if (tabFragment != null) {
            tabFragment.updateSearchResults(searchText);
        }
    }


}
