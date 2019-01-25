package com.dev.thunderkilll.mundylingo.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.dev.thunderkilll.mundylingo.Models.Cour;
import com.dev.thunderkilll.mundylingo.Models.User;

/*
 *
 * this is mad eby Khaled and Raoudha
 *
 */
//reform code ctrl +alt+L
public class DatabaseHelper extends SQLiteOpenHelper {

    //Declarations :
    //database name
    private static final String DATABASE_NAME = "mondy_database.db";
    //table name which will store the data
    private static final String TABLE_NAME_User = "t_user";
    private static final String TABLE_NAME_Cour = "t_cour";
    //attributes in this database
    //TODO: save the user information in his phone
    //TODO: these are the names of the attribute in variable for later usage
    private static final String id_user = "id"; //will be our primarry key for user table
    private static final String username = "username";
    private static final String imgUrl = "imgUrl";
    private static final String scfr = "scoreFr";
    private static final String scen = "scoreEng";
    private static final String scsp = "scoreSpan";
    private static final String scgr = "scoreGer";
    private static final String lvlfr = "levelFr";
    private static final String lvlen = "levelEng";
    private static final String lvlsp = "levelSpan";
    private static final String lvlgr = "levelGer";
    // this is for the table of saved cours
    // TABLE   COUR SQLite
    private static final String id_cour = "id"; //will be our primarry key
    private static final String t_langue = "langue"; //will be our primarry key
    private static final String grammaire = "grammaire"; //will be our primarry key
    private static final String conjugaison = "conjugaison"; //will be our primarry key
    private static final String orthographe = "orthographe"; //will be our primarry key
    private static final String secondary_key = "idc"; //will be our primarry key

    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, 1); //takes the database name as attribute
        //getWritableDatabase is the function to write something in the databas ein sqlite

        SQLiteDatabase db = this.getWritableDatabase(); //create our database and tables

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO: in here we are going to initiate our database means when we create the datbase we will create also our tables
        //TODO: the tables will be empty

        db.execSQL("create table " + TABLE_NAME_User + "(" + id_user + " INTEGER PRIMARY KEY AUTOINCREMENT, username  TEXT ,  imgUrl  TEXT ," +
                "  scoreFr  TEXT , scoreEng   TEXT , scoreSpan   TEXT ,scoreGer    TEXT , levelFr   TEXT ,levelEng    TEXT ," +
                "levelSpan   TEXT ,levelGer    TEXT )");
        Log.i("create user table", "table user created sqlite");
        db.execSQL("create table " + TABLE_NAME_Cour + "(" + id_cour + " INTEGER PRIMARY KEY AUTOINCREMENT,   grammaire  TEXT ,  conjugaison  TEXT ,  orthographe  TEXT , langue  TEXT , idc TEXT  )");
        Log.i("create cour table", "table cour created sqlite");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//TODO: upgrade our database we drop the encian one and then creat new one
        db.execSQL("drop table if exists " + TABLE_NAME_User);
        db.execSQL("drop table if exists " + TABLE_NAME_Cour);
        onCreate(db);

    }

    //TODO : the insert methodes
    //TODO : only 1 user must be saved here
    //TODO : save cours as much as we want

    // get all users fill base  si count == 0 insert user sinon delete l'encian w inseri ejdid from scratch
    //first select all methode

    //TODO :    User
    public Cursor getAllusers() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * from " + TABLE_NAME_User, new String[]{});

        return cur;
    }

    public int DeleteAllUsers() {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME_User, "1", null);//3 arguments ism el table , whereClause , string arguments
        //el return mta3 delete() howa 3dad les row affected sinon 0
    }

    public User searchUser() {
        User u = new User();
        SQLiteDatabase db = this.getWritableDatabase(); //get the database that was created in this instance
        Cursor c =  this.getAllusers();
        if (c.moveToLast()) {
            u.setUsername(c.getString(1));
            u.setEmail(c.getString(1));
            u.setImgUrl(c.getString(2));
            u.setScoreEng(c.getString(3));
            u.setScoreFr(c.getString(4));
            u.setScoreSpan(c.getString(5));
            u.setScoreGer(c.getString(6));
            u.setLevelFr(c.getString(7));
            u.setLevelEng(c.getString(8));
            u.setLevelSpan(c.getString(9));
            u.setLevelGer(c.getString(10));
            return u;

        }else {
            Log.e("error not found", "user can't be found or dabase empty");
            return u;
        }

    }

    //Inserting user to sqlite
    public boolean InsertUserData(User user) {

        SQLiteDatabase db = this.getWritableDatabase(); //return this database we are working on
        Cursor res = this.getAllusers();
        if (res.getCount() == 0) { //if response is empty
            //insert 3adi dans la base
            ContentValues contentValues = new ContentValues();//This class is used to store a set of values
//inserting
            contentValues.put(username, user.getEmail());  //contentValues(ism el colone , el valeur );
            contentValues.put(imgUrl, user.getImgUrl());
            contentValues.put(scfr, user.getScoreFr());
            contentValues.put(scen, user.getScoreEng());
            contentValues.put(scsp, user.getScoreSpan());
            contentValues.put(scgr, user.getScoreGer());
            contentValues.put(lvlfr, user.getLevelFr());
            contentValues.put(lvlen, user.getLevelEng());
            contentValues.put(lvlsp, user.getLevelSpan());
            contentValues.put(lvlgr, user.getLevelGer());
            long result = db.insert(TABLE_NAME_User, null, contentValues); //insert in the table
            if (result == -1)
                return false; //insert was not a success
            else
                return true; //insert was successuful

        } else {
//delete all
            this.DeleteAllUsers();
//start inserting
            ContentValues contentValues = new ContentValues();//This class is used to store a set of values
//inserting
            contentValues.put(username, user.getEmail());  //contentValues(ism el colone , el valeur );
            contentValues.put(imgUrl, user.getImgUrl());
            contentValues.put(scfr, user.getScoreFr());
            contentValues.put(scen, user.getScoreEng());
            contentValues.put(scsp, user.getScoreSpan());
            contentValues.put(scgr, user.getScoreGer());
            contentValues.put(lvlfr, user.getLevelFr());
            contentValues.put(lvlen, user.getLevelEng());
            contentValues.put(lvlsp, user.getLevelSpan());
            contentValues.put(lvlgr, user.getLevelGer());
            long result = db.insert(TABLE_NAME_User, null, contentValues); //insert in the table
            if (result == -1)

                return false; //insert was not a success

            else
                return true; //insert was successuful

        }

    }


    //TODO : Cour

    public Cursor getAllCours() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * from " + TABLE_NAME_Cour, new String[]{});

        return cur;
    }

    public int DeleteAllCours() {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME_Cour, "1", null);//3 arguments ism el table , whereClause , string arguments
        //el return mta3 delete() howa 3dad les row affected sinon 0
    }


    public boolean InsertCour(String langue, String text1 , String text2 , String text3 , String text4) {

        SQLiteDatabase db = this.getWritableDatabase(); //return this database we are working on

        if (searchCour(text4)){ // meas that the cours already exists
            return false;
        }else{
            ContentValues contentValues = new ContentValues();//This class is used to store a set of values
            contentValues.put(t_langue, langue);
            contentValues.put(grammaire, text1);
            contentValues.put(conjugaison, text2);
            contentValues.put(orthographe, text3);
            contentValues.put(secondary_key , text4);
            long result = db.insert(TABLE_NAME_Cour, null, contentValues); //insert in the table
            if (result == -1)

                return false; //insert was not a success

            else
                return true; //insert was successuful
        }




    }

    public boolean searchCour(String idc){

        SQLiteDatabase db = this.getWritableDatabase(); //return this database we are working on
        Cursor cur = db.rawQuery("SELECT * from " + TABLE_NAME_Cour +" where idc ="+idc , new String[]{});

        if (cur.getCount() == 0)
        {
            return false ;
        }else{
            return true ;
        }

    }


}
