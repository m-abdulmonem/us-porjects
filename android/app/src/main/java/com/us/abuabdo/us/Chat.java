package com.us.abuabdo.us;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.us.abuabdo.us.Model.Message;
import com.us.abuabdo.us.Vendor.Auth;
import com.us.abuabdo.us.Vendor.Constants;
import com.us.abuabdo.us.Vendor.Database.SingleTon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chat extends AppCompatActivity {

    LinearLayout user_toolbar;
    RecyclerView recyclerView;
    EditText msg_box;
    List<Message> mMessages;
    ImageView send_btn;
    Intent intent;
    int userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        user_toolbar = findViewById(R.id.user_toolbar);
        recyclerView = findViewById(R.id.messages_recycler_view);
        msg_box = findViewById(R.id.msg_box);
        send_btn = findViewById(R.id.send_btn);

        userID =intent.getIntExtra("userID",-1);

        user_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!msg_box.getText().toString().equals(""))
                    sendMessages(auth().getID(),userID,msg_box.getText().toString());
            }
        });
    }

    public void sendMessages(final int sender, final int receiver, final String message){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.MESSAGES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("APP_LOG",response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("messages");
                    if (jsonObject.getBoolean("successful_sending"))
                        msg_box.setText("");
                    for (int i =0; i<= jsonArray.length();i++){
                        Message dbMessage = new Message(sender, receiver, message);
                    }
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
                params.put("sender",String.valueOf(sender));
                params.put("receiver",String.valueOf(receiver));
                params.put("message",message);
                return params;
            }
        };
        SingleTon.getInstance(Chat.this).addToRequestQueue(stringRequest);
    }
    public void readMessages(final int me, final int friend){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.READ_MESSAGES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("APP_LOG",response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Message message = new Message();
                    if (message.getSender() == me && message.getReceiver() == friend);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        SingleTon.getInstance(this).addToRequestQueue(stringRequest);
    }

    public Auth auth(){
        return new Auth(this);
    }
}
