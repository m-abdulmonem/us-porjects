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
import com.us.abuabdo.us.R;
import com.us.abuabdo.us.Vendor.Auth;
import com.us.abuabdo.us.Vendor.Constants;
import com.us.abuabdo.us.Vendor.Database.Columns.UsersTable;
import com.us.abuabdo.us.Vendor.Helper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    EditText name,pass,user,username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        name = findViewById(R.id.name);
        user = findViewById(R.id.forget_user);
        username = findViewById(R.id.profile_username);
        pass = findViewById(R.id.password);
        TextView register = findViewById(R.id.txt_register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request();
            }
        });


        UsersTable users = new UsersTable(this);


    }


    private Helper helper() {
        return new Helper(this);
    }

    public void register(View view ){
//        request();
    }


    public void request(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.REGISTER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("APP_LOG",response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean loginStatus = jsonObject.getBoolean("loginStatus");
                    if (loginStatus)
                        startActivity(new Intent(Register.this,Home.class));
                    else {
                        boolean inserted = jsonObject.getBoolean("insertSuccess");
                        if (inserted) {
                            if(jsonObject.getString("message").equals("User inserted")){
                                auth().setLoginStatus(true);
                                helper().toast(jsonObject.getString("message"));
                                helper().toast(jsonObject.getString("error"));
                                startActivity(new Intent(Register.this, Home.class));
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                helper().toast(error.toString());
                Log.d("APP_LOG",error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("user",user.getText().toString());
                params.put("username",username.getText().toString());
                params.put("pass",pass.getText().toString());
                params.put("name",name.getText().toString());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        requestQueue.add(stringRequest);
    }

    public void login(View view) {
        startActivity(new Intent(this,Login.class));
    }
    private Auth auth(){
        return new Auth(this);
    }
}
