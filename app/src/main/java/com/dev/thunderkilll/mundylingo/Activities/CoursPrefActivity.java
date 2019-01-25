package com.dev.thunderkilll.mundylingo.Activities;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;


import com.dev.thunderkilll.mundylingo.Adapters.SavedCoursAdapters;
import com.dev.thunderkilll.mundylingo.Helpers.DatabaseHelper;
import com.dev.thunderkilll.mundylingo.Helpers.InternetConnectivityObserver;
import com.dev.thunderkilll.mundylingo.Models.Cour;
import com.dev.thunderkilll.mundylingo.R;

import java.util.ArrayList;
import java.util.List;

public class CoursPrefActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SavedCoursAdapters mAdapter;
    private List<Cour> courList;
    DatabaseHelper Mydb;
    private boolean mIsOnline = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cours_pref);
        Mydb = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.recycler_view_pref);
        courList = new ArrayList<>();
        //remplir la liste

        Cursor c = Mydb.getAllCours();
        while (c.moveToNext()) {
            Cour nc = new Cour();
            nc.setId(String.valueOf(c.getInt(0)));
            nc.setGrammaire(c.getString(1));
            nc.setConjugaison(c.getString(2));
            nc.setOrthographe(c.getString(3));
            nc.setLangue(c.getString(4));
            nc.setId(c.getString(5));

            courList.add(nc);

        }
        System.out.println("this is list cours size" + courList.size());
        for (int i = 0; i < courList.size(); i++) {
            System.out.println(courList.get(i).getLangue());
        }
        mAdapter = new SavedCoursAdapters(this, courList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(8), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


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


    @Override
    protected void onResume() {
        super.onResume();

        InternetConnectivityObserver.get().setConsumer(new InternetConnectivityObserver.Consumer() {
            @Override
            public void accept(boolean internet) {

                mIsOnline = internet;

                if (internet) {
                    Intent i = new Intent(CoursPrefActivity.this, LoginActivity.class);
                    startActivity(i);
                } else {
//stay here
                }
            }
        });
        InternetConnectivityObserver.get().stop();
        InternetConnectivityObserver.get().start();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }


}
