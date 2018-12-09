package com.dev.thunderkilll.mundylingo.Activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.dev.thunderkilll.mundylingo.R;


public class RegisterActivity extends AppCompatActivity {

    ImageView userphoto ;
    static  int PReqCode = 1 ;
    static  int REQUESCODE = 1 ;
    Uri pickedImageUri ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userphoto = (ImageView) findViewById(R.id.regUserPhoto);
        userphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= 22){
                    checkAndRequestForPermission();
                }else{

                    openGallery();
                }

            }
        });
    }

    private void openGallery() {
        //TODO : oppen gallery intent and wait for user to pick an imag!
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent , REQUESCODE);

    }

    private void checkAndRequestForPermission() {

        if (ContextCompat.checkSelfPermission(RegisterActivity.this ,android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){

            if(ActivityCompat.shouldShowRequestPermissionRationale(RegisterActivity.this,android.Manifest.permission.READ_EXTERNAL_STORAGE)){
                Toast.makeText(RegisterActivity.this ,"please accept for required permission",Toast.LENGTH_SHORT).show();
            }
            else{
                ActivityCompat.requestPermissions(RegisterActivity.this ,
                                                           new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                                                                PReqCode);
            }

        }
        else
            openGallery();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == REQUESCODE && data != null){
            //user successfully open gallery and pickd an image
            //i need to save the image now and it's reference
            pickedImageUri = data.getData();
            userphoto.setImageURI(pickedImageUri);
        }

    }

    public void toHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
