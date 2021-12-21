package com.us.abuabdo.us.Auth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.us.abuabdo.us.Home;
import com.us.abuabdo.us.R;
import com.us.abuabdo.us.Vendor.Constants;
import com.us.abuabdo.us.Vendor.Helper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ForgetPassword extends AppCompatActivity {

    EditText forget_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);

        forget_user = findViewById(R.id.forget_user);


    }

    public void forgetPassword(View view) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.FORGET_PASSWORD_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("APP_LOG_FORGET",response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean("rest"))
                        startActivity(new Intent(getApplicationContext(),ResetPassword.class));
                    else
                        helper().toast(jsonObject.getString("message"));

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
                params.put("user",forget_user.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void login(View view) {
        startActivity(new Intent(this,Login.class));
    }

    public void register(View view) {
        startActivity(new Intent(this,Register.class));
    }
    private Helper helper(){
        return new Helper(this);
    }
}
