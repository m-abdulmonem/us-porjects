package com.us.abuabdo.us.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.us.abuabdo.us.Home;
import com.us.abuabdo.us.R;
import com.us.abuabdo.us.Stories;


public class ConversationFragment extends Fragment {


    float x1,x2,y1,y2;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_conversation, container, false);

        return view;
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
                {
                    ChatFragment chatFragment = new ChatFragment();
                    FragmentManager fragmentManager = ((AppCompatActivity)getContext()).getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, chatFragment);
                    fragmentTransaction.commit();
                }
                else if (x1>x2)
                    startActivity(new Intent(getContext(), Stories.class));
                break;
        }
        return false;
    }


}
