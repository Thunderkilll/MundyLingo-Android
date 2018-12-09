package com.dev.thunderkilll.mundylingo.Activities;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dev.thunderkilll.mundylingo.Models.User;
import com.dev.thunderkilll.mundylingo.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import static com.facebook.Profile.getCurrentProfile;
/*

made By Khaled and Raoudha

 */

public class LoginActivity extends AppCompatActivity {

    private Button button2;
    private static final String EMAIL = "email";
    private LoginButton loginButton;
    private EditText username ;
    private EditText password ;
    public static Profile user ;
   // private String url = "http://192.168.1.6/miniProjetWebService/Langue/user/addNewUser.php";
    private String url = "http://192.168.1.6/miniProjetWebService/Langue/user/searchUser.php";
    public static User currentUser ;

    private CallbackManager callbackManager ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//GONNA REPLACE ALL THIS BY A FACEBOOK BUTTON AND GMAIL BUTTON
        //check status of the user if he is already connected once in this account

       // String state = sharedPreferences.getString("etat" ,"");
       // if(state.equals("connected")) {

            setContentView(R.layout.activity_login);
          //  sharedPreferences = getPreferences(MODE_PRIVATE);
            username =  findViewById(R.id.log_username);
            password =  findViewById(R.id.log_password);


        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
Log.e("isLoggeIn", String.valueOf(isLoggedIn));

if(isLoggedIn){
//set the curent user
     user = getCurrentProfile ();

//current logged user is static
    //  currentUser = new User( user.getName() , String.valueOf(user.getProfilePictureUri(75,75) ) ,"10","10","0", "0","1","1","1","1", String.valueOf(user.getId()));


    Intent intent = new Intent(this , MainActivity.class); // send me to the main activity activity
    startActivity(intent);
    Toast.makeText(LoginActivity.this ,"  Welcome "+ user.getName(),Toast.LENGTH_LONG).show();

}

//callback register facebook
        callbackManager = CallbackManager.Factory.create();
        loginButton =  findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));




     loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                System.out.println("login successuful");
                Intent intent = new Intent(LoginActivity.this , MainActivity.class); // send me to the main activity activity
                startActivity(intent);
                System.out.println(loginResult.getAccessToken());
                System.out.println(loginResult.getRecentlyGrantedPermissions());
              user = getCurrentProfile() ;

               // loginApp(user.getName() ,image , user.getId() );
            }

            @Override
            public void onCancel() {
                Log.d("canceled fb","login canceled");

            }

            @Override
            public void onError(FacebookException exception) {
                exception.getCause().toString();

                System.out.println("login error  "+exception.getMessage().toString());
              Log.e("facebook",exception.getCause().toString());

            }
        });




    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


//login the application
    public void LogIn(View view) {

        currentUser = new User( "Khaled Guesmi" ,
                "https://graph.facebook.com/v3.2/10212541082352959/picture?height=150&width=150&migration_overrides=%7Boctober_2012%3Atrue%7D" ,
                "10","10","0",
                "0","1","1",
                "1","1", String.valueOf(randomId(0,10000000 )) );

        Intent intent = new Intent(this , MainActivity.class); // send me to the main activity activity
        startActivity(intent);


    }










//register a new account in the application


    public void  loginApp(String usernamedb , String imageUri , String idFacebook ){

        url += "?username="+usernamedb+"&image="+imageUri+"&idFG="+idFacebook;



        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("post response ",response);
                //JSONObject jsonObject = new JSONObject(response);
                Log.e("post : response object " ,response);
                Toast.makeText(LoginActivity.this, "inside try and catch", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "error someweher in the request", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                HashMap<String, String> params2 = new HashMap<String, String>();
                // params2.put("id_user", Integer.toString(id));
                //params2.put("id_cause", Integer.toString(c1.getId()));
                params2.put("username",username.getText().toString());
                params2.put("image",password.getText().toString());
                params2.put("idFG",String.valueOf(randomId(0,100000)));
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

public int randomId(int min , int max ){

    Random r = new Random();
    int i = r.nextInt(max-min +1)+min ;
        return i ;
}

}
