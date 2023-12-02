package com.example.hakbokwe.Common;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import com.example.hakbokwe.Adaptor.CustomAdapterForNotice;
import com.example.hakbokwe.Collections.Notice;
import com.example.hakbokwe.databinding.FragmentNoticeListBinding;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class NoticeListFragment extends Fragment {
    private FragmentNoticeListBinding binding;

    ArrayList<Notice> dataSet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNoticeListBinding.inflate(inflater, container, false);
        View view =binding.getRoot();

        dataSet = new ArrayList<>();

        //RecyclerView와 연결
        RecyclerView recyclerView = binding.noticelistRecyclerViewRv;

        //LayoutManager 객체 생성 후 RecylcerView 객체와 연결
        binding.noticelistRecyclerViewRv.setLayoutManager(new LinearLayoutManager(getContext()));

        //CustomAdapter 객체 생성 후 RecyclerView 객체와 연결
        CustomAdapterForNotice customAdapterForNotice = new CustomAdapterForNotice(dataSet);
        binding.noticelistRecyclerViewRv.setAdapter(customAdapterForNotice);

        // Firebase 초기화 코드
        FirebaseApp.initializeApp(requireContext());

        //Firestore 이용
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("notice").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.d("TAG", "Listen failed.");
                    return;
                }

                if(value != null) {
                    dataSet.clear();
                    for (DocumentSnapshot snapshot : value.getDocuments()) {
                        Notice notice = snapshot.toObject(Notice.class);
                        if (notice != null) {
                            dataSet.add(notice);
                        }
                    }
                    customAdapterForNotice.notifyDataSetChanged();
                }
            }
        });

        return view;
    }
}