package com.example.hakbokwe;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hakbokwe.databinding.FragmentHomeBinding;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements RecyclerViewItemClickListener {

    private FragmentHomeBinding binding;

    ArrayList<Stuff> dataSet;

    //검색 기능 구현
    EditText searchET;
    ArrayList<Stuff> filteredList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view =binding.getRoot();

        dataSet = new ArrayList<>();

        //RecyclerView와 연결
        RecyclerView recyclerView = binding.homeItemListRv;

        //LayoutManager 객체 생성 후 RecylcerView 객체와 연결
        binding.homeItemListRv.setLayoutManager(new LinearLayoutManager(getContext()));

        //CustomAdapter 객체 생성 후 RecyclerView 객체와 연결
        CustomAdapter customAdapter = new CustomAdapter(dataSet,this);
        binding.homeItemListRv.setAdapter(customAdapter);

        // Firebase 초기화 코드
        FirebaseApp.initializeApp(requireContext());

        //Firestore 이용
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("stuff").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.d("TAG", "Listen failed.");
                    return;
                }

                if(value != null) {
                    dataSet.clear();
                    for (DocumentSnapshot snapshot : value.getDocuments()) {
                        Stuff stuff = snapshot.toObject(Stuff.class);
                        if (stuff != null) {
                            dataSet.add(stuff);
                        }
                    }
                    customAdapter.notifyDataSetChanged();
                }
            }
        });


        //검색 기능 구현
        searchET = binding.homeSearchEt;
        filteredList = new ArrayList<>();

        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchText = searchET.getText().toString();
                filteredList.clear();

                if(searchText.equals("")){
                    customAdapter.setItems(dataSet);
                }
                else {
                    // 검색 단어를 포함하는지 확인
                    for (int i = 0; i < dataSet.size(); i++) {
                        if (dataSet.get(i).getName().toLowerCase().contains(searchText.toLowerCase())) {
                            filteredList.add(dataSet.get(i));
                        }
                        customAdapter.setItems(filteredList);
                    }
                }
            }
        });

        return view;
    }
    //리사이클러뷰의 온클릭리스너 추상메서드 구현
    public void onItemClick(int position, String name, String profile ,int quantity, int deposit){
        Intent intent = new Intent(getActivity(),RentContentActivity.class);
        intent.putExtra("name",name);
        intent.putExtra("profile",profile);
        intent.putExtra("quantity",quantity);
        intent.putExtra("deposit",deposit);
        startActivity(intent);
    }
}
