package com.dev.thunderkilll.mundylingo.Activities;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import com.dev.thunderkilll.mundylingo.R;

public class OfflineActivity extends AppCompatActivity {

    private ImageButton offline_start;
    ConstraintLayout offline_layout;

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    Thread offlineThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);
        offline_start = findViewById(R.id.offline_start);
        offline_layout = findViewById(R.id.offline_layout);

        startOfflineAnimation();
    }


    public void startOfflineAnimation() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        offline_layout.clearAnimation();
        offline_layout.startAnimation(anim);
        anim = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        anim.reset();


        offlineThread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited <1000) {
                        sleep(100);
                        waited += 100;
                    }
             //start animation
                    Animation anim = AnimationUtils.loadAnimation(OfflineActivity.this, R.anim.alpha);
                    anim.reset();
                    anim = AnimationUtils.loadAnimation(OfflineActivity.this , R.anim.fade_out);
                    anim.reset();
                    offline_start.clearAnimation();
                    offline_start.startAnimation(anim);


                } catch (InterruptedException e) {
                    // do nothing
                } finally {

                }

            }
        };
        offlineThread.start();
    }
}
