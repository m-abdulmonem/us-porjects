package com.us.abuabdo.us;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.us.abuabdo.us.Auth.Login;
import com.us.abuabdo.us.Vendor.Auth;

public class Welcome extends AppCompatActivity {

    final static int SECOND = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void run() {
                if (auth().getLoginStatus())
                    startActivity(new Intent(Welcome.this, Home.class));
                else
                    startActivity(new Intent(Welcome.this,Home.class));
            }
        }, SECOND);
    }

    private Auth auth(){
        return new Auth(this);
    }
}