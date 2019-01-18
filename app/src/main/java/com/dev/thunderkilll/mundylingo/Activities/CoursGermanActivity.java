package com.dev.thunderkilll.mundylingo.Activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.dev.thunderkilll.mundylingo.Adapters.CoursAdapter;
import com.dev.thunderkilll.mundylingo.Helpers.RecyclerTouchListener;
import com.dev.thunderkilll.mundylingo.Models.Cour;
import com.dev.thunderkilll.mundylingo.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import static com.dev.thunderkilll.mundylingo.Activities.LoginActivity.IPadress;
import static com.dev.thunderkilll.mundylingo.Activities.LoginActivity.currentUser;
import static com.dev.thunderkilll.mundylingo.fragment.CoursFragment.selectedCour;

public class CoursGermanActivity extends AppCompatActivity {


    public String url = IPadress + "/miniProjetWebService/Langue/cours/selectCourParLangue.php?langue=3";
    private RecyclerView recyclerView;
    private CoursAdapter mAdapter;
    private List<Cour> courList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cours_german);
        recyclerView = findViewById(R.id.recycler_view_gr);
        courList = new ArrayList<>();
        getDataGerman();
        mAdapter = new CoursAdapter(this, courList);


        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new  GridSpacingItemDecoration(30, dpToPx(8), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Cour cour = courList.get(position);
                int levelID = Integer.parseInt(cour.getIdLevel());
                int langueID = Integer.parseInt(cour.getLangue());
                if (levelID > ConvertToInt(currentUser.getLevelGer())) {
                    Toast.makeText(CoursGermanActivity.this, "  your level is low for this course please finish the level first ", Toast.LENGTH_SHORT).show();

                } else {
                    //redirect to cours details

                    Intent intent = new Intent(CoursGermanActivity.this, ConjugaisonDetailActivity.class);
                    startActivity(intent);

                    selectedCour = cour;
                }

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

//grid spacing between my couses

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }


    //convert a string to int if possible
    public int ConvertToInt(String numb) {

        return Integer.parseInt(numb);
    }


    public void getDataGerman() {


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                Log.e("index>>>>Spanish>>", jsonArray.toString());
                Log.e("index>>>>Spanish>>Url", url);


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
                            System.out.println(courList.get(j).toString());
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                mAdapter.itemcourList = courList;
                mAdapter.notifyDataSetChanged();

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
