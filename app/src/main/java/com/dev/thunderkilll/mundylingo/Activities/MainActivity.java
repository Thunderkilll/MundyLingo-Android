package com.dev.thunderkilll.mundylingo.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.dev.thunderkilll.mundylingo.Adapters.CoursAdapter;
import com.dev.thunderkilll.mundylingo.Adapters.UserAdapter;
import com.dev.thunderkilll.mundylingo.Models.Cour;
import com.dev.thunderkilll.mundylingo.Models.User;
import com.dev.thunderkilll.mundylingo.R;
import com.dev.thunderkilll.mundylingo.fragment.CoursFragment;
import com.dev.thunderkilll.mundylingo.fragment.HomeFragment;
import com.dev.thunderkilll.mundylingo.fragment.LeadFragment;
import com.dev.thunderkilll.mundylingo.fragment.ProfileFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.dev.thunderkilll.mundylingo.Activities.LoginActivity.IPadress;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    public static List<User> UseritemsList;
    public static List<User> UseritemsListFr;
    public static List<User> UseritemsListSp;
    public static List<User> UseritemsListGr;
    public static List<Cour> courList;
    public static UserAdapter mAdapter;
    public static UserAdapter mAdapterFR;
    public static UserAdapter mAdapterSP;
    public static UserAdapter mAdapterGR;
    public static CoursAdapter m2Adapter;
    //Urls
    public String url2 = IPadress + "/miniProjetWebService/Langue/leaderboard/LeaderboardENG.php";
    public String url3 = IPadress + "/miniProjetWebService/Langue/leaderboard/LeaderboardFR.php";
    public String url4 = IPadress + "/miniProjetWebService/Langue/leaderboard/LeaderboardESP.php";
    public String url5 = IPadress + "/miniProjetWebService/Langue/leaderboard/LeaderboardGER.php";
    private static final String URLi = IPadress + "/miniProjetWebService/Langue/cours/getAllCourses.php";

//TODO: getters and setters

    public static List<User> getUseritemsListFr() {
        return UseritemsListFr;
    }

    public static void setUseritemsListFr(List<User> useritemsListFr) {
        UseritemsListFr = useritemsListFr;
    }

    public static List<User> getUseritemsListSp() {
        return UseritemsListSp;
    }

    public static void setUseritemsListSp(List<User> useritemsListSp) {
        UseritemsListSp = useritemsListSp;
    }

    public static List<User> getUseritemsListGr() {
        return UseritemsListGr;
    }

    public static void setUseritemsListGr(List<User> useritemsListGr) {
        UseritemsListGr = useritemsListGr;
    }

    public static UserAdapter getmAdapterFR() {
        return mAdapterFR;
    }

    public static void setmAdapterFR(UserAdapter mAdapterFR) {
        MainActivity.mAdapterFR = mAdapterFR;
    }

    public static UserAdapter getmAdapterSP() {
        return mAdapterSP;
    }

    public static void setmAdapterSP(UserAdapter mAdapterSP) {
        MainActivity.mAdapterSP = mAdapterSP;
    }

    public static UserAdapter getmAdapterGR() {
        return mAdapterGR;
    }

    public static void setmAdapterGR(UserAdapter mAdapterGR) {
        MainActivity.mAdapterGR = mAdapterGR;
    }

    public static List<Cour> getCourList() {
        return courList;
    }

    public static void setCourList(List<Cour> courList) {
        MainActivity.courList = courList;
    }

    public static CoursAdapter getM2Adapter() {
        return m2Adapter;
    }

    public static void setM2Adapter(CoursAdapter m2Adapter) {
        MainActivity.m2Adapter = m2Adapter;
    }

    public static List<User> getUseritemsList() {
        return UseritemsList;
    }

    public static void setUseritemsList(List<User> useritemsList) {
        UseritemsList = useritemsList;
    }

    public static UserAdapter getmAdapter() {
        return mAdapter;
    }

    public static void setmAdapter(UserAdapter mAdapter) {
        MainActivity.mAdapter = mAdapter;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar); //to set each fragment title
        BottomNavigationView navigation = findViewById(R.id.navigation); //to navigate to each fragment i nead a ref for the nav bottom view
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //declaring the lists of objects

        UseritemsList = new ArrayList<>();
        UseritemsListFr = new ArrayList<>();
        UseritemsListSp = new ArrayList<>();
        UseritemsListGr = new ArrayList<>();
        courList = new ArrayList<>();

        // Json response
        getListEnglish();
        getListFrench();
        getListSpanish();
        getListGerman();
        getData();
        //editing the adapter
        mAdapter = new UserAdapter(this, UseritemsList);
        mAdapterFR = new UserAdapter(this, UseritemsListFr);
        mAdapterSP = new UserAdapter(this, UseritemsListSp);
        mAdapterGR = new UserAdapter(this, UseritemsListGr);
        m2Adapter = new CoursAdapter(this, courList);

        // load the home fragment  :   fragment by default
        toolbar.setTitle("Home");
        loadFragment(new HomeFragment());



    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_shop:
                    // toolbar.setTitle("Shop");
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    toolbar.setTitle("Home");
                    return true;
                case R.id.navigation_gifts:
                    //toolbar.setTitle("My Gifts");
                    fragment = new CoursFragment();
                    loadFragment(fragment);
                    toolbar.setTitle("main courses");
                    return true;
                case R.id.navigation_cart:
                    // toolbar.setTitle("Cart");
                    fragment = new LeadFragment();
                    loadFragment(fragment);
                    toolbar.setTitle("Leaderboard");
                    return true;
                case R.id.navigation_profile:
                    toolbar.setTitle("Profile");
                    fragment = new ProfileFragment();
                    loadFragment(fragment);
                    return true;
            }

            return false;
        }
    };

    /**
     * loading fragment into FrameLayout
     *
     * @param fragment
     */
    //function 3adeya n3aytilha bech nloadi another fragment
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void onSelect() {

        Intent intent = new Intent(MainActivity.this, CoursEnglishActivity.class);
        startActivity(intent);
    }

    //get leaderboard list of english language
    public void getListEnglish() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url2, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                Log.e("index>>>>>>", jsonArray.toString());


                try {


                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jObj = jsonArray.getJSONObject(i);
                        User user = new User(jObj.getString("email"), jObj.getString("image"), jObj.getString("scoreAng"));
                        UseritemsList.add(user);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mAdapter.leadersList = UseritemsList;
                //mAdapter = new UserAdapter(getActivity(), itemsList);

                mAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);


    }

    //get leaderboard list of french language
    public void getListFrench() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url3, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                Log.e("index>>>>>>", jsonArray.toString());


                try {


                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jObj = jsonArray.getJSONObject(i);
                        User user = new User(jObj.getString("email"), jObj.getString("image"), jObj.getString("scoreF"));
                        UseritemsListFr.add(user);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mAdapterFR.leadersList = UseritemsListFr;
                //mAdapter = new UserAdapter(getActivity(), itemsList);

                mAdapterFR.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);


    }

    //get leaderboard list of spanish language
    public void getListSpanish() {


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url4, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
//TODO: fill in the list of 5 best user in spanish
                try {


                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jObj = jsonArray.getJSONObject(i);
                        User user = new User(jObj.getString("email"), jObj.getString("image"), jObj.getString("scoreEsp"));
                        UseritemsListSp.add(user);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mAdapterSP.leadersList = UseritemsListSp;
                mAdapterSP.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);


    }

    //get leaderboard list of german language
    public void getListGerman() {
//TODO: fill in the list of 5 best user in german

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url5, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {

                try {
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jObj = jsonArray.getJSONObject(i);
                        User user = new User(jObj.getString("email"), jObj.getString("image"), jObj.getString("scoreGerm"));
                        UseritemsListGr.add(user);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mAdapterGR.leadersList = UseritemsListGr;
                mAdapterGR.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);


    }


    public void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLi, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                Log.e("index>>>>>>", jsonArray.toString());


                try {


                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jObj = jsonArray.getJSONObject(i);

                        Cour c = new Cour(
                                String.valueOf(jObj.getInt("id")),
                                jObj.getString("idLevel"),
                                jObj.getString("grammaire"),
                                jObj.getString("conjugaison"),
                                jObj.getString("orthographe"),
                                String.valueOf(jObj.getInt("langue")));


                        Log.d("affichageCours", c.toString());

                        courList.add(c);
                        for (int j = 0; j < courList.size(); j++) {
                            if(courList.get(j).getLangue().equals("1"))
                                System.out.println("fr");
                            if(courList.get(j).getLangue().equals("2"))
                                System.out.println("en");
                            if(courList.get(j).getLangue().equals("3"))
                                System.out.println("ger");
                            if(courList.get(j).getLangue().equals("4"))
                                System.out.println("sp");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                m2Adapter.itemcourList = courList;
                m2Adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }


}
