/*
11-30
TODO customAdaptor 이름 구체화하기, usaintloginwebPageActivity 이름 usaint 빼기
TODO addSnapshotListener( ... customAdapter.notifyDataSetChanged()) 필요 여부 파악
TODO cloud function 구현
TODO category viewPager, Listener 구현
 */

package com.example.hakbokwe.Common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.hakbokwe.Login.UserSessionManager;
import com.example.hakbokwe.R;
import com.example.hakbokwe.Ui.TabFragment;
import com.example.hakbokwe.Adaptor.ViewPagerFragmentAdapter;
import com.example.hakbokwe.databinding.FragmentMypageBinding;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MyPageFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String[] tabElement = new String[]{"빌린 물품", "즐겨찾기"};
        UserSessionManager manager = new UserSessionManager();

        FragmentMypageBinding binding = FragmentMypageBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.mypageProfileNameTv.setText(manager.getNameInSession(getContext()));
        binding.mypageProfileSchnumberVariableTv.setText(manager.getUidInSession(getContext()));

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new TabFragment(tabElement[0]));
        fragments.add(new TabFragment(tabElement[1]));

        ViewPagerFragmentAdapter adapterForViewPager = new ViewPagerFragmentAdapter(getActivity(), fragments);
        binding.mypageItemlistVp.setAdapter(adapterForViewPager);

        new TabLayoutMediator(binding.mypageCategoryTl, binding.mypageItemlistVp, (tab, position) -> {
            tab.setText(tabElement[position]);
        }).attach();

        return view;
    }
}