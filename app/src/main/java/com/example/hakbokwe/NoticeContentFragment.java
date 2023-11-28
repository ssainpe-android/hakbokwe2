package com.example.hakbokwe;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hakbokwe.databinding.FragmentNoticeContentBinding;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NoticeContentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoticeContentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NoticeContentFragment() {
        // Required empty public constructor
    }

    private FragmentNoticeContentBinding binding;
    int resultInt;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NoticeContentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NoticeContentFragment newInstance(String param1, String param2) {
        NoticeContentFragment fragment = new NoticeContentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                // We use a String here, but any type that can be put in a Bundle is supported
                resultInt = Integer.parseInt(result.getString("bundleKey"));
                // Do something with the result...
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNoticeContentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

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
                else if(value != null) {
                    DocumentSnapshot snapshot = value.getDocuments().get(resultInt);
                    Notice notice = snapshot.toObject(Notice.class);
                    if (notice != null) {
                        binding.noticeContentNotice.setText(notice.getType());
                        binding.noticeContentMethod.setText(notice.getTitle());
                        binding.noticeContentDay.setText(notice.getDate());
                    }
                }
            }
        });

        return view;
    }
}