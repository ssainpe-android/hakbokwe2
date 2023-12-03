package com.example.hakbokwe.Collections;

public class RentedItem {

    private String name;
    private String profile;
    private int quantity;
    private int deposit;

    private int quantityHeld;

    private String documentid;

    public RentedItem() {
    }

    public RentedItem(String name, String profile, int quantity, int deposit, String documentid) {
        this.name = name;
        this.profile = profile;
        this.quantity = quantity;
        this.deposit = deposit;
        this.documentid = documentid;
    }

    public String getName() {
        return name;
    }

    public String getProfile() {
        return profile;
    }

    public int getQuantityHeld() {
        return quantityHeld;
    }

    public String getDocumentid() {
        return documentid;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getDeposit() {
        return deposit;
    }
}
