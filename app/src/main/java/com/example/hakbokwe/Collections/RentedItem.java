package com.example.hakbokwe.Collections;

public class RentedItem {
    private String name;
    private String profile;
    private int deposit;
    private String documentid;
    private int quantityHeld;

    private int quantity;

    public RentedItem(String name, String profile, int deposit, String documentid, int quantityHeld) {
        this.name = name;
        this.profile = profile;
        this.deposit = deposit;
        this.documentid = documentid;
        this.quantityHeld = quantityHeld;
    }

    public int getQuantity() {
        return quantity;
    }

    public RentedItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public String getDocumentid() {
        return documentid;
    }

    public void setDocumentid(String documentid) {
        this.documentid = documentid;
    }

    public void setQuantityHeld(int quantityHeld) {
        this.quantityHeld = quantityHeld;
    }

    public int getQuantityHeld() {
        return quantityHeld;
    }
}
