package com.us.abuabdo.us;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.us.abuabdo.us.Fragment.ConversationFragment;
import com.us.abuabdo.us.Fragment.HomeFragment;
import com.us.abuabdo.us.Fragment.PhotoFragment;
import com.us.abuabdo.us.Fragment.VideoFragment;


public class Camera extends AppCompatActivity {

    float x1,x2,y1,y2;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
        back = findViewById(R.id.back);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Home.class));
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.camera_container,new PhotoFragment()).commit();
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
                if (x1<x2) {
                    startActivity(new Intent(getApplicationContext(),Home.class));
                }
                else if (x1>x2){
                    getSupportFragmentManager().beginTransaction().replace(R.id.camera_container,new VideoFragment()).commit();
                }
                break;
        }
        return false;
    }
}
