package com.example.hakbokwe;

public class Notice {
    private String type;
    private String title;
    private String date;

    public Notice() {}
    public Notice(String type, String title, String date) {
        this.type = type;
        this.title = title;
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
