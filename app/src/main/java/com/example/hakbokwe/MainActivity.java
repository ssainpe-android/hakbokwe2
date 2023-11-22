/*
2023-11-15: Class UsaintLogin
2023-11-20: UsaintLogin Class -> LoginWebPageActivity
2023-11-22: layout과 merging complete.
 */

package com.example.hakbokwe;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.hakbokwe.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadFragment(new HomeFragment());
        binding.mainBnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.mypageFragment) {
                    loadFragment(new MypageFragment());
                } else if (item.getItemId() == R.id.homeFragment) {
                    loadFragment(new HomeFragment());
                }
                return false;
            }
        });
    }

    private void loadFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frm, fragment).commit();
    }
}