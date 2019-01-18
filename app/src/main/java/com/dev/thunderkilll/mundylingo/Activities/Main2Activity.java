package com.dev.thunderkilll.mundylingo.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.dev.thunderkilll.mundylingo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.dev.thunderkilll.mundylingo.Activities.LoginActivity.IPadress;

public class Main2Activity extends AppCompatActivity {

    //declaration des variables
    public String urlquetion = IPadress+"/miniprojetwebservice/selectQuestion.php?level=1&langue=";
    public String urlProposition =IPadress+"/miniprojetwebservice/selectProposition.php?level=1&question=";
    public String urlReponse =IPadress+"/miniprojetwebservice/selectReponsedeQuestion.php?level=1&question=";
    private TextView monQuestion;
    private TextView propo1;
    private TextView propo2;
    private TextView propo3;
    private TextView propo4;
    private Switch SW1;
    private Switch SW2;
    private Switch SW3;
    private Switch SW4;
    private String idQ = "";
    private String reponseVrai ;
    private int BonnusReponse;
    private String langue;
    private String level;
    private Toolbar toolbar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        langue = getIntent().getStringExtra("langue");
        level = getIntent().getStringExtra("level");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("this is a test 1" +langue);
        System.out.println("this is a test 2" +level);

        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        monQuestion =findViewById(R.id.questionTextView);
        propo1      =findViewById(R.id.Prop1TextView);
        propo2      =findViewById(R.id.prop2TextView);
        propo3      =findViewById(R.id.prop3TextView);
        propo4      =findViewById(R.id.prop4TextView);
        SW1         =findViewById(R.id.switch1);
        SW2         =findViewById(R.id.switch2);
        SW3         =findViewById(R.id.switch3);
        SW4         =findViewById(R.id.switch4);
        toolbar     =findViewById(R.id.toolbarQt);
        toolbar.setTitle("Questions");
        setSupportActionBar(toolbar);

        urlquetion += langue;
        urlquetion +="&type=0";
        SW1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    Toast.makeText(Main2Activity.this,"ischecked",Toast.LENGTH_LONG).show();
                    SW2.setChecked(false);
                    SW3.setChecked(false);
                    SW4.setChecked(false);
                }
            }
        });
        SW2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    Toast.makeText(Main2Activity.this,"ischecked",Toast.LENGTH_LONG).show();
                    SW1.setChecked(false);
                    SW3.setChecked(false);
                    SW4.setChecked(false);
                }
            }
        });
        SW3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    Toast.makeText(Main2Activity.this,"ischecked",Toast.LENGTH_LONG).show();
                    SW1.setChecked(false);
                    SW2.setChecked(false);
                    SW4.setChecked(false);
                }
            }
        });
        SW4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    Toast.makeText(Main2Activity.this,"ischecked",Toast.LENGTH_LONG).show();
                    SW1.setChecked(false);
                    SW3.setChecked(false);
                    SW2.setChecked(false);
                }
            }
        });

        GetQuestion(urlquetion);
    }










    public void GetQuestion(final String url)
    {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("response from Json", response.toString());
                try {
                    for (int i = 0; i<response.length();i++){
                        JSONObject jsonObject = response.getJSONObject(i);
                        //Toast.makeText(Main2Activity.this,jsonObject.getString("contenu"),Toast.LENGTH_LONG).show();
                        monQuestion.setText(jsonObject.getString("contenu"));
                        idQ =jsonObject.getString("id");
                        urlProposition +=idQ;
                        urlProposition +="&langue=";
                        urlProposition +=langue;
                        urlReponse +=idQ;
                        urlReponse +="&idLangue=";
                        urlReponse +=langue;
                        GetPropositions(urlProposition);
                        GetReponseDeQuestion(urlReponse);

                    }
                }catch (JSONException e){
                    Log.e("json exception",e.getMessage());
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("volley error", error.getMessage());
            }
        });
        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(jsonArrayRequest);

    }

    public void GetPropositions (String url)
    {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("response from Json 2", response.toString());
                try {
                    JSONObject jsonObject1 = response.getJSONObject(0);
                    //propo1.setText(jsonObject1.getString("contenu"));
                    SW1.setText(jsonObject1.getString("contenu"));
                    JSONObject jsonObject2 = response.getJSONObject(1);
                    //propo2.setText(jsonObject2.getString("contenu"));
                    SW2.setText(jsonObject2.getString("contenu"));
                    JSONObject jsonObject3 = response.getJSONObject(2);
                    //propo3.setText(jsonObject3.getString("contenu"));
                    SW3.setText(jsonObject3.getString("contenu"));

                    JSONObject jsonObject4 = response.getJSONObject(3);
                    // propo4.setText(jsonObject4.getString("contenu"));
                    SW4.setText(jsonObject4.getString("contenu"));
                }catch (JSONException e){
                    Log.e("json exception 2",e.getMessage());
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("volley error 2", error.getMessage());


            }
        });
        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(jsonArrayRequest);

    }
    public void GetReponseDeQuestion(String url)
    {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("response from Json3", response.toString());
                try {

                    JSONObject jsonObject = response.getJSONObject(0);
                    reponseVrai = jsonObject.getString("text");

                    BonnusReponse =jsonObject.getInt("bonus");

                    //Toast.makeText(Main2Activity.this,"reponse vrai="+reponseVrai+"bonnus"+BonnusReponse,Toast.LENGTH_LONG).show();



                }catch (JSONException e){
                    Log.e("json exception3",e.getMessage());
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("volley error3", error.getMessage());
            }
        });
        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(jsonArrayRequest);
    }

    public void ValidateReponse(View view) {
        //Toast.makeText(Main2Activity.this,"reponse vrai"+reponseVrai,Toast.LENGTH_LONG).show();
        //   Toast.makeText(Main2Activity.this,SW1.getText(),Toast.LENGTH_LONG).show();

        if (SW1.isChecked()==true)
        {
            if(SW1.getText().equals(reponseVrai)){
                Toast.makeText(Main2Activity.this, "1 est la bonne reponse", Toast.LENGTH_LONG).show();

            }

        }
        if (SW2.isChecked()==true)
        {
            if(SW2.getText().equals(reponseVrai)){
                Toast.makeText(Main2Activity.this, "2 est la bonne reponse", Toast.LENGTH_LONG).show();

            }

        }
        if (SW3.isChecked()==true)
        {
            if(SW3.getText().equals(reponseVrai)){
                Toast.makeText(Main2Activity.this, "3 est la bonne reponse", Toast.LENGTH_LONG).show();

            }

        }
        if (SW4.isChecked()==true)
        {
            if(SW4.getText().equals(reponseVrai)){
                Toast.makeText(Main2Activity.this, "4 est la bonne reponse", Toast.LENGTH_LONG).show();

            }

        }
    }
}


