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
import com.us.abuabdo.us.Auth.Login;
import com.us.abuabdo.us.Auth.Register;
import com.us.abuabdo.us.R;
import com.us.abuabdo.us.Vendor.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ResetPassword extends AppCompatActivity {

    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password);

        password = findViewById(R.id.newpassword);
    }

    private void request(){
        StringRequest stringRequest =new StringRequest(Request.Method.POST, Constants.FORGET_PASSWORD_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("APP_LOG",response);
                try{
                    JSONObject jsonObject =new JSONObject(response);

                }catch (JSONException error){

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
                params.put("pass",password.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void restPassword(View view) {
        request();
    }

    public void login(View view) {
        startActivity(new Intent(this, Login.class));
    }

    public void register(View view) {
        startActivity(new Intent(this, Register.class));
    }
}
