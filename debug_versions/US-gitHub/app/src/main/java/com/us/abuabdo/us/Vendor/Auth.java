package com.us.abuabdo.us.Vendor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.us.abuabdo.us.Auth.Login;
import com.us.abuabdo.us.Home;
import com.us.abuabdo.us.Welcome;

public class Auth {

    private Context context;
    final private String AUTH = "AUTH";
    final private String AUTH_STATUS = "AUTH_STATUS";

    final private String ID   = "USER_ID";
    final private String USER = "USER_NAME";
    final private String PASS = "USER_PASS";



    public Auth(Context context){
        this.context = context;
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
        editor().putBoolean(AUTH_STATUS,false);
        editor().apply();
        context.startActivity(new Intent(context, Login.class));
    }

    public void setLoginStatus(boolean loginStatus){
        editor().putBoolean(AUTH_STATUS,loginStatus);
        editor().apply();
    }

    public boolean setLogin(int id, String user, String pass){
        editor().putInt(ID,id);
        editor().putString(USER,user);
        editor().putString(PASS,pass);
        editor().apply();
        return true;
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
