package com.example.hakbokwe;

public class Notice {
    private String type;
    private String title;
    private String date;
    private String subtitle1;
    private String subtitle2;
    private String subtitle3;
    private String subtitle1content;
    private String subtitle2content;
    private String subtitle3content;
    private String subtitle1subcontent;
    private String subtitle2subcontent;
    private String subtitle3subcontent;

    public Notice() {}

    public Notice(String type, String title, String date, String subtitle1, String subtitle2, String subtitle3, String subtitle1content, String subtitle2content, String subtitle3content, String subtitle1subcontent, String subtitle2subcontent, String subtitle3subcontent) {
        this.type = type;
        this.title = title;
        this.date = date;
        this.subtitle1 = subtitle1;
        this.subtitle2 = subtitle2;
        this.subtitle3 = subtitle3;
        this.subtitle1content = subtitle1content;
        this.subtitle2content = subtitle2content;
        this.subtitle3content = subtitle3content;
        this.subtitle1subcontent = subtitle1subcontent;
        this.subtitle2subcontent = subtitle2subcontent;
        this.subtitle3subcontent = subtitle3subcontent;
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

    public String getSubtitle1() {
        return subtitle1;
    }

    public void setSubtitle1(String subtitle1) {
        this.subtitle1 = subtitle1;
    }

    public String getSubtitle2() {
        return subtitle2;
    }

    public void setSubtitle2(String subtitle2) {
        this.subtitle2 = subtitle2;
    }

    public String getSubtitle3() {
        return subtitle3;
    }

    public void setSubtitle3(String subtitle3) {
        this.subtitle3 = subtitle3;
    }

    public String getSubtitle1content() {
        return subtitle1content;
    }

    public void setSubtitle1content(String subtitle1content) {
        this.subtitle1content = subtitle1content;
    }

    public String getSubtitle2content() {
        return subtitle2content;
    }

    public void setSubtitle2content(String subtitle2content) {
        this.subtitle2content = subtitle2content;
    }

    public String getSubtitle3content() {
        return subtitle3content;
    }

    public void setSubtitle3content(String subtitle3content) {
        this.subtitle3content = subtitle3content;
    }

    public String getSubtitle1subcontent() {
        return subtitle1subcontent;
    }

    public void setSubtitle1subcontent(String subtitle1subcontent) {
        this.subtitle1subcontent = subtitle1subcontent;
    }

    public String getSubtitle2subcontent() {
        return subtitle2subcontent;
    }

    public void setSubtitle2subcontent(String subtitle2subcontent) {
        this.subtitle2subcontent = subtitle2subcontent;
    }

    public String getSubtitle3subcontent() {
        return subtitle3subcontent;
    }

    public void setSubtitle3subcontent(String subtitle3subcontent) {
        this.subtitle3subcontent = subtitle3subcontent;
    }
}
