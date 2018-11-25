package com.dev.thunderkilll.mundylingo.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dev.thunderkilll.mundylingo.R;

public class LoginActivity extends AppCompatActivity {

    private Button button2;
    private EditText username ;
    private EditText password ;
    SharedPreferences sharedPrefereces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPrefereces = getPreferences(MODE_PRIVATE);
        username = (EditText) findViewById(R.id.log_username);
        password = (EditText) findViewById(R.id.log_password);


    }

    public void SavePref(){
        SharedPreferences.Editor editor= sharedPrefereces.edit();
        String username1 = username.getText().toString();
        String password1 = password.getText().toString();
        editor.putString("username",username1); // "name" the key that will be saved in shared prefrences , name1 is the value
        editor.putString("password",password1);
        editor.commit(); //save preferences
    }





    public void LogIn(View view) {
        try {
            //login change logic
            //if user exist in database loginBase = true and Password = true => go next activity
            //else if the shared Preference is not empty
            //stay here
            String text = sharedPrefereces.getString("username","");
            username.setText(text);
            String text2 = sharedPrefereces.getString("password","");
            password.setText(text2);

            String staticUsername = username.getText().toString();
            String staticPassword = password.getText().toString();
            System.out.println( username.getText().toString());
            System.out.println( password.getText().toString());

            SavePref();

            if( staticUsername.equals(text) &&  staticPassword.equals(text2) ) {
                Toast.makeText(LoginActivity.this ,"  Welcome ",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, Main3Activity.class);
                startActivity(intent);
            }else{
                Toast.makeText(LoginActivity.this ,"username or password are wrong",Toast.LENGTH_SHORT).show();

            }
        }catch (NullPointerException e){

        }

    }

    public void SignIn(View view) {

        Intent intent = new Intent(this , RegisterActivity.class); // send me to the register activity
        startActivity(intent);
    }
    public void  loginApp(){
        //message d'alert 9bal manimchi lil activity elli mba3dha
        Toast.makeText(LoginActivity.this ,"  Welcome ",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Main3Activity.class);
        startActivity(intent);
    }
}
