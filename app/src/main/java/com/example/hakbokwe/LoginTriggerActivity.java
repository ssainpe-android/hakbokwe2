package com.example.hakbokwe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

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
            Log.d("park", "Results in intent: " + data.getStringExtra("studentName") + ", " + data.getStringExtra("studentId"));
            connectUserToDatabase(data.getStringExtra("studentName"), data.getStringExtra("studentId"));
            startActivity(new Intent(LoginTriggerActivity.this, MainActivity.class));
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void connectUserToDatabase(String name, String id) {                                    // TODO 지금 유저 불러와서 클래스 저장(메모리 저장) or 마이페이지에서 세션으로 불러오기
        UserSessionManager manager = new UserSessionManager();
        try {
            manager.addUserToDatabase(name, id);                                                    // 메소드 내 중복 검사 존재
            manager.createUidInSession(this, id);
        } catch (Exception e) {
            Log.e("park", "Not a valid access to DATABASE or INTERNAL FILE.");
            finish();                                                                               //TODO CRITICAL ERROR에 대한 대응 추가 논의 ex) dialog 띄우고 앱 재시작
        }
    }
}