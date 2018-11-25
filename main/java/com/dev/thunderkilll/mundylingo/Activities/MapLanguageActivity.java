package com.dev.thunderkilll.mundylingo.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.dev.thunderkilll.mundylingo.R;

import static java.lang.Thread.sleep;

public class MapLanguageActivity extends AppCompatActivity {

    ImageButton lvl1 ;
    ImageButton lvl2 ;
    ImageButton lvl3 ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_language);
        lvl1 = findViewById(R.id.level1);
        lvl2 = findViewById(R.id.level2);
        lvl3 = findViewById(R.id.level3);

        lvl2.setVisibility(View.INVISIBLE);
        lvl3.setVisibility(View.INVISIBLE);
        lvl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited <2000) {
                        sleep(100);
                        waited += 100;
                    }
                    lvl2.setVisibility(View.VISIBLE);
                } catch (InterruptedException e) {
                    // do nothing
                }

            }
        });




        lvl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited <2000) {
                        sleep(100);
                        waited += 100;
                    }
                    lvl3.setVisibility(View.VISIBLE);
                } catch (InterruptedException e) {
                    // do nothing
                }
            }
        });
    }


}
