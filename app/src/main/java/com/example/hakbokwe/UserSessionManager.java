/*
주의사항
1. getSharedPreferences() 사용 시 parameter에 inflate된 context를 명시적으로 넘겨줘야 한다. or error.

TODO Path(collection, file 등) define 해주기 - 시간 날 때
TODO addUserToDatabase()에 있는 test code 참고해 add rentItems code 작성
 */

package com.example.hakbokwe;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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

    public void createUidInSession(Context context, String id) {     // data add async; if data drop happens, try to trans apply() -> commit();
        if (!isExistUidInSession(context)) {
            SharedPreferences sharedPref = context.getSharedPreferences(FILE_KEY, context.MODE_PRIVATE);
            sharedPref.edit()
                    .putString("id", id)
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
        User user = new User(name, id);
        Log.d("park", "니 이름: " + user.getName()+user.getId());

        db.collection("users").document(id).set(user, SetOptions.merge())
                .addOnSuccessListener(unused -> {
                    Log.d("park", "addUserToDatabase(), DocumentSnapshot successfully written!");
                })
                .addOnFailureListener(e -> {
                    Log.e("park", "addUserToDatabase(), Error writing document: ", e);
                });

        //add rentedItem code for test
        db.collection("users").document(id).collection("rentedItems").document()
                .set(new HashMap<String, Object>() {
                         {
                             put("name", "ball");
                             put("quantity", 3);
                         }
                     }
                );
    }

    interface MyCallback {
        void onCallback(boolean flag);
    }
}