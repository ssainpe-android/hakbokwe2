/*
12-02
TODO BUG: 마이페이지에서 메뉴 띄우고 뒤로 가기 -> 메뉴 사라지는 대신 홈으로 이동됨.
TODO BUG: home 검색 키보드 띄우고 지운 뒤 layout 오류, 나갔다 들어오면 정상
TODO BUG: fragment_mypage LinearLayout이 위로 확장됨, ViewPager height이 작아 리스트 하단에서는 스크롤 안됨.
TODO Database stuff category 분류 -> collection을 여러 개
TODO addSnapshotListener( ... customAdapter.notifyDataSetChanged()) 필요 여부 파악
TODO file 이름 구체화
TODO cloud function 구현
 */

package com.example.hakbokwe.Common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.hakbokwe.Adaptor.ViewPagerFragmentAdapter;
import com.example.hakbokwe.Login.UserSessionManager;
import com.example.hakbokwe.R;
import com.example.hakbokwe.Ui.MyPageTabFragment;
import com.example.hakbokwe.databinding.FragmentMypageBinding;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MyPageFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        UserSessionManager manager = new UserSessionManager();
        String[] tabElement = new String[]{"빌린 물품", "즐겨찾기"};

        FragmentMypageBinding binding = FragmentMypageBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.mypageProfileNameTv.setText(manager.getNameInSession(getContext()));
        binding.mypageProfileSchnumberVariableTv.setText(manager.getUidInSession(getContext()));

        //ViewPager2에 TabFragments 전달(Adapter pattern)
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MyPageTabFragment(tabElement[0]));
        fragments.add(new MyPageTabFragment(tabElement[1]));
        ViewPagerFragmentAdapter adapterForViewPager = new ViewPagerFragmentAdapter(getActivity(), fragments);
        binding.mypageItemlistVp.setAdapter(adapterForViewPager);

        //TabLayout과 ViewPager2 연동
        new TabLayoutMediator(binding.mypageCategoryTl, binding.mypageItemlistVp, (tab, position) -> {
            tab.setText(tabElement[position]);
        }).attach();

        return view;
    }
}