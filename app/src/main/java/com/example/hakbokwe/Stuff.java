package com.example.hakbokwe;

public class Stuff {
    private String profile;
    private String name;
    private int quantity;
    private int deposit;
    private String documentid;

    //생성자
    public Stuff() {}

    //혹시 몰라서 만든 생성자 -> 데이터를 쓸 때 필요할지도?
    public Stuff(String name, int quantity, String profile, String documentid) {
        this.name = name;
        this.quantity = quantity;
        this.profile = profile;
        this.documentid = documentid;
    }

    //getter, setter
    public String getProfile() {
        return profile;
    }
    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
}
