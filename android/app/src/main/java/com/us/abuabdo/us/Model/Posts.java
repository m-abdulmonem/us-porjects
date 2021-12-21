package com.us.abuabdo.us.Model;

import java.util.List;

public class Posts {

    String description;
    String date;
    String username;
    String location;
    String image;
    String userImage;
    int saved;
    int loves;
    int comment;
    int id;
    private int userID;
    private List<Story> story;

    public Posts(String username, String userImage){
        this.username = username;
        this.userImage = userImage;
    }
    public Posts(String username, String location, String date, String description, int loves, int comment, String image, String userImage, int saved, int id, int userID){
        this.username = username;
        this.location = location;
        this.date = date;
        this.description = description;
        this.loves = loves;
        this.comment = comment;
        this.image = image;
        this.userImage = userImage;
        this.saved = saved;
        this.id = id;
        this.userID = userID;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getUsername() {
        return username;
    }

    public String getLocation() {
        return location;
    }
    public String getImage(){
        return image;
    }
    public int getLoves() {
        return loves;
    }

    public int getComment() {
        return comment;
    }

    public int getSaved(){
        return saved;
    }

    public String getUserImage() {
        return userImage;
    }

    public int getPostID() {
        return id;
    }

    public int getUserID() {
        return userID;
    }

    public List<Story> getStory() {
        return story;
    }

    public void setStory(List<Story> story) {
        this.story = story;
    }
}
