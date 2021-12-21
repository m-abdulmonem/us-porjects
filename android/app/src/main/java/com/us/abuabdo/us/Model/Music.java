package com.us.abuabdo.us.Model;

public class Music {

    private int id;
    private int userID;
    private String songName;
    private String songDesc;
    private String albumName;
    private String albumDesc;
    private String albumDate;
    private String albumImage;
    private Boolean albumLoved;

    public Music(String albumName, String albumDate, String albumImage, Boolean albumLoved) {
        this.albumName = albumName;
        this.albumDate = albumDate;
        this.albumImage = albumImage;
        this.albumLoved = albumLoved;
    }

    public Music(int id, int userID, String songName, String songDesc, String albumName, String albumDesc, String albumDate, String albumImage, Boolean albumLoved) {
        this.id = id;
        this.userID = userID;
        this.songName = songName;
        this.songDesc = songDesc;
        this.albumName = albumName;
        this.albumDesc = albumDesc;
        this.albumDate = albumDate;
        this.albumImage = albumImage;
        this.albumLoved = albumLoved;
    }

    public Music(int id, String songName, String songDesc, String albumName, String albumDesc, String albumDate, String albumImage, Boolean albumLoved) {
        this.id = id;
        this.songName = songName;
        this.songDesc = songDesc;
        this.albumName = albumName;
        this.albumDesc = albumDesc;
        this.albumDate = albumDate;
        this.albumImage = albumImage;
        this.albumLoved = albumLoved;
    }

    public int getId() {
        return id;
    }

    public int getUserID() {
        return userID;
    }

    public String getSongName() {
        return songName;
    }

    public String getSongDesc() {
        return songDesc;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getAlbumDesc() {
        return albumDesc;
    }

    public String getAlbumDate() {
        return albumDate;
    }

    public Boolean getAlbumLoved() {
        return albumLoved;
    }

    public String getAlbumImage() {
        return albumImage;
    }
}
