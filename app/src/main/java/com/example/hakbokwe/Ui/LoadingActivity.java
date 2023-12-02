package com.example.hakbokwe.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.hakbokwe.Common.MainActivity;
import com.example.hakbokwe.Login.LoginTriggerActivity;
import com.example.hakbokwe.Login.UserSessionManager;
import com.example.hakbokwe.R;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        startLoading();
    }

    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            UserSessionManager manager = new UserSessionManager();

            if (manager.isExistUidInSession(this)) {
                manager.isExistUserToDatabase(manager.getUidInSession(this), isExist -> {
                    if (isExist) {
                        Log.d("park", "Valid session found.");
                        startActivity(new Intent(LoadingActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Log.d("park", "Start new login.");
                        startActivity(new Intent(LoadingActivity.this, LoginTriggerActivity.class));
                        finish();
                    }
                });
            } else {
                startActivity(new Intent(LoadingActivity.this, LoginTriggerActivity.class));
            }
        }, 3000);
    }
}