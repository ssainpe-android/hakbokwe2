package com.example.hakbokwe.Adaptor;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import java.util.ArrayList;
import java.util.List;

public class ViewPagerFragmentAdapter extends FragmentStateAdapter {    //CategoryFragment 전환에 사용.
    private List<Fragment> fragments;

    public ViewPagerFragmentAdapter(FragmentActivity activity, List<Fragment> fragments) {
        super(activity);
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
