package com.example.hakbokwe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.FirebaseApp;

public class LoginTriggerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btn = findViewById(R.id.login_btn);
        btn.setOnClickListener(v ->
                startActivityForResult(new Intent(LoginTriggerActivity.this, UsaintLoginWebPageActivity.class), 200));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 200 && resultCode == RESULT_OK) {
            startActivity(new Intent(LoginTriggerActivity.this, MainActivity.class));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}