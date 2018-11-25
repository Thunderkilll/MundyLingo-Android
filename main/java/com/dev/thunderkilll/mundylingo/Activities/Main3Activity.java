package com.dev.thunderkilll.mundylingo.Activities;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.dev.thunderkilll.mundylingo.R;

public class Main3Activity extends AppCompatActivity {
private DrawerLayout mDrawerLayout;
private ActionBarDrawerToggle mToggle ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer); //call our layout
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.Open,R.string.Close); //menu burger icon
        mDrawerLayout.addDrawerListener(mToggle); //add toggle to the drawer layout
        mToggle.syncState(); //synchroniser l'etat du toggle hide / show
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // set it true

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            switch (item.getItemId()) {
                case R.id.db:
                      startActivity( new Intent(this, MapLanguageActivity.class));


            }
            return true ;
        }
        return super.onOptionsItemSelected(item);
    }


    public void GoToEglishMap(View view) {
        Intent intent = new Intent(this, MapLanguageActivity.class);
        startActivity(intent);
    }
}
