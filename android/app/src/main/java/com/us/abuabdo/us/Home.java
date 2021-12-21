package com.us.abuabdo.us;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.us.abuabdo.us.Auth.Login;
import com.us.abuabdo.us.Fragment.ChatFragment;
import com.us.abuabdo.us.Fragment.ConversationFragment;
import com.us.abuabdo.us.Fragment.HomeFragment;

import com.us.abuabdo.us.Fragment.MusicFragment;
import com.us.abuabdo.us.Fragment.ProfileFragment;
import com.us.abuabdo.us.Vendor.Auth;
import com.us.abuabdo.us.Vendor.Helper;

public class Home extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    RelativeLayout musicContainer;
    Fragment fragment = null;
    private boolean getMusic = false;
    float x1,x2,y1,y2;
    boolean chatSwiped;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        if (!auth().getLoginStatus())
            startActivity(new Intent(this, Login.class));

        musicContainer = findViewById(R.id.home_music_container);
        if (getMusic)
            musicContainer.setVisibility(View.VISIBLE);
        else
            musicContainer.setVisibility(View.GONE);

        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
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
                    startActivity(new Intent(this,Camera.class));
                else if (x1>x2){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ConversationFragment()).commit();

//                    ChatFragment chatFragment = new ChatFragment();
//                    if (!fragment.equals(chatFragment))
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ChatFragment()).commit();
//                    chatSwiped = true;
                }
            break;
        }
        return false;
    }
    private Helper helper() {
        return new Helper(this);
    }

    public void setFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()){

                        case R.id.navigation_home:
                            fragment = new HomeFragment();
                            break;
                        case R.id.music:
                            fragment = new MusicFragment();
                            break;
                        case R.id.user_profile:
                            fragment = new ProfileFragment();
                            break;

                        case R.id.chat:
                            if (!chatSwiped)
                                fragment = new ChatFragment();
                            break;

                        case R.id.add:
                            startActivity(new Intent(getApplicationContext(),Camera.class));
                            fragment = null;
                            break;

                    }
                    if (fragment != null)
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
                    return true;
                }
            };


//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        Intent welcome = new Intent(this,Welcome.class);
//        welcome.putExtra("Close",true);
//        startActivity(welcome);
//    }

    private Auth auth(){
        return new Auth(this);
    }

}
