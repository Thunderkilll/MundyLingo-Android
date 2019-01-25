package com.dev.thunderkilll.mundylingo.fragment;


import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dev.thunderkilll.mundylingo.Activities.CoursEnglishActivity;
import com.dev.thunderkilll.mundylingo.Activities.CoursFrançais;
import com.dev.thunderkilll.mundylingo.Activities.CoursGermanActivity;
import com.dev.thunderkilll.mundylingo.Activities.CoursSpanish;
import com.dev.thunderkilll.mundylingo.Helpers.RecyclerTouchListener;
import com.dev.thunderkilll.mundylingo.Models.Langue;
import com.dev.thunderkilll.mundylingo.R;
import java.util.ArrayList;
import java.util.List;
import static com.dev.thunderkilll.mundylingo.Activities.LoginActivity.IPadress;
import static com.dev.thunderkilll.mundylingo.Activities.LoginActivity.currentUser;


public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String URLi = IPadress+"/miniProjetWebService/Langue/selectAllLangues.php";
    private static final String TAG = HomeFragment.class.getSimpleName();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private List<Langue> itemsList;
    private LangueAdapter mAdapter;






    public HomeFragment() {

    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        itemsList = new ArrayList<>();
        mAdapter = new LangueAdapter(getActivity(), itemsList);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(8), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Langue langue = itemsList.get(position);

                //TODO: go to the next view par el intent
                  //les conditions bech najim nit3adda lil les maps
                if (langue.getTitle().equals("english")) {
                    //>>redirect to game map of english
                    Intent intent = new Intent(getContext(), CoursEnglishActivity.class);
                    getActivity().startActivity(intent);

                }
                if (langue.getTitle().equals("french")) {
                    //>>redirect to french map
                    Intent intent = new Intent(getContext(), CoursFrançais.class);
                    getActivity().startActivity(intent);

                }
                if (langue.getTitle().equals("spanish")) {
                    //>>redirect to spanish map
                    Intent intent = new Intent(getContext(), CoursSpanish.class);
                    getActivity().startActivity(intent);

                }
                if (langue.getTitle().equals("german")) {
                    // >>redirect to spanish map
                    Intent intent = new Intent(getContext(), CoursGermanActivity.class);
                    getActivity().startActivity(intent);

                }

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareMovieData();
       return view ;
    }


    private void prepareMovieData() {
     Langue langue = new Langue("english" ,IPadress+"/miniProjetRessources/englishflag.jpg","");
     itemsList.add(langue);

         langue = new Langue("french" ,IPadress+"/miniProjetRessources/french_flag.jpg","");
        itemsList.add(langue);
        langue = new Langue("spanish" ,IPadress+"/miniProjetRessources/spanishflag.jpg","");
        itemsList.add(langue);
        langue = new Langue("german" ,IPadress+"/miniProjetRessources/germanflag.jpg","");
        itemsList.add(langue);

        // notify adapter about data set changes
        // so that it will render the list with new data
        mAdapter.notifyDataSetChanged();
    }





    //TODO: convert image min dp to px
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }



//TODO: Read from a Json file ya3ni njib webservice w na9rah houni






    // TODO: custom spacing between elements in the recycle view

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






}
