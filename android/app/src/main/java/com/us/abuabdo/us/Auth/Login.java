package com.us.abuabdo.us.Auth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.us.abuabdo.us.Home;
import com.us.abuabdo.us.Model.Users;
import com.us.abuabdo.us.R;
import com.us.abuabdo.us.Vendor.Auth;
import com.us.abuabdo.us.Vendor.Constants;
import com.us.abuabdo.us.Vendor.Database.Columns.UsersTable;
import com.us.abuabdo.us.Vendor.Database.DatabaseHandler;
import com.us.abuabdo.us.Vendor.Helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private EditText user,pass;
    private TextView btnLogin;
    private int attempt = 0;
    private Users userModel;

    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        if (auth().getLoginStatus())
            startActivity(new Intent(this, Home.class));

        btnLogin = findViewById(R.id.btn_login);
        user = findViewById(R.id.forget_user);
        pass = findViewById(R.id.pass);
        userModel = new Users(this);
        databaseHandler = new DatabaseHandler(this);
        UsersTable usersTable = new UsersTable(this);

    }

    private Auth auth() {
        return new Auth(this);
    }

    public void login(View view){
        btnLogin.setText("WAIt");
        btnLogin.setClickable(false);
        request();
    }

    private void request(){
       StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.LOGIN_URL, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
               Log.d("LOG_LOGIN",response);
               btnLogin.setText("Welcome Back");
               btnLogin.setClickable(false);
               try {
                   JSONObject user = new JSONObject(response);
                   helper().toast(user.getString("username"));

                   if (user.getBoolean("loginStatus")){
                       if (!user.getBoolean("errorStatus")) {
                           auth().setLoginStatus(user.getBoolean("loginStatus"));
                           String auth;
                           if (user.getString("email").equals(""))
                               auth = user.getString("phone");
                           else
                               auth = user.getString("email");

                           int id           = user.getInt("id");
                           String username  = user.getString("username");
                           String name  = user.getString("name");
                           String cover     = user.getString("profileCover");
                           String picture   = user.getString("picture");
                           String location  = user.getString("location");
                           String date      = user.getString("date");
                           String followers = user.getString("followers");
                           String following = user.getString("following");
                           String posts     = user.getString("posts");
                           String status    = user.getString("status");
                           String ip        = user.getString("ip");
                           String grand     = user.getString("grand");

                           auth().setLogin(id,auth,username,name,followers,following,posts,date,location,cover,picture);
                           startActivity(new Intent(getApplicationContext(), Home.class));
                       }
                   }
                   helper().toast(user.getString("error"));
               } catch (JSONException e) {
                   e.printStackTrace();
               }

           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               Log.d("APP_LOG",error.toString());
           }
       }){
           @Override
           protected Map<String, String> getParams() throws AuthFailureError {
               Map<String,String> params = new HashMap<>();
               params.put("user",user.getText().toString());
               params.put("pass",pass.getText().toString());
               return params;
           }
       };
       RequestQueue requestQueue = Volley.newRequestQueue(this);
       requestQueue.add(stringRequest);
    }

    private Helper helper(){
        return new Helper(this);
    }

    public void register(View view) {
        startActivity(new Intent(this,Register.class));
    }

    public void forgetPassword(View view) {
        startActivity(new Intent(this,ForgetPassword.class));
    }
}
