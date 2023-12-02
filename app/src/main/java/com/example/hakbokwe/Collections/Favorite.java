package com.example.hakbokwe.Collections;

public class Favorite {
    private String name;
    private String profile;
    private int quantity;
    private int deposit;

    public Favorite() {
    }

    public Favorite(String name, String profile, int quantity, int deposit) {
        this.name = name;
        this.profile = profile;
        this.quantity = quantity;
        this.deposit = deposit;
    }

    public String getName() {
        return name;
    }

    public String getProfile() {
        return profile;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getDeposit() {
        return deposit;
    }
}
