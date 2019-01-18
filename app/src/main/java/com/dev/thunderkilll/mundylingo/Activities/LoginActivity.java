package com.dev.thunderkilll.mundylingo.Activities;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dev.thunderkilll.mundylingo.AboutUs.AboutUsActivity;
import com.dev.thunderkilll.mundylingo.Helpers.DatabaseHelper;
import com.dev.thunderkilll.mundylingo.Models.User;
import com.dev.thunderkilll.mundylingo.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

/*

made By Khaled and Raoudha

 */

public class LoginActivity extends AppCompatActivity {


    private static final String EMAIL = "email";
    private LoginButton loginButton;
    private Button about;
    public static Profile user;
    public static String IPadress = "http://192.168.1.6";
    public String url = IPadress + "/miniProjetWebService/selectAjouUser.php?email=";
    public String url2 = IPadress + "/miniProjetWebService/selectUser.php?email=";
    public static User currentUser;
    public DatabaseHelper Mydb;
    private CallbackManager callbackManager;

    static String LinkUri = "";
    static String image = "";


    // Instance of Facebook Class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //initiate databse interne
        Mydb = new DatabaseHelper(this);
        about = findViewById(R.id.button4);
        //TODO: go page about us

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this , AboutUsActivity.class);
                startActivity(intent);
            }
        });


        //  sharedPreferences = getPreferences(MODE _PRIVATE);
        //   get access token from facebook
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        Log.e("isLoggeIn", String.valueOf(isLoggedIn));


        if (isLoggedIn) { //if user connected with facebook
            //set the curent user in database


            try {

                currentUser = Mydb.searchUser();
                Intent intent = new Intent(this, MainActivity.class); // send me to the main activity activity
                startActivity(intent);
                Toast.makeText(LoginActivity.this, "  Welcome " + currentUser.getUsername(), Toast.LENGTH_LONG).show();

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("login without user info ");

            }


        }


//callback register facebook
        callbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));//  a chercher


        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                try {


                    retrieveFbUserProfile(AccessToken.getCurrentAccessToken());

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class); // send me to the main activity activity
                    startActivity(intent);
                    System.out.println(loginResult.getAccessToken());
                    System.out.println(loginResult.getRecentlyGrantedPermissions());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("try&catch user", "in login success ");
                }

            }

            @Override
            public void onCancel() {
                Log.d("canceled fb", "login canceled");

            }

            @Override
            public void onError(FacebookException exception) {
                exception.getCause().toString();

                System.out.println("login error  " + exception.getMessage().toString());
                Log.e("facebook", exception.getCause().toString());

            }
        });


    }

    //getting fb information

    private void retrieveFbUserProfile(final AccessToken accessToken) {

        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        if (object != null) {
                            Log.d("logging", "onCompleted: " + object.toString());
                            User user = new User();
                            try {

                                user.setUsername(object.getString("name"));
                                user.setEmail(object.getString("email"));

                                user.setImgUrl("https://graph.facebook.com/" + accessToken.getUserId() + "/picture");
                                Log.d("logging", "onCompleted: user: " + user.toString());


                                LinkUri = user.getEmail();
                                image = user.getImgUrl();
                                url += LinkUri;
                                url += "&image=" + image;
                                loginApp(LinkUri, image);
                                user.setImgUrl("https://graph.facebook.com/" + accessToken.getUserId() + "/picture?height=600");
                                checkUser(user);

                            } catch (JSONException e) {
                                e.printStackTrace();
                                // hideProgressBar();
                                //showErrorAlert();
                            }
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,picture");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void checkUser(final User user) {
        url2 += user.getEmail();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url2, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {

                try {


                    JSONObject jObj = jsonArray.getJSONObject(0);

                    user.setLevelFr(jObj.getString("levelFr"));
                    user.setLevelEng(jObj.getString("levelAng"));
                    user.setLevelSpan(jObj.getString("levelEsp"));
                    user.setLevelGer(jObj.getString("levelGer"));
                    user.setScoreFr(jObj.getString("scoreF"));
                    user.setScoreEng(jObj.getString("scoreAng"));
                    user.setScoreSpan(jObj.getString("scoreEsp"));
                    user.setScoreGer(jObj.getString("scoreGerm"));
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    currentUser = user;
                    System.out.println("getting levels and scores :  " + currentUser);
                    System.out.println();
                    System.out.println();
                    Mydb.InsertUserData(currentUser);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);


    }

    //login the application
    public void Offline_LogIn(View view) {
        //login offline
        User u = new User("testUser", "Test Image", "10", "200", "35", "25", "2", "2", "5", "1", "86589859");
        Mydb.InsertUserData(u);
        //make an offline views
        Intent intent = new Intent(this, CoursPrefActivity.class); // send me to the main activity activity
        startActivity(intent);


    }


//register a new account in the application


    public void loginApp(final String strig, final String image) {

        System.out.println();
        System.out.println("this is url login");

        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("post response ", response);
                System.out.println();
                System.out.println();

                Log.e("post : response object ", response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("volley insert  ", error.getCause().toString());
                Toast.makeText(LoginActivity.this, "error someweher in the request", Toast.LENGTH_LONG).show();

            }
        }) {


            @Override
            public byte[] getBody() throws AuthFailureError {
                HashMap<String, String> params2 = new HashMap<String, String>();
                params2.put("email", strig);
                params2.put("image", image);

                return new JSONObject(params2).toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }


        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(sr);
    }


    public int randomId(int min, int max) {

        Random r = new Random();
        int i = r.nextInt(max - min + 1) + min;
        return i;
    }

}
