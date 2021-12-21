package com.us.abuabdo.us.Model;

public class Conversation {

    private String conversation_image;
    private String conversation_username;
    private String conversation_message;
    private String conversation_msg_count;
    private String conversation_date;
    private int id;


    public Conversation(int id,String conversation_image, String conversation_username, String conversation_message, String conversation_msg_count, String conversation_date) {
        this.conversation_image = conversation_image;
        this.conversation_username = conversation_username;
        this.conversation_message = conversation_message;
        this.conversation_msg_count = conversation_msg_count;
        this.conversation_date = conversation_date;
        this.id                = id;
    }

    public String getConversation_image() {
        return conversation_image;
    }

    public String getConversation_username() {
        return conversation_username;
    }

    public String getConversation_message() {
        return conversation_message;
    }

    public String getConversation_msg_count() {
        return conversation_msg_count;
    }

    public String getConversation_date() {
        return conversation_date;
    }
    public int getID(){
        return id;
    }
}
