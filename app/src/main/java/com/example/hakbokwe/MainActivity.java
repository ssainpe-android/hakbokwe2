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
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.hakbokwe.databinding.ActivityMainBinding;
import com.example.hakbokwe.databinding.FragmentNoticeListBinding;
import com.google.android.material.navigation.NavigationBarView;

import android.content.Context;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    DrawerLayout drawer;
    long initTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //드로우 레이아웃 객체 저장
        drawer = findViewById(R.id.drawer);
        //첫번째는 홈화면
        loadFragment(new HomeFragment());
        //드로어(옆에 메뉴) 리스너
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

    //뒤로가기 버튼을 눌렀을 때 어떻게 화면이 전환되는지
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.main_frm);

        if(keyCode == KeyEvent.KEYCODE_BACK && !(currentFragment instanceof HomeFragment) && !(currentFragment instanceof NoticeContentFragment)) {
            loadFragment(new HomeFragment());
        }
        else if(keyCode == KeyEvent.KEYCODE_BACK && drawer.isOpen()) {
            drawer.close();
        }
        else if(keyCode == KeyEvent.KEYCODE_BACK && !(drawer.isOpen()) && !(currentFragment instanceof NoticeContentFragment)) {
            if(System.currentTimeMillis() - initTime > 3000) {
                showToast("종료하려면 한 번 더 누르세요.");
                initTime = System.currentTimeMillis();
            }
            else {
                finishAffinity();
                System.runFinalization();
                System.exit(0);
            }
        }
        else if (keyCode == KeyEvent.KEYCODE_BACK && currentFragment instanceof NoticeContentFragment) {
            loadFragment(new NoticeListFragment());
        }

        return true;
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}