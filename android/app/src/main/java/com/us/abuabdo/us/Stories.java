package com.us.abuabdo.us;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;
import com.us.abuabdo.us.Vendor.Constants;
import com.us.abuabdo.us.Vendor.Database.SingleTon;
import com.us.abuabdo.us.Vendor.Helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import jp.shts.android.storiesprogressview.StoriesProgressView;

public class Stories extends AppCompatActivity implements StoriesProgressView.StoriesListener{

    StoriesProgressView storiesProgressView;
    ImageView imageView;
    CircleImageView userImage;
    TextView username,date;
    float x1,x2,y1,y2;
    private static final int PROGRESS_COUNTE = 6;
    private int counter = 0;
    private String[] resources = new String[]{
          "https://images.pexels.com/photos/67636/rose-blue-flower-rose-blooms-67636.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
          "https://images.pexels.com/photos/60597/dahlia-red-blossom-bloom-60597.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
          "https://images.pexels.com/photos/736230/pexels-photo-736230.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
          "https://images.pexels.com/photos/1086178/pexels-photo-1086178.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
          "https://images.pexels.com/photos/1820567/pexels-photo-1820567.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
          "https://images.pexels.com/photos/133472/pexels-photo-133472.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
    };
    private final long[] durations = new long[]{
            500L,1000L,1500,4000L,5000L,1000,
    };
    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()){
                case MotionEvent.ACTION_DOWN:
                    pressTime = System.currentTimeMillis();
                    storiesProgressView.pause();
                    return false;
                case MotionEvent.ACTION_UP:
                    long now =System.currentTimeMillis();
                    return limit<now-pressTime;
            }
            return false;
        }
    };
    long pressTime = 0L;
    long limit = 500L;
    int userID;
    boolean storiesProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stories);


        Intent intentUserID = getIntent();
        userID = intentUserID.getIntExtra("userID",-1);
        getImages();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        storiesProgressView = findViewById(R.id.stories);
        storiesProgressView.setStoriesCount(PROGRESS_COUNTE); // <- set stories
        storiesProgressView.setStoryDuration(4000L); // <- set a story duration
        storiesProgressView.setStoriesListener(this); // <- set listener
        storiesProgressView.startStories(counter); // <- start progress
        imageView = findViewById(R.id.image);
        userImage = findViewById(R.id.userImageStories);
//        imageView.setImageResource(resources[counter]);

        Picasso.with(this).load(resources[counter]).into(imageView);


        Picasso.with(this).load(resources[0]).into(userImage);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storiesProgressView.pause();
            }
        });
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                storiesProgressView.resume();
                return false;
            }
        });

        View reverse = findViewById(R.id.reverse);
        reverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storiesProgressView.reverse();
            }
        });
        reverse.setOnTouchListener(onTouchListener);
        View skip = findViewById(R.id.skipe);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storiesProgressView.skip();
            }
        });
        skip.setOnTouchListener(onTouchListener);

    }
    public boolean onTouchEvent(MotionEvent touchEvent){
        switch (touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if (x1<x2)
                    startActivity(new Intent(this,Home.class));
//                else if (x1>x2)
//                    startActivity(new Intent(this,Story.class));
                break;
        }
        return false;
    }
    @Override
    public void onNext() {
        Picasso.with(this).load(resources[++counter]).into(imageView);
//        imageView.setImageResource(resources[++counter]);
    }

    @Override
    public void onPrev() {
        // Call when finished revserse animation.
        if ((counter-1)<0)return;
        Picasso.with(this).load(resources[--counter]).into(imageView);
//        imageView.setImageResource(resources[--counter]);
    }

    @Override
    public void onComplete() {
        counter = 0;
        startActivity(new Intent(getApplicationContext(),Home.class));
    }

    @Override
    protected void onDestroy() {
        // Very important !
        storiesProgressView.destroy();
        startActivity(new Intent(getApplicationContext(),Home.class));
        super.onDestroy();
    }


    private void getImages(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.STORY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("APP_LOG",response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("images");

                    resources = jsonArray.toString().split(",");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("APP_LOG",error.toString());
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("userID",String.valueOf(userID));
                return params;
            }
        };

        SingleTon.getInstance(this).addToRequestQueue(stringRequest);
    }
    private Helper helper(){
        return new Helper(this);
    }
}
