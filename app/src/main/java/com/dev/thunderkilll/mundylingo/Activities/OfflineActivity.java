package com.dev.thunderkilll.mundylingo.Activities;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import com.dev.thunderkilll.mundylingo.Helpers.InternetConnectivityObserver;
import com.dev.thunderkilll.mundylingo.R;

public class OfflineActivity extends AppCompatActivity {

    private ImageButton offline_start;
    ConstraintLayout offline_layout;
    private boolean mIsOnline = true;

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
                    while (waited < 1000) {
                        sleep(100);
                        waited += 100;
                    }
                    //start animation
                    Animation anim = AnimationUtils.loadAnimation(OfflineActivity.this, R.anim.alpha);
                    anim.reset();
                    anim = AnimationUtils.loadAnimation(OfflineActivity.this, R.anim.fade_out);
                    anim.reset();
                    offline_start.clearAnimation();
                    offline_start.startAnimation(anim);
                    GotoListPrefs();

                } catch (InterruptedException e) {
                    // do nothing
                } finally {

                }

            }
        };
        offlineThread.start();
    }


    public void GotoListPrefs() {
        offline_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OfflineActivity.this, CoursPrefActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

        InternetConnectivityObserver.get().setConsumer(new InternetConnectivityObserver.Consumer() {
            @Override
            public void accept(boolean internet) {

                mIsOnline = internet;

                if (internet) {
                    Intent i = new Intent(OfflineActivity.this, LoginActivity.class);
                    startActivity(i);
                } else {
//stay here
                }
            }
        });
        InternetConnectivityObserver.get().stop();
        InternetConnectivityObserver.get().start();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}
