package com.us.abuabdo.us.Model;

public class Story {
    private int id;
    private int UserID;
    private String userImage;
    private String Username;
    private String[] Stories;
    private String   Story;

    public Story(int id, int userID, String userImage, String username, String story) {
        this.id = id;
        UserID = userID;
        this.userImage = userImage;
        Username = username;
        Story = story;
    }

    public Story(int id, int userID, String userImage, String username, String[] stories) {
        this.id = id;
        UserID = userID;
        this.userImage = userImage;
        Username = username;
        Stories = stories;
    }


    public int getId() {
        return id;
    }

    public int getUserID() {
        return UserID;
    }

    public String getUserImage() {
        return userImage;
    }

    public String getUsername() {
        return Username;
    }

    public String[] getStories() {
        return Stories;
    }

    public String getStory() {
        return Story;
    }
}
