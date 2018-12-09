package com.dev.thunderkilll.mundylingo.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dev.thunderkilll.mundylingo.R;

public class LanguageActivity extends AppCompatActivity {
    Button btn_eng ;
    Button btn_fr ;
    Button btn_sp ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        //declaration des buttons
        btn_eng = findViewById(R.id.btnenglish);
        btn_fr = findViewById(R.id.btnFrancais);
        btn_sp = findViewById(R.id.btnSpanish);

        //onClickListener

        btn_eng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LanguageActivity.this , MapLanguageActivity.class);
                startActivity(intent);

            }
        });

        btn_fr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LanguageActivity.this , MapFr.class);
                startActivity(intent);

            }
        });
        btn_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LanguageActivity.this , MapSP.class);
                startActivity(intent);

            }
        });

    }
}
