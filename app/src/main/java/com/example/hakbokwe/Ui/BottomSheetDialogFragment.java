package com.example.hakbokwe.Ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hakbokwe.Common.RentAccountActivity;
import com.example.hakbokwe.databinding.FragmentBottomSheetDialogBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class BottomSheetDialogFragment extends com.google.android.material.bottomsheet.BottomSheetDialogFragment {
    private int deposit;
    private int howMany;
    private String documentid;
    private int initial;

    FragmentBottomSheetDialogBinding binding;
    public BottomSheetDialogFragment(int deposit, int howMany, String documentid) {
        this.deposit = deposit;
        this.howMany = howMany;
        this.documentid = documentid;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBottomSheetDialogBinding.inflate(inflater,container,false);
        //보증금 바인딩
        binding.bottomSheetDialogDeposit.setText(String.valueOf(deposit));


        Log.d("YYC", howMany + "" + documentid + " 2nd");

        // Inflate the layout for this fragment
        return  binding.getRoot();
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Firebase 초기화 코드
        FirebaseApp.initializeApp(requireContext());

        //Firestore 이용
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // 수정할 데이터의 경로
        String collectionPath = "stuff";
        String documentId = documentid;

        // 수정할 데이터
        Map<String, Object> dataToUpdate = new HashMap<>();

        //원래 수량 가져오기
        DocumentReference docRef = db.collection("stuff").document(documentId);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        // 문서가 존재할 경우 quantity 필드 가져오기
                        initial = document.getLong("quantity").intValue();
                        // 이제 intValue에 quantity 값이 들어 있음
                        Log.d("YYC", "int 값: " + initial);
                        Log.d("YYC", "int 값2: " + (initial - howMany));
                        dataToUpdate.put("quantity", initial - howMany);
                    } else {
                        // 문서가 존재하지 않을 경우 처리
                        Log.d("YYC", "해당 문서가 존재하지 않습니다.");
                    }
                } else {
                    // 작업이 실패한 경우 처리
                    Log.d("YYC", "작업 실패: ", task.getException());
                }
            }
        });

        //확인 버튼을 눌렀을 때 서버의 수량 바뀜
        binding.bottomSheetDialogCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Firestore에서 문서 업데이트
                if(howMany == 0) {
                    showToast("수량을 다시 확인해주세요");
                }
                else {
                    // Firestore에서 문서 업데이트
                    db.collection(collectionPath)
                            .document(documentId)
                            .update(dataToUpdate);
                    Intent intent = new Intent(getActivity(), RentAccountActivity.class);
                    intent.putExtra("deposit",deposit);
                    startActivity(intent);
                }
            }
        });

    }
}