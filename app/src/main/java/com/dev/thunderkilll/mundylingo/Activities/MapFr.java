package com.dev.thunderkilll.mundylingo.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.thunderkilll.mundylingo.R;

import static java.lang.Thread.sleep;

public class MapFr extends AppCompatActivity {

    ImageButton lvl1 ;
    ImageButton lvl2 ;
    ImageButton lvl3 ;
    ImageButton lvl4 ;
    ImageButton lvl5 ;
    ImageButton btn_exit ;
    ImageView img_background , detail_blue_background;
    TextView txt_welcome_details ,txt_w_on_it;
    Button btn_start_lvl ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_fr);
        //calling UI Elements from layout
        //ImageButtons
        lvl1 = findViewById(R.id.frlevel1);
        lvl2 = findViewById(R.id.frlevel2);
        lvl3 = findViewById(R.id.frlevel3);
        lvl4 = findViewById(R.id.frlevel4);
        lvl5 = findViewById(R.id.frlevel5);
        btn_exit = findViewById(R.id.frbtn_exit);
        //partie detail popup

        img_background = findViewById(R.id.frimg_background);
        detail_blue_background = findViewById(R.id.frdetail_blue_background);
        txt_welcome_details = findViewById(R.id.frtxt_welcome_details);
        txt_w_on_it = findViewById(R.id.frtxt_w_on_it);
        btn_start_lvl = findViewById(R.id.frbtn_start_lvl);


        //initializing them as invisible or gone

        lvl5.setVisibility(View.INVISIBLE);
        lvl4.setVisibility(View.INVISIBLE);
        lvl2.setVisibility(View.INVISIBLE);
        lvl3.setVisibility(View.INVISIBLE);

        // popup elements
        btn_exit.setVisibility(View.GONE);
        btn_start_lvl.setVisibility(View.GONE);
        img_background.setVisibility(View.GONE);
        detail_blue_background.setVisibility(View.GONE);
        txt_welcome_details.setVisibility(View.GONE);
        txt_w_on_it.setVisibility(View.GONE);





        lvl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VisiblePopupDetails();
                txt_welcome_details.setText("Welcome To Level 1");
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

                VisiblePopupDetails();
                txt_welcome_details.setText("Welcome To Level 2");
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
        lvl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                VisiblePopupDetails();
                txt_welcome_details.setText("Welcome To Level 3");

                //wait 2 secondes
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited <2000) {
                        sleep(100);
                        waited += 100;
                    }
                    lvl4.setVisibility(View.VISIBLE);
                } catch (InterruptedException e) {
                    // do nothing
                }
            }
        });

        lvl4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                VisiblePopupDetails();

                txt_welcome_details.setText("Welcome To Level 4");
                //wait 2 seconds
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited <2000) {
                        sleep(100);
                        waited += 100;
                    }
                    lvl5.setVisibility(View.VISIBLE);
                } catch (InterruptedException e) {
                    // do nothing
                }
            }
        });

        lvl5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VisiblePopupDetails();
                txt_welcome_details.setText("Welcome To Level 5");


            }
        });





    }


    public void VisiblePopupDetails(){
        btn_exit.setVisibility(View.VISIBLE);
        btn_start_lvl.setVisibility(View.VISIBLE);
        img_background.setVisibility(View.VISIBLE);
        detail_blue_background.setVisibility(View.VISIBLE);
        txt_welcome_details.setVisibility(View.VISIBLE);
        txt_w_on_it.setVisibility(View.VISIBLE);
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_exit.setVisibility(View.GONE);
                btn_start_lvl.setVisibility(View.GONE);
                img_background.setVisibility(View.GONE);
                detail_blue_background.setVisibility(View.GONE);
                txt_welcome_details.setVisibility(View.GONE);
                txt_w_on_it.setVisibility(View.GONE);
            }
        });
    }


}
