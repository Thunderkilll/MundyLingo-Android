package com.dev.thunderkilll.mundylingo.AboutUs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dev.thunderkilll.mundylingo.R;

public class AboutUsActivity extends AppCompatActivity {

    LinearLayout l;
    TextView textView1, textView2, textView3;
    Animation anim , anim1 ,  anim2  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        l = findViewById(R.id.about_us);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim1 = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim2 = AnimationUtils.loadAnimation(this, R.anim.alpha);

        anim.reset();
        l.clearAnimation();
        l.startAnimation(anim);

        //animation 1

        anim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        anim.reset();

        textView1.clearAnimation();
        textView1.startAnimation(anim);

        //animation 2

        anim1 = AnimationUtils.loadAnimation(this, R.anim.rotate);
        anim1.reset();

        textView2.clearAnimation();
        textView2.startAnimation(anim1);

        //animation 3

        anim2 = AnimationUtils.loadAnimation(this, R.anim.fade);
        anim2.reset();

        textView3.clearAnimation();
        textView3.startAnimation(anim2);

        anim2 = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        anim2.reset();

        textView3.clearAnimation();
        textView3.startAnimation(anim2);


    }
}
