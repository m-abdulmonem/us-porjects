package com.us.abuabdo.us.Vendor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.us.abuabdo.us.Auth.Login;
import com.us.abuabdo.us.Home;
import com.us.abuabdo.us.Welcome;

import java.util.Locale;

public class Auth {

    private Context context;
    final private String AUTH = "AUTH";
    final private String AUTH_STATUS = "AUTH_STATUS";

    final private String ID   = "USER_ID";
//    final private String  = "USER_PASS";


    final private String USERNAME = "USERNAME";
    final private String NAME = "NAME";
    final private String FOLLOWERS = "FOLLOWERS";
    final private String FOLLOWING = "FOLLOWING";
    final private String PHOTOS = "PHOTOS";
    final private String DATE = "DATE";
    final private String LOCATION = "LOCATION";
    final private String COVER    = "COVER";
    final private String PICTURE  = "PICTURE";


    public Auth(Context context){
        this.context = context;
    }


    public void checkAuth(){
        if (getLoginStatus()){
            if (getUsername().isEmpty())
                logout();
        }
    }

    public void setID(){
        SharedPreferences.Editor editor = sharedPreferences().edit();
        editor.putInt(ID,1);
        editor.apply();
    }

    public void isLogin(){
        if (getLoginStatus()){
            if (context.getClass().getSimpleName().equals("Welcome"))
                context.startActivity(new Intent(context, Home.class));
        }else{
            if (!context.getClass().getSimpleName().equals("Welcome"))
                context.startActivity(new Intent(context, Welcome.class));
        }
    }

    public void logout(){
        SharedPreferences.Editor editor = sharedPreferences().edit();
        editor.putBoolean(AUTH_STATUS,false);
        editor.apply();
        context.startActivity(new Intent(context, Login.class));
    }

    public void setLoginStatus(boolean loginStatus){
        SharedPreferences.Editor editor = sharedPreferences().edit();
        editor.putBoolean(AUTH_STATUS,loginStatus);
        editor.apply();
    }

    public void setLogin(int id,String auth, String username, String name, String followers, String following, String photos, String date, String location, String cover,String picture){
        SharedPreferences.Editor editor = sharedPreferences().edit();
        editor.putInt(ID,id);
        editor.putString(AUTH,auth);
        editor.putString(USERNAME,username);
        editor.putString(NAME,name);
        editor.putString(FOLLOWERS,followers);
        editor.putString(FOLLOWING,following);
        editor.putString(PHOTOS,photos);
        editor.putString(DATE,date);
        editor.putString(LOCATION,location);
        editor.putString(COVER,cover);
        editor.putString(PICTURE,picture);

        editor.apply();
    }


    public String getUsername(){
        return sharedPreferences().getString(USERNAME,"");
    }
    public String getName(){
        return sharedPreferences().getString(NAME,"");
    }
    public String getFollowers(){
        return sharedPreferences().getString(FOLLOWERS,"");
    }
    public String getFollowing(){
        return sharedPreferences().getString(FOLLOWING,"");
    }
    public String getPhotos(){
        return sharedPreferences().getString(PHOTOS,"");
    }
    public String getCover(){
        return sharedPreferences().getString(COVER,"");
    }
    public String getPicture(){
        return sharedPreferences().getString(PICTURE,"");
    }
    public String getDate(){
        return sharedPreferences().getString(DATE,"");
    }
    public String getLocation(){
        return sharedPreferences().getString(LOCATION,"");
    }

    public int getID(){
       return sharedPreferences().getInt(ID,-1);
    }

    public boolean getLoginStatus(){
        return sharedPreferences().getBoolean(AUTH_STATUS,false);
    }

    public SharedPreferences sharedPreferences(){
        return context.getSharedPreferences(AUTH, Context.MODE_PRIVATE);
    }

    public SharedPreferences.Editor editor(){
        return sharedPreferences().edit();
    }

}
