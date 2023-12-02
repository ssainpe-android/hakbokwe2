/*
주의사항
1. getSharedPreferences() 사용 시 parameter에 inflate된 context를 명시적으로 넘겨줘야 한다. or error.

TODO Path(collection, file 등) define 해주기 - 시간 날 때
TODO addUserToDatabase()에 있는 test code 참고해 add rentItems code 작성
 */

package com.example.hakbokwe.Login;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.hakbokwe.Collections.User;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

public class UserSessionManager {
    private final String FILE_KEY = "uid";

    public String getUidInSession(Context context) {
        try {
            SharedPreferences sharedPref = context.getSharedPreferences(FILE_KEY, context.MODE_PRIVATE);

            return sharedPref.getString("id", "null");
        } catch (Exception e) {
            Log.w("park", "No session found.");
        }
        return null;
    }

    public String getNameInSession(Context context) {
        try {
            SharedPreferences sharedPref = context.getSharedPreferences(FILE_KEY, context.MODE_PRIVATE);

            return sharedPref.getString("name", "null");
        } catch (Exception e) {
            Log.w("park", "No session found.");
        }
        return null;
    }

    public void createUidInSession(Context context, String name, String id) {     // data add async; if data drop happens, try to trans apply() -> commit();
        if (!isExistUidInSession(context)) {
            SharedPreferences sharedPref = context.getSharedPreferences(FILE_KEY, context.MODE_PRIVATE);
            sharedPref.edit()
                    .putString("id", id)
                    .putString("name", name)
                    .apply();
            Log.d("park", "createUidInSession(), session saved: " + sharedPref.getString("id", "null"));
        }
    }

    public boolean isExistUidInSession(@NonNull Context context) {
        try {
            SharedPreferences sharedPref = context.getSharedPreferences(FILE_KEY, context.MODE_PRIVATE);
            String id = sharedPref.getString("id", "null");

            return !id.equals("null");
        } catch (Exception e) {
            Log.w("park", "No session found.");
            return false;
        }
    }

    public void isExistUserToDatabase(String id, MyCallback callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("users").document(id).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                boolean flag = document.exists();

                callback.onCallback(flag);
            }
        });
    }

    public void addUserToDatabase(String name, String id) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        User user = new User(name);

        db.collection("users").document(id).set(user, SetOptions.merge())
                .addOnSuccessListener(unused -> {
                    Log.d("park", "addUserToDatabase(), DocumentSnapshot successfully written: " + user.getName());
                })
                .addOnFailureListener(e -> {
                    Log.e("park", "addUserToDatabase(), Error writing document: ", e);
                });

        //add default subCollections TODO document 아래 field 추가해 사용
        db.collection("users").document(id).collection("rentedItems").document();
        db.collection("users").document(id).collection("favorites").document();
    }

    public interface MyCallback {
        void onCallback(boolean flag);
    }
}