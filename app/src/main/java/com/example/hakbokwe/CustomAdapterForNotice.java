package com.example.hakbokwe;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterForNotice extends RecyclerView.Adapter<CustomAdapterForNotice.ViewHolder> {
    //필드
    private ArrayList<Notice> localDataSet;

    //생성자
    public CustomAdapterForNotice(ArrayList<Notice> localDataSet) {
        this.localDataSet = localDataSet;
    }

    //어댑터 오버라이드 함수
    //뷰홀더 객체 생성과 초기화
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notice, parent, false);

        return new ViewHolder(view);
    }

    //데이터를 가져와 뷰홀더 안의 내용을 채움
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        holder.type.setText(localDataSet.get(position).getType());
        holder.title.setText(localDataSet.get(position).getTitle());
        holder.date.setText(localDataSet.get(position).getDate());
    }

    //총 아이템의 개수 반환
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    //뷰홀더 클래스 정의
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //item.xml에 만든 '아이템 틀'의 요소들을 불러들어옴
        private TextView type;
        private TextView title;
        private TextView date;
        private View transitionView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.noticelist_type_tv);
            title = itemView.findViewById(R.id.noticelist_title_tv);
            date = itemView.findViewById(R.id.noticelist_date_tv);
            transitionView = itemView.findViewById(R.id.item_notice_transition);


            transitionView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        FragmentManager fragmentManager = ((FragmentActivity) itemView.getContext()).getSupportFragmentManager();
                        NoticeContentFragment noticeContentFragment = NoticeContentFragment.newInstance("param1", "param2");
                        Bundle result = new Bundle();
                        result.putString("bundleKey", String.valueOf(position));
                        fragmentManager.setFragmentResult("requestKey", result);
                        fragmentManager.beginTransaction()
                                .add(R.id.main_frm, noticeContentFragment)
                                .addToBackStack(null)
                                .commit();
                    }
                }
            });


        }
    }
}
