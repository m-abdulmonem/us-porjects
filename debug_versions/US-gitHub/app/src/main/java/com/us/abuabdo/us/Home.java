package com.us.abuabdo.us;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.us.abuabdo.us.Fragment.HomeFragment;

import com.us.abuabdo.us.Vendor.Auth;

public class Home extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Fragment fragment = null;
    private Auth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()){

                        case R.id.navigation_home:
                            fragment = new HomeFragment();
                            break;
                    }
                    if (fragment != null)
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
                    return true;
                }
            };

}
