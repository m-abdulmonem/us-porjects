package com.us.abuabdo.us.Adapter;

public class Posts {


    String name,date,content;
    int image;

    public Posts() {
    }

    public Posts(String name, String date, String content, int image) {
        this.name = name;
        this.date = date;
        this.content = content;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

}
