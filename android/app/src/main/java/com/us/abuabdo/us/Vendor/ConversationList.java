package com.us.abuabdo.us.Vendor;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.us.abuabdo.us.Model.Conversation;

import java.io.FileInputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ConversationList {

    private Context context;

    final private String CONVERSATION = "CONVERSATION";
    final private String ID   = "USER_ID";
    final private String LAST_MSG = "LAST_MSG";
    final private String MESEAGE_COUNT = "MESSAGE_COUNT";
    final private String USERNAME = "USERNAME";
    final private String NAME = "NAME";
    final private String DATE = "DATE";
    final private String PICTURE  = "PICTURE";
    final private String CONVERSATION_LIST  = "CONVERSATION_LIST";


    public ConversationList(Context context){
        this.context = context;
    }


    public void setConversation(int id,String username, String name,String date,String picture,int msg_Count,String last_msg){
        SharedPreferences.Editor editor = sharedPreferences().edit();
        editor.putInt(ID,id);
        editor.putString(USERNAME,username);
        editor.putString(NAME,name);
        editor.putString(DATE,date);
        editor.putString(PICTURE,picture);
        editor.putString(LAST_MSG,last_msg);
        editor.putInt(MESEAGE_COUNT,msg_Count);
        editor.apply();
    }

    public void setChats(List<Conversation> mConversation){
        SharedPreferences.Editor editor = sharedPreferences().edit();
        Gson gson = new Gson();
        String json = gson.toJson(mConversation);
        editor.putString(CONVERSATION_LIST,json);
        editor.apply();
    }

    public void getChats(List<Conversation> mConversation){

        Gson gson = new Gson();
        String json = sharedPreferences().getString(CONVERSATION_LIST,null);
        Type type = new TypeToken<List<Conversation>>(){}.getType();
        mConversation = gson.fromJson(json,type);
        if (mConversation == null)
            mConversation = new ArrayList<>();
    }

    public String getUsername(){
        return sharedPreferences().getString(USERNAME,"");
    }
    public String getName(){
        return sharedPreferences().getString(NAME,"");
    }
    public String getPicture(){
        return sharedPreferences().getString(PICTURE,"");
    }
    public String getDate(){
        return sharedPreferences().getString(DATE,"");
    }

    public int getID(){
        return sharedPreferences().getInt(ID,-1);
    }

    public SharedPreferences sharedPreferences(){
        return context.getSharedPreferences(CONVERSATION, Context.MODE_PRIVATE);
    }

    public SharedPreferences.Editor editor(){
        return sharedPreferences().edit();
    }
}
