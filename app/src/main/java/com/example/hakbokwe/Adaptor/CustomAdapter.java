package com.example.hakbokwe.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hakbokwe.R;
import com.example.hakbokwe.RecyclerViewItemClickListener;
import com.example.hakbokwe.Collections.Stuff;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{
    //필드
    private ArrayList<Stuff> localDataSet;
    private RecyclerViewItemClickListener listener;
    //생성자
    public CustomAdapter(ArrayList<Stuff> localDataSet,RecyclerViewItemClickListener listener) {
        this.localDataSet = localDataSet;
        this.listener = listener;
    }

    //어댑터 오버라이드 함수
    //뷰홀더 객체 생성과 초기화
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rental, parent, false);

        return new ViewHolder(view);
    }

    //데이터를 가져와 뷰홀더 안의 내용을 채움
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        holder.name.setText(localDataSet.get(position).getName());
        holder.quantity.setText(String.valueOf(localDataSet.get(position).getQuantity()));
        Glide.with(holder.profile.getContext()).load(localDataSet.get(position).getProfile()).into(holder.profile);

        //Rent_Content 액티비티로 넘길 정보들
        String name=localDataSet.get(position).getName();
        String profile = localDataSet.get(position).getProfile();
        int quantity=localDataSet.get(position).getQuantity();
        int deposit = localDataSet.get(position).getDeposit();
        //리사이클러뷰 리서너로 정보를 넘긴다.
        holder.itemView.setOnClickListener(v -> {
            listener.onItemClick(position,name,profile,quantity,deposit);
        });
    }

    //총 아이템의 개수 반환
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    //뷰홀더 클래스 정의
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //item.xml에 만든 '아이템 틀'의 요소들을 불러들어옴
        private ImageView profile;
        private TextView name;
        private TextView quantity;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profile = itemView.findViewById(R.id.item_rental_profile);
            name = itemView.findViewById(R.id.item_rental_name);
            quantity = itemView.findViewById(R.id.item_rental_quantity);
        }
    }

    //검색 기능 구현
    public void setItems(ArrayList<Stuff> list){
        localDataSet = list;
        notifyDataSetChanged();
    }
}
