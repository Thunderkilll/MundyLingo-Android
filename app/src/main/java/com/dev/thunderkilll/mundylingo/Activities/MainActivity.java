package com.dev.thunderkilll.mundylingo.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import com.dev.thunderkilll.mundylingo.R;
import com.dev.thunderkilll.mundylingo.fragment.CoursFragment;
import com.dev.thunderkilll.mundylingo.fragment.DashboardFragment;
import com.dev.thunderkilll.mundylingo.fragment.HomeFragment;
import com.dev.thunderkilll.mundylingo.fragment.ProfileFragment;

public class MainActivity extends AppCompatActivity  {
private Toolbar toolbar ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//bech nzid houni el buttom menu elli fih les fragments mte3i w bech nseti un fragment comme par default


toolbar = findViewById(R.id.toolbar);


        BottomNavigationView navigation = findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        // load the dashboard : shop  fragment by default
        toolbar.setTitle("Home");
        loadFragment(new HomeFragment());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_shop:
                   // toolbar.setTitle("Shop");
                 fragment = new HomeFragment();
                loadFragment(fragment);
                    toolbar.setTitle("Home");
                    return true;
                   case R.id.navigation_gifts:
                    //toolbar.setTitle("My Gifts");
                   fragment = new CoursFragment();
                   loadFragment(fragment);
                    toolbar.setTitle("main courses");
                    return true;
                case R.id.navigation_cart:
                   // toolbar.setTitle("Cart");
                  //  fragment = new StoreFragment();
                    //loadFragment(fragment);
                    toolbar.setTitle("Advancement");
                    return true;
                case R.id.navigation_profile:
                    toolbar.setTitle("Profile");
                    fragment = new ProfileFragment();
                    loadFragment(fragment);
                    return true;
            }

            return false;
        }
    };

    /**
     * loading fragment into FrameLayout
     *
     * @param fragment
     */
    //function 3adeya n3aytilha bech nloadi another fragment

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void onSelect(){

        Intent intent = new Intent(MainActivity.this , MapLanguageActivity.class);
        startActivity(intent);
    }



}
