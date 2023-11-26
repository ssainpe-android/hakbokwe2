/*
2023-11-15: Class UsaintLogin
2023-11-20: UsaintLogin Class -> LoginWebPageActivity
2023-11-22: layout과 merging complete.
 */

package com.example.hakbokwe;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.hakbokwe.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

import android.content.Context;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //드로우 레이아웃 객체 저장
        drawer = findViewById(R.id.drawer);
        //첫번째는 홈화면
        loadFragment(new HomeFragment());
        //옆에메뉴 리스너
        drawerlistener();
        //네비게이션 리스너
        navigationinit();
    }

    private void navigationinit() {
        binding.mainBnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.mypageFragment) {
                    loadFragment(new MypageFragment());
                } else if (item.getItemId() == R.id.homeFragment) {
                    loadFragment(new HomeFragment());
                } else if (item.getItemId() == R.id.menuFragment) {
                    drawer.open();
                }
                return false;
            }
        });
    }

    private void drawerlistener() {
        binding.drawerMenuAnnouncement.setOnClickListener(v -> {
            drawer.close();
            loadFragment(new NoticeListFragment());
        });
        binding.drawerMenuAboutus.setOnClickListener(v -> {
            drawer.close();
            loadFragment(new AboutusFragment());
        });
    }

    private void loadFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_frm, fragment)
                .commit();
    }
}