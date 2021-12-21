package com.us.abuabdo.us.Model;

public class UsersSearch {

    private int ID;
    private String username;
    private String bio;
    private String image;

    public UsersSearch(int ID, String username, String bio, String image) {
        this.ID = ID;
        this.username = username;
        this.bio = bio;
        this.image = image;
    }

    public int getID() {
        return ID;
    }

    public String getUsername() {
        return username;
    }

    public String getBio() {
        return bio;
    }

    public String getImage() {
        return image;
    }
}
