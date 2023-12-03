package com.example.hakbokwe;

public interface RecyclerViewItemClickListener {
    void showCustomDialog(int position, String name, String profile ,int quantity, int deposit, String documentid);
    void onItemClick(int position, String name, String profile ,int quantity, int deposit, String documentid);
}
