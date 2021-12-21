package com.us.abuabdo.us.Model;

import android.graphics.Bitmap;

public class Message {


    public static final int TYPE_MESSAGE = 0;
    public static final int TYPE_LOG     = 1;
    public static final int TYPE_ACTION  = 2;

    private int mType;
    private int mMessage;
    private int sender;
    private int receiver;
    private String message;
    private String image;
    private Bitmap mImage;

    public Message() {
    }

    public Message(int sender, int receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
//        this.image = image;
    }

    public static int getTypeMessage() {
        return TYPE_MESSAGE;
    }

    public static int getTypeLog() {
        return TYPE_LOG;
    }

    public static int getTypeAction() {
        return TYPE_ACTION;
    }

    public int getmType() {
        return mType;
    }

    public void setmType(int mType) {
        this.mType = mType;
    }

    public int getmMessage() {
        return mMessage;
    }

    public void setmMessage(int mMessage) {
        this.mMessage = mMessage;
    }

    public Bitmap getmImage() {
        return mImage;
    }

    public void setmImage(Bitmap mImage) {
        this.mImage = mImage;
    }


    public int getSender() {
        return sender;
    }

    public int getReceiver() {
        return receiver;
    }

    public String getMessage() {
        return message;
    }

    public String getImage() {
        return image;
    }
}
